package sample;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)

public class ArraySearchTest {

    private final static int INVALIDLENGTH = 0;
    private final static int VALIDLENGTH = 10;
    private final static int VALIDINDEX = 3;

    private static Object[] getValidIndexes() {
        return new Object[][]{{1}, {5}, {7}, {11}, {25}, {47}};
    }
    private static Object[] getInvalidIndexes() {
        return new Object[][]{{-1}, {-5}, {-7}, {-11}, {-25}, {0}};
    }
    private static Object[] getArray() {
        return new int[][]{
                {21, 25, 78, 97, 51, 34, 2},
                {2, 6, 7, 8, 99, 8, 12, 35},
                {7, 88, 92, 14, 96, 34, 444, 812, 31}};
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getValidIndexes")
    public void methodShouldThrowIAEForEmptyArray(int index){
        ArraySearch.indexSearch(new int[INVALIDLENGTH], index);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidIndexes")
    public void methodShouldThrowIAEForInvalidIndexes(int index) {
        ArraySearch.indexSearch(new int[VALIDLENGTH], index);
    }

    @Test
    @Parameters(method = "getArray")
    public void methodShoudFindElement(int[] array) {
        int[] secondArray = ArraySearch.copySort(array);
        final int searchedIndex = Arrays.binarySearch(secondArray, array[VALIDINDEX - 1]);
        assertEquals(
                ArraySearch.indexSearch(array, VALIDINDEX), secondArray[searchedIndex]);
    }

    @Test
    @Parameters(method = "getArray")
    public void copySortMethodTest(int[] array) {

        int[] secondArray = new int[array.length];
        arrayCopy(array, secondArray);
        insertionSort(secondArray);
        assertArrayEquals(secondArray, ArraySearch.copySort(array));
    }

    public static void insertionSort(int[] array)
    {
        int in, out;
        int nElems = array.length;
        for(out = 1; out < nElems; out++){
            int temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp){
                array[in] = array[in - 1];
                --in;
            }
            array[in] = temp;
        }
    }

    public static void arrayCopy(int[] a, int[] b){
        System.arraycopy(a, 0, b, 0, a.length);
    }
}
