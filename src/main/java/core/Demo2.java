package core;

import datasets.examples.BagBunkerExampleBAGFiles;

public class Demo2 {

    static boolean demo_BAG_extract = true;

    public static void main( String[] ARGS ) throws Exception {

        BagBunkerExampleBAGFiles bagbunkerData = new BagBunkerExampleBAGFiles();

        if ( ARGS != null ) {
            if ( ARGS.length > 0 ) {
                String fn = ARGS[0];
                bagbunkerData.init(fn);
            }
        }

        bagbunkerData.persistBAGFileMetadata();

    }

}
