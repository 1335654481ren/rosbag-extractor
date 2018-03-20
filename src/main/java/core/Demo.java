package core;

import imagecontainers.rosbag.ListMessageInfo;

public class Demo {

    static boolean demo_BAG_extract = true;

    public static void main( String[] ARGS ) throws Exception {

        System.out.println( ">>> BAG Extraction DEMO ... ");
        System.out.println( ">>> BAG file: " +  ARGS[0] );

        // Access ROS-BAG file
        if ( demo_BAG_extract ) {

            System.out.println( ">   Inspection of Metadata ... ");

            ListMessageInfo.main(ARGS);

            // System.out.println( ">   Extraction of images ... ");

            // ExtractImageMessage.main(ARGS);

        }
    }

}
