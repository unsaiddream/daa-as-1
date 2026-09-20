import java.util.concurrent.ThreadLocalRandom;

public class QuickSelect {

    public static int select(int[] arr, int k, Metrics metrics) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k is out of range");
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int pivotIndex = ThreadLocalRandom.current().nextInt(left, right + 1);

            int pivot = arr[pivotIndex];

            int lt = left;
            int i = left;
            int gt = right;

            while (i <= gt) {

                metrics.comparisons++;

                if (arr[i] < pivot) {

                    swap(arr, lt, i);
                    lt++;
                    i++;

                } else {

                    metrics.comparisons++;

                    if (arr[i] > pivot) {

                        swap(arr, i, gt);
                        gt--;

                    } else {

                        i++;
                    }
                }
            }

            if (k < lt) {

                right = lt - 1;

            } else if (k > gt) {

                left = gt + 1;

            } else {

                return arr[k];
            }
        }

        throw new IllegalStateException("QuickSelect failed");
    }


    private static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}