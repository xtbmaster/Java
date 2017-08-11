package sample.dotLines;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class provides a method to sort a set of points with x y coordinates
 * so that the line, which could be drawn through them, won't cross itself.
 * The idea of the algorithm is to find the lowest point on the axis and then
 * sort all the other points in accordance to the angle between them. The dots
 * then could be connected in the order of the growing value of the angle.
 */
public class PointConnector {

    private Point[] pointArray;
    private int nPoints;

    /**
     * Constructor which initializes main fields.
     * @param pointArray an arrays of points with x y coordinates.
     * @throws IllegalArgumentException if the list of point is too small to build a line.
     */
    public PointConnector(Point[] pointArray) throws IllegalArgumentException{
        if (pointArray.length < 2) {throw new IllegalArgumentException
                ("At least two points should be inserted.");}

        this.pointArray = pointArray;
        nPoints = pointArray.length;
    }

    public static final double TOLERANCE = 0.005;


    /**
     * Sorts a set of the introduced points.
     * @return a map of sorted points.
     */
    public Map <Double, Point> notCrossingConnect() {
        Map <Double, Point> dots;

        double lowPoint = pointArray[0].getY();
        int lowestPointIndex = 0;

        for (int i = 0; i < nPoints; i++) { // searching for the lowest point on the axis.
            if (pointArray[i].getY() < lowPoint) {
                lowPoint = pointArray[i].getY();
                lowestPointIndex = i;
            }
        }

        dots = new HashMap <>(nPoints);

        for (int i = 0; i < nPoints; i++) { // filling a map with angles as keys and point objects as values.
            double angle = pointArray[lowestPointIndex].getAngle(pointArray[i]);

    //  There is a chance that there will be two dots on the coordinate system,
    //  situated on the same line towards the base point. In this case the angle
    //  between them will be the same and Hashmap won't distinguish the points,
    //  replacing the first one with the same key-angle value. In order not to
    //  complicate the algorithm with a distance calculation between the
    //  dots, it was opted to make the next verification and correction:

            if (dots.containsKey(angle))
                angle += TOLERANCE;

            dots.put(angle, pointArray[i]); // fills up a Hashmap with values.
        }

        //  The method returns a TreeMap which allows to automatically sort
        //  key values. Passing a Hashmap as an argument allows to save time
        //  because the sorting is performed only once.

        return new TreeMap<>(dots);
    }

    /**
     * Displays point coordinates.
     * @param map a collection of coordinates.
     */
    public void display(Map <Double, Point> map) {
        int count = 0;
        for (Map.Entry<Double, Point> entry : map.entrySet()) {
            System.out.print("P {" + entry.getValue().getX() + ", " + entry.getValue().getY() + "}");
            if(++count != nPoints) System.out.print(" --->");
            else System.out.println("--> <LOOP>");
        }
    }
}

