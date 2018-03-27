package data.ingest_stage;

import java.io.File;

/**
 * Created by kamir on 26.03.18.
 */
public class TimeSeriesIngestStage4BAGMessages {

    public static String getFolder4ExtractedTimeSeries( String fn) {
        File folder = new File( "./out/ts_stage/" + fn.replaceAll("/","___") + "/" );
        if ( !folder.exists() ) {
            folder.mkdirs();
        }
        return folder.getAbsolutePath();
    }

}
