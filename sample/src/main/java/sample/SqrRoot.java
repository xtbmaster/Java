package sample;

import static java.lang.Double.NaN;
import static java.lang.Math.*;

/**
 * The class contains a method to calculate a square root of
 * an introduced value without using standard sqrt(n) or pow(n, 0.5)
 * methods. The calculation is based on Newton's method of successive
 * approximations. The idea is to approximate a guess-inaccurate y for
 * the value of the square of x, until a more precise value is reached.
 */

     class SqrRoot {

        private static int number; // an introduced number.

    /**
     * Returns the correctly rounded positive square root of a value.
     *
     * @param num the initial introduced value.
     * @return positive square root.
    */
    public static double sqrRoot(int num) {
        number = num;
        return meth();
    }

    private static double guess = 1.0; // supposed initial guess value.
    private static double tolerance = 0.001; // default predetermined tolerance.

    /**
     * There is a possibility to manually insert the desirable tolerance value
     * as a the second argument. Nevertheless, taking into account the fact
     * that the answer should be rounded to the nearest floor-int, such
     * overloading does not lead to any visible changes.
     *
     * @param num the initial introduced value.
     * @param toleranceVal a tolerance value.
     * @return positive square root.
     */
    public static double sqrRoot(int num, double toleranceVal){
        number = num;
        if (toleranceVal > 0) { tolerance = toleranceVal; }
        return meth();
    }

    /**
     * The main method.
     * @return positive square root, or NaN if any of the arguments is illegal.
     */
    private static double meth() {
        if (number < 0) { return NaN; }

        if (isEnough()) { return floor(guess); }
        else {
            guess = improved();
            return meth();
        }
    }

    /**
     * Examines if the square of the guessing value is close enough to
     * the radicand (by less than a tolerance that has been established).
     *
     * @return true if the square value of the answer is close enough
     * and false if the value needs to be improved.
     */
    private static boolean isEnough(){
        final double tempCheck = pow (guess, 2.0);
        return (abs(tempCheck - number) <= tolerance);
    }

    /**
     * Computes the improved guess value by averaging it with the
     * old-guess value and the quotient of the radicand.
     *
     * @return the improved guess value.
     */
    private static double improved(){
        final double quotient = number / guess;
        return average(guess, quotient);
    }

    /**
     * Computes the average value between the first and the second argument.
     *
     * @param x the first argument.
     * @param y the second argument.
     * @return the average value between the first and the second argument.
     */
    public static double average (double x, double y){
        return (x + y) / 2;
    }
}
