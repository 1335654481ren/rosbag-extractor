package data.examples;

import datasets.BagFileDataSet;

import java.io.File;

/**
 * Created by kamir on 08.01.18.
 */
public class UdacityExampleBAGFile extends BagFileDataSet {

    public UdacityExampleBAGFile() {
        init();
    }

    public void init(String fn) {
        File f = new File( fn );
        path = f.getAbsolutePath();
    }

    public void init() {
        String tempPath = example_bagfile_repository + "/udacity/self_driving_car/el_camino_north.bag";
        init( tempPath );
    }

    public File[] getBagFiles() {

        File[] folder = new File[1];

        if ( path == null ) {
            UdacityExampleBAGFile ebf = new UdacityExampleBAGFile();
            ebf.init();
            folder[0] = new File( ebf.path );
        }
        else {
            folder[0] = new File( path );
        }

        return folder;

    }

    public String path = null;

}
