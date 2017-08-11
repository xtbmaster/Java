package sample.crypting.compilePck.cipherWorkshop;

/**
 * A set of check ups that should be applied for a ciphered line composition/decomposition.
 */
interface FilterSet {

    boolean getCipherFilter(String s);
    boolean getDriverIdFilter(String s);
    boolean getPrefixFilter(String s);
    boolean getFirstKeyFilter(String s);
    boolean getWaybillCodeFilter(String s);
    boolean getHazardTypeFilter(String s);
    boolean getFragilityFilter(String s);
    boolean getWaybillNumberFilter(String s);
    boolean getWaybillEndingFilter(String s);
    boolean getTemperatureFilter(String s);
    boolean getCargoNameFilter(String s);
    boolean getAppendixFilter(String s);
}
