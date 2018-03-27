package core;

import datasets.examples.BAGBunkerExampleBAGFiles;

public class Demo2 {

    static boolean demo_BAG_extract = true;

    public static void main( String[] ARGS ) throws Exception {

        BAGBunkerExampleBAGFiles bagbunkerData = new BAGBunkerExampleBAGFiles();

        bagbunkerData.persistBAGFileMetadata();

    }

}
