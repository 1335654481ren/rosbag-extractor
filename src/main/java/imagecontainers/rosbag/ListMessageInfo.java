package imagecontainers.rosbag;

import com.github.swrirobotics.bags.reader.BagFile;
import com.github.swrirobotics.bags.reader.BagReader;
import com.github.swrirobotics.bags.reader.TopicInfo;
import com.github.swrirobotics.bags.reader.exceptions.BagReaderException;
import com.github.swrirobotics.bags.reader.messages.serialization.MessageType;
import datasets.ExampleBAGFile;

import java.io.File;
import java.util.Vector;

/**
 * The Udacity self-driving-car dataset (CH-03) is used for a simple ROS-Bag test.
 *
 * Created by kamir on 08.01.18.
 */
public class ListMessageInfo {

    public static void main(String[] args) throws BagReaderException {

        String fns = args[0];

        ExampleBAGFile.init( fns );

        long begin = System.currentTimeMillis();

        File fn = new File( ExampleBAGFile.path );
        System.out.println("\n[File]");
        System.out.println( "\t" + fn.getAbsolutePath() + " - " + fn.canRead() + "\n" );

        BagFile file = BagReader.readFile( ExampleBAGFile.path );

        Vector<String> topics = new Vector<String>();

        System.out.println("\n[BAG Metadata]");

        String path = ExampleBAGFile.path;
        String startT = file.getStartTime().toString();
        String endT = file.getEndTime().toString();
        String duration = file.getDurationS() + " s";
        String compressionType = file.getCompressionType();

        System.out.println("\tPath        : " + path );
        System.out.println("\tstart time  : " + startT );
        System.out.println("\tend time    : " + endT );
        System.out.println("\tduration [s]: " + duration );
        System.out.println("\tcompression : " + compressionType );



        System.out.println("\n\n[Topics]");

        for (TopicInfo topic : file.getTopics()) {
            System.out.println( "\t" + topic.getName() + " \t\t" + topic.getMessageCount() +
                    " msgs \t: " + topic.getMessageType() + " \t" +
                    (topic.getConnectionCount() > 1 ? ("(" + topic.getConnectionCount() + " connections)") : ""));
            topics.add(topic.getName());
        }

        ImageMessageHandler mh = new ImageMessageHandler( ExampleBAGFile.path );



        System.out.println("\n\n[Schema of Messages per Topic]");

        for( String tn : topics ) {

            MessageType mt = file.getFirstMessageOnTopic( tn );

            System.out.println("\n{"+ tn +"} [MD5:"+ mt.getMd5Sum() + "]  package: " + mt.getPackage());

            for( String fieldName : mt.getFieldNames() ) {
               System.out.println( "\t\t" + fieldName + " => " + mt.getField( fieldName).getType() );
            }

        }

        long end = System.currentTimeMillis();

        double delta = (end-begin) / 1000.0;

        System.out.println( "\n\n[Inspection Time] " + delta );

    }

}
