package lessons.lesson1.task3;

public class SortMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, -15, 6, 7, 8},
                {1, 2, 3, 4, -15, 6, 7, 8},
                {1, 2, 3, 4, -15, 6, 7, 8},
                {1, 2, 3, 4, -15, 6, 7, 8},
                {1, 2, 3, 4, -15, 6, 7, 8}
        };

        System.out.println("Before sortColumns: ");
        printMatrix(matrix);

        sortMatrixColumn(matrix);

        System.out.println("After sortColumns: ");
        printMatrix(matrix);
    }


    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                System.out.print(matrix[i][j] + " ");

            }
            System.out.println();
        }

    }
    public static void sortMatrixColumn(int[][] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[i].length - 1; ++j) {
                for (int k = 1; k < arr[i].length; ++k) {

                    if (getAvarage(arr, k - 1) < getAvarage(arr, k)) {
                        for (int l = 0; l < arr.length; ++l) {
                            int tmp = arr[l][k];
                            arr[l][k] = arr[l][k - 1];
                            arr[l][k - 1] = tmp;
                        }

                    }
                }
            }
        }
    }


    public static double getAvarage(int[][] arr, int item) {
        int avarage = 0;

        for (int i = 0; i < arr.length; ++i) {
            avarage += arr[i][item];
        }

        return (double) avarage / arr.length;
    }
}
