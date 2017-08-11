package sample.crypting.compilePck.cipherWorkshop;

import java.util.LinkedHashMap;
import java.util.Map;

import static sample.crypting.compilePck.cipherWorkshop.CodeFields.*;

/**
 * The class provides a map that will be used as a temporary depository of the
 * decompiled parts of a ciphered line. The access to the map is possible through
 * setters, which check the correctness of an introduced value with a help of
 * fixed filter set.
 */
    class Content extends Filter {

    private Map<CodeFields, String> contentMap = new LinkedHashMap<>();

    Content() {
        setDef();
    }

    Map<CodeFields, String> getContentMap() {
        return contentMap;
    }

    void setCipher(String value) throws IllegalArgumentException {
        if (getCipherFilter(value))
            contentMap.put(CIPHER, value);
        else throw new IllegalArgumentException("ILLEGAL_CIPHER_ARGUMENT");
    }

    void setDriverId(String value) throws IllegalArgumentException {
        if (getDriverIdFilter(value))
            contentMap.put(DRIVER_ID, value);
        else throw new IllegalArgumentException("ILLEGAL_DIVERID_ARGUMENT");
    }

    void setPrefix(String value) throws IllegalArgumentException {
        if (getPrefixFilter(value))
            contentMap.put(PREFIX, value);
        else throw new IllegalArgumentException("ILLEGAL_PREFIX_ARGUMENT");
    }

    void setFirstKey(String value) throws IllegalArgumentException {
        if (getFirstKeyFilter(value))
            contentMap.put(FIRSTKEY, value);
        else throw new IllegalArgumentException("ILLEGAL_FIRSTKEY_ARGUMENT");
    }

    void setWaybillCode(String value) throws IllegalArgumentException {
        if (getWaybillCodeFilter(value))
            contentMap.put(WAYBILL_CODE, value);
        else throw new IllegalArgumentException("ILLEGAL_WAYBILLCODE_ARGUMENT");
    }

    void setHazardType(String value) throws IllegalArgumentException {
        if (getHazardTypeFilter(value))
            contentMap.put(HAZARD_TYPE, value);
        else throw new IllegalArgumentException("ILLEGAL_HAZARDTYPE_ARGUMENT");
    }

    void setFragilityType(String value) throws IllegalArgumentException {
        if (getFragilityFilter(value))
            contentMap.put(FRAGALITY_TYPE, value);
        else throw new IllegalArgumentException("ILLEGAL_FRAGILITYTYPE_ARGUMENT");
    }

    void setWaybillNumber(String value) throws IllegalArgumentException {
        if (getWaybillNumberFilter(value))
            contentMap.put(WAYBILL_NUMBER, value);
        else throw new IllegalArgumentException("ILLEGAL_WAYBILLNUMBER_ARGUMENT");
    }

    void setDriverIdDup(String value) throws IllegalArgumentException {
        if (getWaybillEndingFilter(value))
            contentMap.put(DRVER_ID_DUP, value);
        else throw new IllegalArgumentException("ILLEGAL_DRIVERID_ARGUMENT");
    }

    void setTemperature(String value) throws IllegalArgumentException {
        if (getTemperatureFilter(value))
            contentMap.put(TEMPERATURE, value);
        else throw new IllegalArgumentException("ILLEGAL_TEMPERATURE_ARGUMENT");
    }

    void setCargoName(String value) throws IllegalArgumentException {
        if (getCargoNameFilter(value))
            contentMap.put(CARGO_NAME, value);
        else throw new IllegalArgumentException("ILLEGAL_CARGONAME_ARGUMENT");
    }

    void setAppendix(String value) throws IllegalArgumentException {
        if (getAppendixFilter(value))
            contentMap.put(APPENDIX, value);
        else throw new IllegalArgumentException("ILLEGAL_APPENDIX_ARGUMENT");
    }

    private static final String DEFAULTVALUE = "";

    private void setDef() {
        contentMap.put(CIPHER, DEFAULTVALUE);
        contentMap.put(DRIVER_ID, DEFAULTVALUE);
        contentMap.put(PREFIX, DEFAULTVALUE);
        contentMap.put(FIRSTKEY, DEFAULTVALUE);
        contentMap.put(WAYBILL_CODE, DEFAULTVALUE);
        contentMap.put(HAZARD_TYPE, DEFAULTVALUE);
        contentMap.put(FRAGALITY_TYPE, DEFAULTVALUE);
        contentMap.put(WAYBILL_NUMBER, DEFAULTVALUE);
        contentMap.put(DRVER_ID_DUP, DEFAULTVALUE);
        contentMap.put(TEMPERATURE, DEFAULTVALUE);
        contentMap.put(CARGO_NAME, DEFAULTVALUE);
        contentMap.put(APPENDIX, DEFAULTVALUE);
    }
}
