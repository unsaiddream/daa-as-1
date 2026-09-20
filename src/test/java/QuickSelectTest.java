import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {

    @Test
    void smallestElement() {
        int[] arr = {5, 2, 8, 1, 9};

        int result = QuickSelect.select(arr, 0, new Metrics());

        assertEquals(1, result);
    }

    @Test
    void middleElement() {
        int[] arr = {5, 2, 8, 1, 9};

        int result = QuickSelect.select(arr, 2, new Metrics());

        assertEquals(5, result);
    }

    @Test
    void largestElement() {
        int[] arr = {5, 2, 8, 1, 9};

        int result = QuickSelect.select(arr, 4, new Metrics());

        assertEquals(9, result);
    }

    @Test
    void duplicates() {
        int[] arr = {5, 2, 2, 8, 2, 9};

        int result = QuickSelect.select(arr, 2, new Metrics());

        assertEquals(2, result);
    }

    @Test
    void emptyArray() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{}, 0, new Metrics())
        );
    }

    @Test
    void negativeK() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, -1, new Metrics())
        );
    }

    @Test
    void kOutOfRange() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, 3, new Metrics())
        );
    }

    @Test
    void randomArrays() {
        Random random = new Random(42);

        for (int t = 0; t < 100; t++) {
            int size = random.nextInt(1000) + 1;
            int[] arr = new int[size];

            for (int i = 0; i < size; i++) {
                arr[i] = random.nextInt();
            }

            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(size);
            int result = QuickSelect.select(arr, k, new Metrics());

            assertEquals(sorted[k], result);
        }
    }
}