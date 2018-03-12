package lessons.lesson1.task1;

public class PrintShapes {
    public static void main(String[] args) {
        printRhombus(11);

        System.out.println();

        printEquilateralTriangle(7);

        System.out.println();

        printRectangle(5, 10);

        System.out.println();

        printRightAngleTriangle(7);
    }

    public static void printRectangle(int m, int n) {
//              BY CONDITION
//        for (int i = 0; i < m; ++i) {
//            for (int j = 0; j < n; ++j) {
//                System.out.print("* ");
//            }
//            System.out.print("\n");
//
//        }


        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i == 1 || i == m || j == 1 || j == n) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.print("\n");
        }


    }

    public static void printRectangleWithDoWhile(int m, int n) {
        int i = 0;
        do {

            int j = 0;
            while (j < n) {
                System.out.print("* ");
                ++j;
            }
            ++i;

            System.out.print("\n");
        } while (i < m);
    }


    public static void printRightAngleTriangle(int m) {
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < m - i; ++j) {
                System.out.print("  ");
            }


            for (int k = i; k > 0; --k) {
                System.out.print("* ");
            }
            System.out.print("\n");
        }
    }

    public static void printEquilateralTriangle(int m) {
        for (int i = 0; i < m; ++i) {
            for (int j = 1; j < m - i; ++j) {
                System.out.print("  ");
            }

            for (int k = 0; k < 2 * i + 1; ++k) {
                System.out.print("* ");

            }

            System.out.println();
        }


    }


    public static void printRhombus(int m) {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                if (j + i == m / 2 || i + j == m - 1 + m / 2 || j == i + (m / 2) || i == j + (m / 2)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }



//              BY CONDITION
//        printEquilateralTriangle(m);
//
//        for (int i = m - 1; i >= 1; --i) {
//            for (int j = 0; j < m - i; ++j) {
//                System.out.print("  ");
//            }
//
//            for (int k = 0; k < 2 * i - 1; ++k) {
//                System.out.print("* ");
//            }
//            System.out.print("\n");
//        }
    }
}
