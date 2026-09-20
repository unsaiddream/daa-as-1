public class MergeSort {

    private static final int Cutoff = 15;
    public static void sort(int[] arr){

        if (arr == null || arr.length <= 1) return;

        int[] temp = new int[arr.length];

        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSort(
            int[] arr,
            int[] temp,
            int left,
            int right
    ){
        if (right - left + 1 <= Cutoff){
            insertionSort(arr, left, right);
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(arr, temp, left, mid);

        mergeSort(arr, temp, mid + 1, right);

        merge(arr, temp, left, mid, right);
    }

    private static void insertionSort(int[] arr, int left, int right){

        for(int i = left + 1; i <= right; i++){

            int key = arr[i];
            int j = i - 1;

            while (i >= left && arr[j] > key){
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right){
        for (int i = left; i <= right; i++){
            temp[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right){
            if (temp[i] <= temp[i]){
                arr[k] = temp[i];
                i++;
            }

            else {
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
