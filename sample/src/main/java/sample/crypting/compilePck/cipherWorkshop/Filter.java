package sample.crypting.compilePck.cipherWorkshop;

    import static java.util.Arrays.*;
    import static org.apache.commons.collections.CollectionUtils.containsAny;

/**
 * The class implements a filter set.
 */
    class Filter implements FilterSet{

    public boolean getCipherFilter(String testVal) {
        return true;
    }

    @Override
    public boolean getDriverIdFilter(String testVal) {

        int trueVal = Integer.parseInt(EnumFilter.DRIVERIDLENGTH.getKey()[0]);
        return (trueVal == testVal.length());
    }
    @Override
    public boolean getPrefixFilter(String testVal) {
        return (testVal.length() == 1 | testVal.length() == 2);
    }

    @Override
    public boolean getWaybillCodeFilter(String testVal) {
        return (containsAny(
                asList(testVal.substring(0, 1)),
                asList(EnumFilter.RKEY.getKey())));
    }

    @Override
    public boolean getWaybillNumberFilter(String testVal) {
        return (testVal.length() == 3 | testVal.length() == 4);
    }

    @Override
    public boolean getWaybillEndingFilter(String testVal) {
        return (testVal.length() == 4);
    }

    @Override
    public boolean getFirstKeyFilter(String testVal) {
        return (containsAny(
                asList(testVal),
                asList(EnumFilter.RKEY.getKey())));
    }

    @Override
    public boolean getHazardTypeFilter(String testVal) {
        return (containsAny(
                asList(testVal),
                asList(EnumFilter.ISHAZARDOUS.getKey())));
    }

    @Override
    public boolean getFragilityFilter(String testVal) {
        return (containsAny(
                asList(testVal),
                asList(EnumFilter.ISFRAGILE.getKey())));
    }

    @Override
    public boolean getTemperatureFilter(String testVal) {
        return ((testVal == "") | (testVal.length() == 4 &&
                (containsAny(
                        asList(testVal.substring(0, 1)),
                        asList(EnumFilter.HASTEMPERATURECONTROL.getKey())))));
    }

    @Override
    public boolean getCargoNameFilter(String testVal) {
        return ((testVal.length() % 3 == 0) &&
                testVal.matches("[0-9]+"));
    }

    @Override
    public boolean getAppendixFilter(String testVal) {
        return (testVal.length() == 1 | testVal.length() == 2);
    }

    /**
     * Enumeration of all possible keys;
     */
    enum EnumFilter {

        DRIVERIDLENGTH("4"), RKEY("R", "r"), ISHAZARDOUS("d", "D", "true", "false"),
        ISFRAGILE("f", "F", "true", "false"), HASTEMPERATURECONTROL("-", "+", "minus", "plus");

        private String[] key;
        EnumFilter(String... k) { key = k; }
        String[] getKey() { return key; }
    }
}

