package lessons.lesson1.task2;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] array1 = {3, 5, -3, 0, 6, -1, 53, 61, 2, 49, -9, 4, 7, 0, 77, 41, 202, -6, 9};
        int[] array2 = {52, 76, -3, 2, 15, -1, 0, -27, 3, -4, 5, 105, 22, -42};

        System.out.println("Before sort: " + Arrays.toString(array1));

        bubbleSort(array1);

        System.out.println("After sort: " + Arrays.toString(array1));

        System.out.println("\n");

        System.out.println("Before SortSequence: " + Arrays.toString(array2));

        sortSequence(array2);

        System.out.println("Fgter SortSequence: " + Arrays.toString(array2));
    }

    /**
     * By Condition
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] < arr[j - 1] && (arr[j] < 0 || arr[j - 1] < 0)) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                } else if (arr[j] > arr[j - 1] && (arr[j] >= 0 && arr[j - 1] >= 0)) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }


    /**
     * By Condition
     */
    public static void sortSequence(int[] arr) {
        int len = arr.length - 1;
        int first = 0;

        for (int last = len; last > 0 && first < last; --last) {
            if (arr[last] > 0) {
                while (first < len) {
                    if (arr[first] < 0) {
                        swap(arr, last, first);
                        ++first;
                        break;
                    } else {
                        ++first;
                    }

                }
            }
        }
    }

    /**
     * Util method
     */
    public static void swap(int[] items, int left, int right) {
        int temp = items[left];
        items[left] = items[right];
        items[right] = temp;
    }



    private static void quickSort(int[] arr, int low, int high) {
        if (arr.length <= 0) return;
        if (low >= high) return;
        int left = low;
        int right = high;

        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= temp) {
                right--;
            }

            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {
                left++;
            }

            arr[right] = arr[left];
        }

        arr[left] = temp;
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);

    }

}
