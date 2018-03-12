package lessons.lesson1.task2;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] array = new int[]{3, 5, -3, 0, 6, -1, 5, 6, 2, 4, -9, 4, 7, 0, 7, 4, 0, 2, -6, 9};
        int[] array1 = new int[]{3, 5, -3, 0, 6, -1, 5, 6, 2, 4};

        System.out.println("Before sort: " + Arrays.toString(array));

        quickSort(array, 0, array.length - 1);

        System.out.println("After sort: " +  Arrays.toString(array));
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
