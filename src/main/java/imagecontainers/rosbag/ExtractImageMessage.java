package imagecontainers.rosbag;

import com.github.swrirobotics.bags.reader.BagFile;
import com.github.swrirobotics.bags.reader.BagReader;
import com.github.swrirobotics.bags.reader.exceptions.BagReaderException;

import datasets.ExampleBAGFile;

import java.io.File;

/**
 *
 * The Udacity self-driving-car dataset (CH-03) is used for a simple ROS-Bag test.
 *
 * Created by kamir on 08.01.18.
 */
public class ExtractImageMessage {

    public static void main(String[] args) throws BagReaderException {

        File inputBag = new File(  ExampleBAGFile.path + "_images" );
        BagFile file = BagReader.readFile( ExampleBAGFile.path );

        ImageMessageHandler mh = new ImageMessageHandler( inputBag.getAbsolutePath() );

        file.forMessagesOfType( ImageMessageHandler.topic_type, mh );

    }

}
