public class MergeSort {

    private static final int CUTOFF = 15;

    public static void sort(int[] arr, Metrics metrics) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        int[] temp = new int[arr.length];

        mergeSort(arr, temp, 0, arr.length - 1, metrics, 1);
    }

    private static void mergeSort(
            int[] arr,
            int[] temp,
            int left,
            int right,
            Metrics metrics,
            int depth
    ) {

        metrics.maxDepth = Math.max(metrics.maxDepth, depth);

        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(arr, temp, left, mid, metrics, depth + 1);

        mergeSort(arr, temp, mid + 1, right, metrics, depth + 1);

        merge(arr, temp, left, mid, right, metrics);
    }

    private static void insertionSort(
            int[] arr,
            int left,
            int right,
            Metrics metrics
    ) {

        for (int i = left + 1; i <= right; i++) {

            int key = arr[i];
            int j = i - 1;

            while (j >= left) {

                metrics.comparisons++;

                if (arr[j] <= key) {
                    break;
                }

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private static void merge(
            int[] arr,
            int[] temp,
            int left,
            int mid,
            int right,
            Metrics metrics
    ) {

        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {

            metrics.comparisons++;

            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }

            k++;
        }

        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }

        while (j <= right) {
            arr[k] = temp[j];
            j++;
            k++;
        }
    }
}