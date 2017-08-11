package sample.crypting.compilePck;

/**
 * The class provides methods of string-to-octal/octal-to-string
 * converting.
 */
public class OctalCrypting {

    /**
     * Converts an octal number into an open text.
     *
     * @param args octal number to be converted.
     * @return open text.
     */
    public static String octalDCrypt(String args) {
        StringBuilder tempString = new StringBuilder().append(args);

        String finalString = new String();
        int start = 0, end = 3;

        for (int i = 0; i < tempString.length() / 3; i++) {
            String tempSubstring = tempString.substring(start, end);
            finalString += (char) Integer.parseInt(tempSubstring, 8);
            start = end;
            end += 3;
        }
        return finalString;
    }

    /**
     * Converts a word into an octal number.
     *
     * @param args String to be converted.
     * @return octal number of the inserted word.
     */
    public static String octalNCrypt(String args) {
        final char[] charArray = args.toCharArray();
        String tempString = new String();

        for (int i = 0; i < charArray.length; i++) {
            tempString += (String) Integer.toOctalString(charArray[i]);
        }
        return tempString;
    }
}

