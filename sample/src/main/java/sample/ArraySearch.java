package sample;

import java.util.Arrays;

/**
 * The class manipulates two arrays and allows to test the binary-search
 * algorithm, that can be considered as the most convenient alternative,
 * with O(log N) complexity.
 */

    public class ArraySearch {

    /**
     * Creates a copy of the introduced array and sorts its elements in
     * ascending order.
     * @param unsortedArray array to be copied.
     * @return new sorted array with the same elements.
     */
    public static int[] copySort(int[] unsortedArray) {
            int[] secondArray = new int[unsortedArray.length];
            System.arraycopy(unsortedArray, 0, secondArray, 0, unsortedArray.length);
            Arrays.sort(secondArray);
            return secondArray;
    }

    /**
     * Searches the indicated element with a binary-search algorithm.
     * @implNote The index has a range of 1 <= x <= array length, which
     * means that the first element of an array can be found by
     * introducing 1 but not 0 as usual.
     *
     * @param searchId is an index that indicates the location of the
     *                 element in the unsorted array, that has to be found
     *                 in the second, sorted one.
     * @throws IllegalArgumentException if the introduced index is less
     * then 1, or if the array is empty.
     * @return the searched value.
     */
    public static int indexSearch(int[] unsortedArray, int searchId) throws IllegalArgumentException {
        if (searchId <= 0)  {throw new IllegalArgumentException();}
        if (unsortedArray.length == 0) {throw new IllegalArgumentException("Array can't be empty");}
        int[] secondArray = copySort(unsortedArray);

        int lowerBound = 0, upperBound = (unsortedArray.length - 1);
        final int theElement = unsortedArray[searchId - 1];
        int checkKey;

        while(true){
            checkKey = (lowerBound + upperBound) / 2;
            if (secondArray[checkKey] == theElement) {
                return secondArray[checkKey];
            }
            else {
                if (secondArray[checkKey] < theElement) {
                    lowerBound = checkKey + 1; // the element is in the upper half.
                }
                else {
                    upperBound = checkKey - 1; // the element is in the lower half.
                }
            }
        }
    }
}
