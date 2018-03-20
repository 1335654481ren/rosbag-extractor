package imagecontainers.rosbag;

import com.github.swrirobotics.bags.reader.MessageHandler;
import com.github.swrirobotics.bags.reader.messages.serialization.ArrayType;
import com.github.swrirobotics.bags.reader.messages.serialization.MessageType;
import com.github.swrirobotics.bags.reader.records.Connection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by kamir on 08.01.18.
 */
public class ImageMessageHandler implements MessageHandler {

    public ImageMessageHandler( String basePathToStore ) {
        basePath = new File( basePathToStore );

    }

    // Which messages should the hanlder handle?
    public static String topic_type = "sensor_msgs/CompressedImage";
    public static String fn_prefix = "sensor_msgs_CompressedImage";

    //
    // Where should the extracted images be stored?
    //
    // Here we can bump into SOLR as well ;-)
    //
    File basePath = null;

    // COUNT MESSAGES EXTRACTED BY THIS HANDLER
    int i = 0;

    public boolean process(MessageType message, Connection con) {

        try {

            i++;

            ArrayType data = message.getField("data");
            byte[] dataBytes = data.getAsBytes();

            BufferedImage originalImage = ImageIO.read( new ByteArrayInputStream( dataBytes ) );

            FileOutputStream fout = new FileOutputStream( new File( basePath.getAbsolutePath() + "_" + fn_prefix + "_" + i + ".jpg" ) );
            ImageIO.write(originalImage, "jpg", fout);

            fout.flush();

        }
        catch (Exception e) {

            e.printStackTrace();

            System.err.println("Field was not available.");
        }

        return true;
    }

}
