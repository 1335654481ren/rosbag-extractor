package data.ingest_stage;

import java.io.File;

/**
 * Created by kamir on 26.03.18.
 */
public class ImageIngestStage4BAGMessages {

    public String getFolder4ExtractedImages(String fn) {
        File folder = new File( "./out/image_stage/" + fn.replaceAll("/","___") + "/"  );
        if ( !folder.exists() ) {
            folder.mkdirs();
        }
        return folder.getAbsolutePath();
    }

}
