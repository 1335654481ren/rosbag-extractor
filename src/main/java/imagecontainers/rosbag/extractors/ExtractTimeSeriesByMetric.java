package imagecontainers.rosbag.extractors;

import com.github.swrirobotics.bags.reader.BagFile;
import com.github.swrirobotics.bags.reader.BagReader;
import data.ingest_stage.TimeSeriesIngestStage4BAGMessages;
import imagecontainers.rosbag.messagehandler.MetricMessageHandler;
import opentsdb.MetricLabellTool;
import opentsdb.OpenTSDBConnector;
import org.apache.hadoopts.chart.simple.MultiChart;
import org.apache.hadoopts.data.series.TimeSeriesObject;
import org.apache.hadoopts.hadoopts.core.TSBucket;

import java.io.File;
import java.security.MessageDigest;
import java.util.Properties;

/**
 *
 * The Udacity self-driving-car dataset (CH-03) is used for a simple ROS-Bag test.
 *
 * Created by kamir on 08.01.18.
 */
public class ExtractTimeSeriesByMetric {

    public static boolean export_2_OpenTSDB = false;
    public static boolean export_2_TSB = true;
    public static boolean show_chart = false;

    public static void process(String filename) throws Exception {

        /**
         * Input data is loaded from a BAGFile from local fs.
         */
        File fn = new File( filename );
        BagFile file = BagReader.readFile( filename );

        file.printInfo();
        file.printRDFInfo();


        // TODO: Test with HDFS location
        // TODO: Test with S3 location
        // TODO: Test with "RDD of filenames" in cluster

        String topic_type = "sensor_msgs/FluidPressure";
        String fn_prefix = "pressure___sensor_msgs___FluidPressure";

        String CONTEXT = fn.getAbsolutePath();
        String BASE64CONTEXT = new String( java.util.Base64.getEncoder().encode( CONTEXT.getBytes() ));
        byte[] bytesOfContext = CONTEXT.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5OfContext = md.digest(bytesOfContext);

        MetricMessageHandler mh = new MetricMessageHandler( TimeSeriesIngestStage4BAGMessages.getFolder4ExtractedTimeSeries( filename ) , topic_type, fn_prefix, CONTEXT );

        file.forMessagesOfType( MetricMessageHandler.topic_type, mh );

        TimeSeriesObject tso = mh.getTimeSeriesObject();

        /**
         * The time series object needs a label in this format:
         *
         * "demo server=2,rack=1,ds=1"
         */
        String metricName = topic_type;
        String topic = mh.getTopic();
        Properties props = new Properties();

        props.put("topic", topic);
        props.put("provider", "udacity");
        props.put("context_hash", md5OfContext);

        tso = MetricLabellTool.adjustTSOLabel( tso, metricName, props );

        if ( export_2_OpenTSDB ) {
            OpenTSDBConnector connector = new OpenTSDBConnector("127.0.0.1");
            String result = opentsdb.OpenTSDBConnector.streamEventsToOpenTSDB(tso, connector);
            System.out.println("#### OpenTSDBConnector ####");
            System.out.println(result);
            tso.addComment("persisted_in_OpenTSDB: " + result);
        }
        if ( export_2_TSB ) {
            TSBucket tsb = new TSBucket();
            tsb._open(TimeSeriesIngestStage4BAGMessages.getFolder4ExtractedTimeSeries( filename ) + "/" + fn_prefix + "tsbucket.tsb");
            tsb.putMessreihe(tso, topic_type);
            tsb.close();
        }
        if ( show_chart ) {
            MultiChart.open( tso );
        }

    }

}
