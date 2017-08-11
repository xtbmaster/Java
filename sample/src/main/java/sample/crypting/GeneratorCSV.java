package sample.crypting;

import sample.crypting.compilePck.cipherWorkshop.CodeFields;

import java.util.Iterator;
import java.util.Map;


/**
 * The class to generate a CSV line.
 */
public class GeneratorCSV {
    /**
     * Generates CSV line
     */
    public static String generateCSVFile(Map <CodeFields, String> map){
        String output = "";

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry pair = (Map.Entry) iterator.next();
            if (pair.getValue() != "")
            output += pair.getKey() + ",";
        }
        iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry pair = (Map.Entry) iterator.next();
            if ((pair.getValue() != "") && iterator.hasNext())
            output += pair.getValue() + ",";
        }
        return output;
    }
}
