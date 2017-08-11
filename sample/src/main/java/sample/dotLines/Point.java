package sample.dotLines;

import static java.lang.Math.atan2;
import static java.lang.Math.toDegrees;

    /**
     * The class allows to create a point on a coordinate system.
     * It is also provides a method of calculating an angle between two points.
     */
    public class Point {
        private double x, y, z;

        /**
         * Constructor to work with 2D coordinate system.
         * @param x axis.
         * @param y axis.
         */
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Constructor to work with 3D coordinate system.
         * @param x axis.
         * @param y axis.
         * @param z axis.
         */
        public Point(double x, double y, double z) {
            this(x, y);
            this.z = z;
        }

        public double getX() { return x; }
        public double getY() { return y; }
        public double getZ() { return z; }

        /**
         * Calculates an angle between two points.
         * @param b is a second point.
         * @return angle between two points.
         */
        public double getAngle(Point b) {
            final double yDiff = b.getY() - y;
            final double xDiff = b.getX() - x;
            return toDegrees(atan2(yDiff, xDiff));
        }
    }

