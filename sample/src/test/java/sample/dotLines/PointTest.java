package sample.dotLines;

import org.junit.Test;

import static org.junit.Assert.*;

    public class PointTest {
    private static final double TESTVAL_A = 0,
            TESTVAL_B = 90, TESTVAL_C = 180, TESTVAL_D = -90;

        private static final Point BASEPOINT = new Point(0, 0),
                POINT_RIGHT = new Point(1.0, 0), POINT_LEFT = new Point(-1.0, 0),
                POINT_TOP = new Point(0, 1.0), POINT_BOTTOM = new Point(0, -1.0);

    @Test
    public void getAngleTesting() {
           assertEquals(TESTVAL_A, BASEPOINT.getAngle(POINT_RIGHT), PointConnector.TOLERANCE);
           assertEquals(TESTVAL_B, BASEPOINT.getAngle(POINT_TOP), PointConnector.TOLERANCE);
           assertEquals(TESTVAL_C, BASEPOINT.getAngle(POINT_LEFT), PointConnector.TOLERANCE);
           assertEquals(TESTVAL_D, BASEPOINT.getAngle(POINT_BOTTOM), PointConnector.TOLERANCE);
    }

}