package sample.crypting.compilePck;

import sample.crypting.GeneratorCSV;
import sample.crypting.compilePck.cipherWorkshop.CodeFields;
import sample.crypting.compilePck.cipherWorkshop.Separator;

import java.util.*;

/**
 * The class works as a bridge for the further encryption/decryption.
 */
public class CodeCompiler {
//
//    private static Set<String> CipheredSet; // a set of unciphered lines.
//    private static Set<String> unCipheredSet; // a set of ciphered lines.
//    private static Map<CodeFields, String> map;
//    private static Separator separator;

//    public CodeCompiler(String...string){
//        CipheredSet = new HashSet<>();
//        unCipheredSet = new HashSet<>(CipheredSet);
//
//    }

    /**
     * Decompiles a set of lines and transmits it for one for the
     * decomposition.
     */
    public static Set<String> getUncryptedList(String...arg) {

        Set<String> cipheredSet = new HashSet<>();
        Set<String> unCipheredSet = new HashSet<>(cipheredSet);
        Map<CodeFields, String> map;
        Separator separator;

        String cipheredLine;

        cipheredSet.addAll(Arrays.asList(arg));
        Iterator lineIterator = cipheredSet.iterator();

        while (lineIterator.hasNext()) {
            cipheredLine = (String) lineIterator.next();
            separator = new Separator(cipheredLine); // launches a process of decomposition.
            map = separator.getCodeFields();

            String cargoDCrypt = OctalCrypting.octalDCrypt(map.get(CodeFields.CARGO_NAME));
            map.put(CodeFields.CARGO_NAME, cargoDCrypt);

            String CSV = GeneratorCSV.generateCSVFile(map);

            unCipheredSet.add(CSV);
        }
        return unCipheredSet;
    }
}




