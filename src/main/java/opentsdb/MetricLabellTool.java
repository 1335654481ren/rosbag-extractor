package opentsdb;

import org.apache.hadoopts.data.series.TimeSeriesObject;

import java.util.Properties;
import java.util.Set;

public class MetricLabellTool {



    public static TimeSeriesObject adjustTSOLabel(TimeSeriesObject tso, String metricName, Properties props) {

        StringBuffer sb = new StringBuffer();
        sb.append(metricName + " ");

        Set<Object> keys = props.keySet();

        String valueS = "???";
        String keyS = "???";

        for ( Object key : keys ) {

            try {

                Object value = props.get(key);

                valueS = (String)props.get(key);
                keyS = (String)key;

                sb.append( valueS + "=" + keyS + "," );

            }
            catch(Exception ex){

                System.out.println(">> Ignore property on TSO Label: " + keyS );

            }

        }

        // String label = "demo server=2,rack=1,ds=1";
        String label = sb.toString().substring(0, sb.toString().length()-1 );

        String oldLabel = tso.getLabel();
        tso.setLabel( label );

        tso.addComment( "oldLabel: " + oldLabel );

        return tso;

    }



}
