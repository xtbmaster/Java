package sample.crypting.compilePck.cipherWorkshop;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Arrays.*;
import static org.apache.commons.collections.CollectionUtils.containsAny;

/**
 * The class provides a set of methods to separate key parts of a ciphered
 * line, and store them for the further processing.
 */
public class Separator extends Content{

    private Content content;
    private StringBuffer processLine;
    private char FIRSTCHAR;

    /**
     * Constructor receives a ciphered line for the decomposition.
     * @param line to be divided into key parts.
     */
    public Separator(String line) {
        processLine = new StringBuffer(line);
        content = new Content();
    }

    private static final int DRIVERIDLENGTH = Integer.
            parseInt(EnumFilter.DRIVERIDLENGTH.getKey()[0]);
    private int start = 0, end = DRIVERIDLENGTH;

    /**
     * @return a map of uncrypted fields.
     */
    public Map<CodeFields, String> getCodeFields() {

        content.setCipher(processLine.toString());
        content.setDriverId(getDriverId(processLine));
        deletePrefix(processLine);
        content.setHazardType(getHazardType(processLine));
        content.setFragilityType(getFragilityType(processLine));
        content.setWaybillCode(getWaybillCode(processLine));
        content.setTemperature(getTemperature(processLine));
        content.setCargoName(getCargoNameCipher(processLine));

        return new LinkedHashMap<>(content.getContentMap());
    }

    /**
     * @return driver's ID cipher.
     */
        String getDriverId(StringBuffer sb){
            FIRSTCHAR = processLine.charAt(0);
        return sb.substring(start, end);
    }

    /**
     * Clears a line from a noise prefix.
     */
    String deletePrefix(StringBuffer sb) {

        int temp = 2 + DRIVERIDLENGTH;
        String tempCheck = "";

        while (temp >= 0) {
            // checking whether the line contains any of key elements.
            if (containsAny(
                    asList(processLine.substring(temp, temp + 1).split("(?!^)")),
                    asList(EnumFilter.RKEY.getKey()))){
                tempCheck = sb.substring(0, temp);
                processLine.replace(0, temp, "");
            }
            temp--;
        }
        return tempCheck;
    }

    private boolean isHazardous = false, isFragile = false;

    /**
     * @return hazard control type.
     */
    String getHazardType(StringBuffer sb) {

        if (containsAny(
                asList(sb.substring(1, 2).split("(?!^)")),
                asList(EnumFilter.ISHAZARDOUS.getKey()))) {
        isHazardous = true;
            return "true";
        }
        else return "false";
    }

    /**
     * @return fragility control type.
     */
    String getFragilityType(StringBuffer sb) {
        if (containsAny(
                asList(sb.substring(1, 3).split("(?!^)")),
                asList(EnumFilter.ISFRAGILE.getKey()))) {
            isFragile = true;
            return "true";
        }
        else return "false";
    }

    /**
     * Determines whether the line contains a duplication of the driver's ID.
     *
     * @return a duplication of the ID cipher or an empty value.
     */
    String getWaybillCode(StringBuffer sb) {
        String waybillCode;

        if (isFragile && isHazardous){
            waybillCode = sb.substring(0, 6);
            processLine.replace(0, 6, "");
        }else {
            waybillCode = sb.substring(0, 5);
            processLine.replace(0, 5, "");
        }
        if (processLine.charAt(0) == FIRSTCHAR){
            waybillCode.concat(sb.substring(0, 4));
            processLine.replace(0, 4, "");
        }
        return waybillCode;
    }

    /**
     * @return temperature control type.
     */
    String getTemperature(StringBuffer sb) {
        String temp;

        if (containsAny(
                asList(sb.substring(0, 1).split("(?!^)")),
                asList(EnumFilter.HASTEMPERATURECONTROL.getKey()))) {
            temp = sb.substring(0, 4);
            processLine.replace(0, 4, "");
            return temp;
        }
        else return "";
    }
    /**
     * @return crypted cargo name.
     */
    String getCargoNameCipher(StringBuffer sb) {
        int check = sb.length() % 3;
        if (check == 0) {
            return sb.toString();
        } else if (check == 1) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return sb.substring(0, sb.length() - 2);
        }
    }
}