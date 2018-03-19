package lessons.lesson1.task4;

import java.util.ArrayList;

import static lessons.lesson1.task3.SortMatrix.printMatrix;

public class PrintMatrixBySpiral {
    public static void main(String[] args) {

        int[][] matrix = {
                {9, 9, 9, 9, 9, 9, 8},
                {6, 5, 5, 5, 5, 5, 8},
                {6, 2, 3, 0, 2, 6, 8},
                {6, 2, 3, 3, 3, 3, 8},
                {7, 7, 7, 7, 7, 7, 8}
        };
        System.out.println("Print matrix: ");
        printMatrix(matrix);


        ArrayList<Integer> spiral = printSpiral(matrix);
        System.out.println(spiral);
    }

    public static ArrayList<Integer> printSpiral(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }
        int a = 0, b = matrix.length - 1;
        int c = 0, d = matrix[0].length - 1;

        while (a <= b && c <= d) {
            for (int i = c; i <= d; i++) {
                result.add(matrix[a][i]);
            }
            a++;
            if (a > b) {
                break;
            }

            for (int i = a; i <= b; i++) {
                result.add(matrix[i][d]);
            }
            d--;
            if (d < c) {
                break;
            }
            for (int i = d; i >= c; i--) {
                result.add(matrix[b][i]);
            }
            b--;
            if (b < a) {
                break;
            }
            for (int i = b; i >= a; i--) {
                result.add(matrix[i][c]);
            }
            c++;
            if (c > d) {
                break;
            }
        }
        return result;
    }
}
