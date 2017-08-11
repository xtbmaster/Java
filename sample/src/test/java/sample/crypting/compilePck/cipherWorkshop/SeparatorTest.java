package sample.crypting.compilePck.cipherWorkshop;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;


import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.*;



import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)

public class SeparatorTest {


    private StringBuffer stringBuffer;
    private static final int DRIVERIDLENGTH = 4;
    private Separator separator = new Separator("TESTTEST");


    private static Object[] getTestVals() {
        return new Object[][]{{"Af00SrCJ"}, {"QQWEsRkf"}, {"JKCIRO"}, {"ZZZar"}, {"S88KKKRsciiio"}, {"10refe5S"}};
    }

    @Test
    @Parameters(method = "getTestVals")
    public void driverIdTest(String args) {

        final String VALIDOUTPUT = args.substring(0, 4);
        stringBuffer = new StringBuffer(args);
        assertEquals(VALIDOUTPUT, separator.getDriverId(stringBuffer));
        assertEquals(DRIVERIDLENGTH, separator.getDriverId(stringBuffer).length());
    }
    final static String[] RKEY = new String[]{"r", "R"};
    @Test
    @Parameters(method = "getTestVals")
    public void deletePrefixTest(String args) {

        stringBuffer = new StringBuffer(args);
        assertFalse(asList(RKEY).contains(separator.deletePrefix(stringBuffer)));
    }

    private static Object[] getValsWithHazardousType() {
        return new Object[][]{{"Ad00SrCJ"}, {"QdEsRkf"}};
    }

    private static Object[] getValsWithoutHazardousType() {
        return new Object[][]{{"A00SrCJ"}, {"QQEsRkf"}};
    }

    @Test
    @Parameters(method = "getValsWithHazardousType")
    public void hazardousTypeCheckTest_A(String args) {
        stringBuffer = new StringBuffer(args);
        assertEquals("true", separator.getHazardType(stringBuffer));
    }

    @Test
    @Parameters(method = "getValsWithoutHazardousType")
    public void hazardousTypeCheckTest_B(String args) {
        stringBuffer = new StringBuffer(args);
        assertEquals("false", separator.getHazardType(stringBuffer));
    }

    private static Object[] getValsWithFragilityType() {
        return new Object[][]{{"Adf0SrCJ"}, {"QfEsRkf"}};
    }

    private static Object[] getValsWithoutFragilityType() {
        return new Object[][]{{"Ad0SrCJ"}, {"QQEsRkf"}};
    }

    @Test
    @Parameters(method = "getValsWithFragilityType")
    public void fragilityTypeCheckTest_A(String args) {
        stringBuffer = new StringBuffer(args);
        assertEquals("true", separator.getFragilityType(stringBuffer));
    }

    @Test
    @Parameters(method = "getValsWithoutFragilityType")
    public void fragilityTypeCheckTest_B(String args) {
        stringBuffer = new StringBuffer(args);
        assertEquals("false", separator.getFragilityType(stringBuffer));
    }

    private static Object[] getValsWithTemperatureControl() {
        return new Object[][]{{"-001"}, {"+444"}};
    }

    private static Object[] getValsWithoutTemperatureControl() {
        return new Object[][]{{"0001"}, {"minus"}};
    }

    @Test
    @Parameters(method = "getValsWithTemperatureControl")
    public void temperatureControlCheckTest_A(String args) {
        stringBuffer = new StringBuffer(args);
        assertEquals(args, separator.getTemperature(stringBuffer));
    }

    @Test
    @Parameters(method = "getValsWithoutTemperatureControl")
    public void temperatureConrolCheckTest_B(String args) {
        stringBuffer = new StringBuffer(args);
        assertEquals("", separator.getTemperature(stringBuffer));
    }

    private static Object[] getCargoName() {
        return new Object[][]{{"143150145145163145"}, {"143150145145163145A"}, {"143150145145163145AA"}};
    }

    @Test
    @Parameters(method = "getCargoName")
    public void cargoNameTest(String args) {
        stringBuffer = new StringBuffer(args);
        String cryptedLine = separator.getCargoNameCipher(stringBuffer);
        assertTrue(cryptedLine.length() % 3 == 0);
        assertTrue(cryptedLine.matches("[0-9]+"));
    }
}