package imagecontainers.rosbag.extractors;

import com.github.swrirobotics.bags.reader.BagFile;
import com.github.swrirobotics.bags.reader.BagReader;
import com.github.swrirobotics.bags.reader.TopicInfo;
import com.github.swrirobotics.bags.reader.exceptions.BagReaderException;
import com.github.swrirobotics.bags.reader.messages.serialization.MessageType;

import java.io.File;
import java.util.Vector;

/**
 * The Udacity self-driving-car dataset (CH-03) is used for a simple ROS-Bag test.
 *
 * Created by kamir on 08.01.18.
 */
public class ExtractBagMetadataAndMessageInfo {

    public static void main(String[] args) throws BagReaderException {

        String fns = args[0];

        processBAGFile( new File(fns) );

    }

    public static void processBAGFile(File bag) throws BagReaderException {

        long begin = System.currentTimeMillis();

        File fn = bag;
        System.out.println("\n\t[File]");
        System.out.println( "\t" + fn.getAbsolutePath() + " - " + fn.canRead() + "\n" );

        BagFile file = BagReader.readFile( bag.getAbsoluteFile() );

        Vector<String> topics = new Vector<String>();

        System.out.println("\n\t[BAG Metadata]");

        String path = bag.getAbsolutePath();
        String startT = file.getStartTime().toString();
        String endT = file.getEndTime().toString();
        String duration = file.getDurationS() + " s";
        String compressionType = file.getCompressionType();

        System.out.println("\tPath        : " + path );
        System.out.println("\tstart time  : " + startT );
        System.out.println("\tend time    : " + endT );
        System.out.println("\tduration [s]: " + duration );
        System.out.println("\tcompression : " + compressionType );

        System.out.println("\n\n\t[Topics]");

        for (TopicInfo topic : file.getTopics()) {
            System.out.println( "\t" + topic.getName() + " \t\t" + topic.getMessageCount() +
                    " msgs \t: " + topic.getMessageType() + " \t" +
                    (topic.getConnectionCount() > 1 ? ("(" + topic.getConnectionCount() + " connections)") : ""));
            topics.add(topic.getName());
        }

        System.out.println("\n\n\t[Schema of Messages per Topic]");
        System.out.println("\n\t{topicname} [MD5]  package ");

        for( String tn : topics ) {

            MessageType mt = file.getFirstMessageOnTopic( tn );

            System.out.println("\n\t{"+ tn +"} [MD5:"+ mt.getMd5Sum() + "]  package: " + mt.getPackage());

            for( String fieldName : mt.getFieldNames() ) {
                System.out.println( "\t\t" + fieldName + " => " + mt.getField( fieldName).getType() );
            }

        }

        long end = System.currentTimeMillis();

        double delta = (end-begin) / 1000.0;

        System.out.println( "\n\n\t[Inspection Time] " + delta );

        try {

            file.printRDFInfo();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
