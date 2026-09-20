import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void emptyArray() {
        int[] arr = {};

        QuickSort.sort(arr, new Metrics());

        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void oneElement() {
        int[] arr = {5};

        QuickSort.sort(arr, new Metrics());

        assertArrayEquals(new int[]{5}, arr);
    }

    @Test
    void allElementsEqual() {
        int[] arr = {7, 7, 7, 7, 7};

        QuickSort.sort(arr, new Metrics());

        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, arr);
    }

    @Test
    void alreadySorted() {
        int[] arr = {1, 2, 3, 4, 5, 6};

        QuickSort.sort(arr, new Metrics());

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, arr);
    }

    @Test
    void randomArrays() {
        Random random = new Random(42);

        for (int t = 0; t < 100; t++) {
            int size = random.nextInt(1000);
            int[] arr = new int[size];

            for (int i = 0; i < size; i++) {
                arr[i] = random.nextInt();
            }

            int[] expected = arr.clone();
            Arrays.sort(expected);

            QuickSort.sort(arr, new Metrics());

            assertArrayEquals(expected, arr);
        }
    }

    @Test
    void recursionDepth() {
        int n = 100_000;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);

        double maxAllowedDepth = 2 * (Math.log(n) / Math.log(2));

        assertTrue(metrics.maxDepth <= maxAllowedDepth);
    }
}