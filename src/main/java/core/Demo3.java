package core;

import datasets.examples.ExampleBAGFile;
import imagecontainers.rosbag.extractors.ExtractImageMessage;
import imagecontainers.rosbag.extractors.ExtractTimeSeriesByMetric;

/**
 *
 * One single Bag-File will be processed
 *
 */
public class Demo3 {

    static boolean demo_BAG_extract = true;

    public static void main( String[] ARGS ) throws Exception {

        ExampleBAGFile example = new ExampleBAGFile();

        System.out.println( ">>> BAG Extraction DEMO ... ");
        System.out.println( ">>> BAG file: " + example.path );

        // Access ROS-BAG file
        if ( demo_BAG_extract ) {

            System.out.println( ">>> Inspection of Metadata ... ");
            example.persistBAGFileMetadata();

            System.out.println( ">   Extraction of images ... ");
            ExtractImageMessage.process( example.path );

            System.out.println( ">   Extraction of presure data ... ");
            ExtractTimeSeriesByMetric.process( example.path );

        }
    }

}
