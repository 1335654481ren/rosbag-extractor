package core;

import com.github.swrirobotics.bags.reader.vocabulary.BagCAT;
import org.apache.jena.rdf.model.Model;

/**
 * Created by kamir on 26.03.18.
 */
public class Demo1 {

    public static void main(String[] ARGS) throws Exception {

        Model model = BagCAT.loadCatalogData();

    }

}
