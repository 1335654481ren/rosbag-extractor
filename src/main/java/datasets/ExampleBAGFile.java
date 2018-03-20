package datasets;

import java.io.File;

/**
 * Created by kamir on 08.01.18.
 */
public class ExampleBAGFile {

    public static void init(String fn) {
        File f = new File( fn );
        path = f.getAbsolutePath();
    }

    public static void init() {
        path = "data/udacity/self_driving_car/el_camino_north.bag";
    }

    public static String path = null;

}
