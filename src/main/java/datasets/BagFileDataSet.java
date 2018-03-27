package datasets;

import com.github.swrirobotics.bags.reader.exceptions.BagReaderException;
import imagecontainers.rosbag.extractors.ExtractBagMetadataAndMessageInfo;

import java.io.File;

/**
 *
 *  Dataset Properties (as stored by Cloudera Navigator)
 * -----------------------------------------------------------------------------------------------

 Name	            Type                            Description

 compressionType	tokenizedCaseInsensitiveText	The type of compression of a dataset file.
 dataType	        string	                        The data type: record.
 datasetType	    tokenizedCaseInsensitiveText	The type of the dataset: Kite.
 fileFormat	        tokenizedCaseInsensitiveText	The format of a dataset file: Avro or Parquet.
 fullDataType	    string	                        The full data type: record.
 partitionType	    string	                        The type of the partition.
 schemaName	        string	                        The name of the dataset schema.
 schemaNameSpace	string	                        The namespace of the dataset schema.

 */
public abstract class BagFileDataSet {

    public String example_bagfile_repository = "/Volumes/DS-Tools/IWH/raw_image_stage";

    public abstract File[] getBagFiles();

    public void persistBAGFileMetadata() throws BagReaderException {
        File[] BAGS = this.getBagFiles();
        for( File bag : BAGS ) {
            ExtractBagMetadataAndMessageInfo.processBAGFile( bag );
        }
    }

}
