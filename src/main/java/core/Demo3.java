package core;

import datasets.examples.UdacityExampleBAGFile;
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

        UdacityExampleBAGFile example = new UdacityExampleBAGFile();

        if ( ARGS != null ) {
            if ( ARGS.length > 0 ) {
                String fn = ARGS[0];
                example.init(fn);
            }
        }

        System.out.println( ">>> BAG Extraction DEMO ... ");
        System.out.println( ">>> BAG file: " + example.path );

        // Access ROS-BAG file
        if ( demo_BAG_extract ) {

            System.out.println( ">>> Inspection of Metadata ... ");
            example.persistBAGFileMetadata();

            System.out.println( ">   Extraction of images ... ");
            ExtractImageMessage.process( example.path );

            try {
                System.out.println(">   Extraction of presure data ... ");
                ExtractTimeSeriesByMetric.process(example.path);
            }
            catch(Exception ex) {
                System.out.println(">   NO PRESURE DATA AVAILABLE." );
            }

        }
    }

}
