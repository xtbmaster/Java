package sample;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;
import org.junit.Test;

import static java.lang.Math.sqrt;
import static org.junit.Assert.*;

    @RunWith(JUnitParamsRunner.class)

    public class SqrRootTest {

        private final static double DEFAULTTOLERANCE = 0.001;

        private static Object[] getTestingValues(){ return new Object[][]{{9}, {25}, {169}, {4}, {484}};}

        @Test
        @Parameters(method = "getTestingValues")
        public void expectedSqrtValue(int number){
           assertEquals(SqrRoot.sqrRoot(number), sqrt(number), DEFAULTTOLERANCE);
        }
}