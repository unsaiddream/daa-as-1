import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void emptyArray() {
        int[] arr = {};

        MergeSort.sort(arr, new Metrics());

        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void oneElement() {
        int[] arr = {5};

        MergeSort.sort(arr, new Metrics());

        assertArrayEquals(new int[]{5}, arr);
    }

    @Test
    void allEqual() {
        int[] arr = {7, 7, 7, 7, 7};

        MergeSort.sort(arr, new Metrics());

        assertArrayEquals(
                new int[]{7, 7, 7, 7, 7},
                arr
        );
    }

    @Test
    void alreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};

        MergeSort.sort(arr, new Metrics());

        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5},
                arr
        );
    }

    @Test
    void compareWithArraysSortOn100RandomArrays() {

        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {

            int size = random.nextInt(1000);
            int[] actual = new int[size];

            for (int i = 0; i < size; i++) {
                actual[i] = random.nextInt();
            }

            int[] expected = actual.clone();

            Arrays.sort(expected);

            MergeSort.sort(actual, new Metrics());

            assertArrayEquals(expected, actual);
        }
    }
}