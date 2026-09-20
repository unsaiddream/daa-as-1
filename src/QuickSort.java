import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static void sort(int[] arr, Metrics metrics){
        if (arr == null || arr.length <= 1) {
            return;
        }

        quickSort(arr, 0, arr.length - 1, metrics, 1);
        }
    }

    private static void quickSort(
            int[] arr,
            int left,
            int right,
            Metrics metrics,
            int depth
    ) {
    }

    private static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
