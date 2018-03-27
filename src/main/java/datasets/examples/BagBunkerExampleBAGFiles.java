package datasets.examples;

import datasets.BagFileDataSet;
import io.filefilters.BagFileFilter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by kamir on 08.01.18.
 */
public class BagBunkerExampleBAGFiles extends BagFileDataSet {

    public static void init(String fn) {
        File f = new File( fn );
        path = f.getAbsolutePath();
    }

    public void init() {
        path = "data";
    }

    public File[] getBagFiles(){
        BagBunkerExampleBAGFiles bbf = new BagBunkerExampleBAGFiles();
        bbf.init();
        File folder = new File( path );
        return folder.listFiles( ff );
    }

    public static FileFilter ff = new BagFileFilter();
    public static String path = null;

}
