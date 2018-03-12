package lessons.lesson1.task2;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] array1 = {3, 5, -3, 0, 6, -1, 5, 6, 2, 4, -9, 4, 7, 0, 7, 4, 2, -6, 9};
        int[] array2 = {5, 4, -3, 2, 1, -1, 0, -2, 3, -4, 5, 100, 22, -4};

        System.out.println("Before sort: " + Arrays.toString(array1));

        quickSort(array1, 0, array1.length - 1);

        System.out.println("After sort: " +  Arrays.toString(array1));

        System.out.println("Before SortSequence: " + Arrays.toString(array2));

        sortSequence(array2);

        System.out.println("Fgter SortSequence: " + Arrays.toString(array2));
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


    public static void sortSequence(int[] arr) {
        int len = arr.length - 1;
        int first = 0;

        for (int last = len; last > 0 && first != last; --last) {
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


    public static void swap(int[] items, int left, int right) {
        int temp = items[left];
        items[left] = items[right];
        items[right] = temp;
    }


}
