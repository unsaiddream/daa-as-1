import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static void sort(int[] arr, Metrics metrics) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        quickSort(arr, 0, arr.length - 1, metrics, 1);
    }


    private static void quickSort(
            int[] arr,
            int left,
            int right,
            Metrics metrics,
            int depth
    ) {

        while (left < right) {

            metrics.maxDepth = Math.max(metrics.maxDepth, depth);

            int pivotIndex =
                    ThreadLocalRandom.current().nextInt(left, right + 1);

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

            int leftSize = lt - left;
            int rightSize = right - gt;

            if (leftSize < rightSize) {

                if (left < lt - 1) {
                    quickSort(
                            arr,
                            left,
                            lt - 1,
                            metrics,
                            depth + 1
                    );
                }

                left = gt + 1;

            } else {

                if (gt + 1 < right) {
                    quickSort(
                            arr,
                            gt + 1,
                            right,
                            metrics,
                            depth + 1
                    );
                }

                right = lt - 1;
            }
        }
    }


    private static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}