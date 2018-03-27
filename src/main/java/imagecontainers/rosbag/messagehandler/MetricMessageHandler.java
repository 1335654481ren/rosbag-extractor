package imagecontainers.rosbag.messagehandler;

import com.github.swrirobotics.bags.reader.MessageHandler;
import com.github.swrirobotics.bags.reader.messages.serialization.Float64Type;
import com.github.swrirobotics.bags.reader.messages.serialization.MessageType;
import com.github.swrirobotics.bags.reader.messages.serialization.TimeType;
import com.github.swrirobotics.bags.reader.records.Connection;
import org.apache.hadoopts.data.series.TimeSeriesObject;

import java.io.File;

/**
 * Created by kamir on 08.01.18.
 */
public class MetricMessageHandler implements MessageHandler {

    static boolean debug = false;

    public MetricMessageHandler(String basePathToStore ) {
        basePath = new File( basePathToStore );
    }

    /**
     * The context of a particular extraction task can be provided as an encoded RDF graph.
     *
     * @param basePathToStore
     * @param tt
     * @param fnp
     * @param BASE64CONTEXT
     */
    public MetricMessageHandler(String basePathToStore, String tt, String fnp, String BASE64CONTEXT) {

        tso = new TimeSeriesObject();
        tso.setLabel(topic_type);
        tso.addComment("{ 'CONTEXT' : '" + BASE64CONTEXT + "' }");

        basePath = new File(basePathToStore);
        topic_type = tt;
        fn_prefix = fnp;
    }

    @Override
    public boolean postProcessTrigger() {

        if ( debug ) {
            System.out.println("# postProcessTrigger in " + this.getClass().getCanonicalName() + " ...");

            System.out.println(tso.toString());
        }

        return true;
    }

    TimeSeriesObject tso = null;

    // Which messages should the hanlder handle?
    public static String topic_type = "sensor_msgs/CompressedImage";
    public static String fn_prefix = "sensor_msgs_CompressedImage";

    //
    // Where should the extracted images be stored?
    //
    // Here we can bump into SOLR as well ;-)
    //
    File basePath = null;
    String topic = null;

    // COUNT MESSAGES EXTRACTED BY THIS HANDLER
    int i = 0;

    public boolean process(MessageType message, Connection con) {

        try {

            if ( i == 0 ) processConnectionMD( con, tso );

            i++;

            MessageType header = message.getField("header");
            TimeType ts = header.getField("stamp");

            Float64Type data = message.getField("fluid_pressure");

            Double value = data.getValue();

            tso.addValuePair( ts.getValue().getTime(), value);

            if ( debug ) {
                if (i % 1000 == 0)
                    System.out.println(i + "\t" + ts.getValue().toString() + "\t" + value);
            }

        }
        catch (Exception e) {

            e.printStackTrace();

            System.err.println("Field {"+topic_type+"} was not available.");

        }

        return true;
    }

    private void processConnectionMD(Connection con, TimeSeriesObject tso) {

        topic = con.getTopic();
        String callerId = con.getCallerId();
        String comment = con.getMessageDefinition();

        tso.appendToLabel( topic , "_" );

        tso.addComment( "callerId = " + callerId + "\n" + comment );

    }

    public TimeSeriesObject getTimeSeriesObject() {
        return tso;
    }

    public String getTopic() {
        return topic;
    }
}
