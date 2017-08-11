package sample.crypting.compilePck.cipherWorkshop;

import org.junit.Test;

import static org.junit.Assert.*;

public class FilterTest {
    private Filter filter = new Filter();


    private static final String DRIVERIDVALIDVALUE = "4444";
    private static final String DRIVERIDINVALIDVALUE = "333";

    @Test
    public void getDriverIdFilterTest() {
    assertTrue(filter.getDriverIdFilter(DRIVERIDVALIDVALUE));
    assertFalse(filter.getDriverIdFilter(DRIVERIDINVALIDVALUE));
    }

    private static final String PREFIXVALIDVALUE_A = "1";
    private static final String PREFIXVALIDVALUE_B = "22";
    private static final String PREFIXINVALIDVALUE = "333";

    @Test
    public void getPrefixFilterTest() {
    assertTrue(filter.getPrefixFilter(PREFIXVALIDVALUE_A));
    assertTrue(filter.getPrefixFilter(PREFIXVALIDVALUE_B));
    assertFalse(filter.getPrefixFilter(PREFIXINVALIDVALUE));
    }

    private static final String WAYBILLCODEVALID_A = "Rdfwf";
    private static final String WAYBILLCODEVALID_B = "rW#Fgd";
    private static final String WAYBILLCODEINVALID = "Zffw";

    @Test
    public void getWaybillCodeFilterTest() {
    assertTrue(filter.getWaybillCodeFilter(WAYBILLCODEVALID_A));
    assertTrue(filter.getWaybillCodeFilter(WAYBILLCODEVALID_B));
    assertFalse(filter.getWaybillCodeFilter(WAYBILLCODEINVALID));
    }

    private static final String WAYBILLNUMBERVALID_A = "333";
    private static final String WAYBILLNUMBERVALID_B = "4444";
    private static final String WAYBILLNUMBERINVALID = "55555";

    @Test
    public void getWaybillNumberFilterTest() {
        assertTrue(filter.getWaybillNumberFilter(WAYBILLNUMBERVALID_A));
        assertTrue(filter.getWaybillNumberFilter(WAYBILLNUMBERVALID_B));
        assertFalse(filter.getWaybillNumberFilter(WAYBILLNUMBERINVALID));
    }

    private static final String WAYBILLENDINGVALID = "4444";
    private static final String WAYBILLENDINGINVALID = "666666";

    @Test
    public void getWaybillEndingFilterTest() {
    assertTrue(filter.getWaybillEndingFilter(WAYBILLENDINGVALID));
    assertFalse(filter.getWaybillEndingFilter(WAYBILLENDINGINVALID));
    }

    private static final String FIRSTKEYVALID_A = "R";
    private static final String FIRSTKEYVALID_B = "r";
    private static final String FIRSTKEYINVALID = "Z";

    @Test
    public void getFirstKeyFilterTest() {
        assertTrue(filter.getFirstKeyFilter(FIRSTKEYVALID_A));
        assertTrue(filter.getFirstKeyFilter(FIRSTKEYVALID_B));
        assertFalse(filter.getFirstKeyFilter(FIRSTKEYINVALID));
    }

    private static final String HAZARDOUSTYPEVALID = "d";
    private static final String HAZARDOUSTYPEINVALID = "h";

    @Test
    public void getHazardTypeFilterTest() {
        assertTrue(filter.getHazardTypeFilter(HAZARDOUSTYPEVALID));
        assertFalse(filter.getHazardTypeFilter(HAZARDOUSTYPEINVALID));
    }

    private static final String FRAGILITYTYPEVALID = "f";
    private static final String FRAGILITYTYPEINVALID = "w";

    @Test
    public void getFragilityFilterTest() {
        assertTrue(filter.getFragilityFilter(FRAGILITYTYPEVALID));
        assertFalse(filter.getFragilityFilter(FRAGILITYTYPEINVALID));
    }

    private static final String TEMPERATURECONTROLVALID_A = "-000";
    private static final String TEMPERATURECONTROLVALID_B = "+888";
    private static final String TEMPERATURECONTROLINVALID_A = "*444";
    private static final String TEMPERATURECONTROLINVALID_B = "+44";

    @Test
    public void getTemperatureFilterTest() {
    assertTrue(filter.getTemperatureFilter(TEMPERATURECONTROLVALID_A));
    assertTrue(filter.getTemperatureFilter(TEMPERATURECONTROLVALID_B));
    assertFalse(filter.getTemperatureFilter(TEMPERATURECONTROLINVALID_A));
    assertFalse(filter.getTemperatureFilter(TEMPERATURECONTROLINVALID_B));
    }

    private static final String CARGONAMEVALID_A = "123";
    private static final String CARGONAMEVALID_B = "123";
    private static final String CARGONAMEINVALID_A = "22";
    private static final String CARGONAMEINVALID_B = "22";

    @Test
    public void getCargoNameFilterTest() {
        assertTrue(filter.getCargoNameFilter(CARGONAMEVALID_A));
        assertTrue(filter.getCargoNameFilter(CARGONAMEVALID_B));
        assertFalse(filter.getCargoNameFilter(CARGONAMEINVALID_A));
        assertFalse(filter.getCargoNameFilter(CARGONAMEINVALID_B));
    }

    private static final String APPENDIXVALID_A = "1";
    private static final String APPENDIXVALID_B = "22";
    private static final String APPENDIXINVALID = "333";

    @Test
    public void getAppendixFilterTest() {
        assertTrue(filter.getAppendixFilter(APPENDIXVALID_A));
        assertTrue(filter.getAppendixFilter(APPENDIXVALID_B));
        assertFalse(filter.getAppendixFilter(APPENDIXINVALID));
    }

}