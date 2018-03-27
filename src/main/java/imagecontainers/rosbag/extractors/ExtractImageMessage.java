package imagecontainers.rosbag.extractors;

import com.github.swrirobotics.bags.reader.BagFile;
import com.github.swrirobotics.bags.reader.BagReader;
import com.github.swrirobotics.bags.reader.exceptions.BagReaderException;
import data.ingest_stage.ImageIngestStage4BAGMessages;
import imagecontainers.rosbag.messagehandler.ImageMessageHandler;

/**
 *
 * The Udacity self-driving-car dataset (CH-03) is used for a simple ROS-Bag test.
 *
 * Created by kamir on 08.01.18.
 */
public class ExtractImageMessage {

    public static void process(String fn) throws BagReaderException {

        ImageIngestStage4BAGMessages stageLocal = new ImageIngestStage4BAGMessages();

        BagFile file = BagReader.readFile( fn );
        file.printInfo();

        ImageMessageHandler mh = new ImageMessageHandler( stageLocal.getFolder4ExtractedImages( fn ) );
        file.forMessagesOfType( ImageMessageHandler.topic_type, mh );

    }

}
