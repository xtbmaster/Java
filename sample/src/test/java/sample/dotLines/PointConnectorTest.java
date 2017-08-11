package sample.dotLines;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.awt.geom.Line2D;
import java.lang.Double;
import java.util.Map;

import static org.junit.Assert.*;

    @RunWith(JUnitParamsRunner.class)

    public class PointConnectorTest {
        private static Object[] getInvalidArraySize() {
            return new Object[][]{
                    {new Point[0]}, {new Point[1]}};}

        private static Object[] getValidPoints() {
            return new Object[][]{
                    {new Point(1.0, 2.0), new Point(3.0, -9.4), new Point(-2.3, 7.2), new Point(4.4, -2.6),
                            new Point(4.4, -25.6), new Point(7.2, 5.4)},
                    {new Point(3.2, -12.4), new Point(-2.3, -7.1), new Point(-8.9, 2.9), new Point(-4.3, -11.6),
                            new Point(1.5, 7.4)}};
        }

        @Test(expected = IllegalArgumentException.class)
        @Parameters(method = "getInvalidArraySize")
        public void constructorShouldThrowIAEForAnEmptyArray(Point[] elem) {
            new PointConnector(elem);
        }

        @Test
        @Parameters(method = "getValidPoints")
        public void LinesShouldNotCross(Point[] elem){
            PointConnector lCon = new PointConnector(elem);
            Point[] array = new Point[elem.length];
            Map <Double, Point> sortedMap = lCon.notCrossingConnect();

            int count = 0;
            for (Map.Entry<Double, Point> entry : sortedMap.entrySet()) {
                array[count++] = entry.getValue();
            }
            for (int i = 0; i < array.length - 3; i++) {
                assertFalse(Line2D.linesIntersect(
                        array[i].getX(), array[i].getY(),
                        array[i+1].getX(), array[i+1].getY(),
                        array[i+2].getX(), array[i+2].getY(),
                        array[i+3].getX(), array[i+3].getY()
                        ));
            }
        }
    }
