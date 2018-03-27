package datasets.examples;

import datasets.BagFileDataSet;

import java.io.File;

/**
 * Created by kamir on 08.01.18.
 */
public class ExampleBAGFile extends BagFileDataSet {

    public ExampleBAGFile() {
        init();
    }

    public void init(String fn) {
        File f = new File( fn );
        path = f.getAbsolutePath();
    }

    public void init() {
        path = example_bagfile_repository + "/udacity/self_driving_car/el_camino_north.bag";
    }

    public File[] getBagFiles() {

        ExampleBAGFile ebf = new ExampleBAGFile();
        ebf.init();

        File[] folder = new File[1];

        folder[0] = new File( ebf.path );
        return folder;

    }

    public String path = null;

}
