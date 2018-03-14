package lessons.lesson2.task1;


import java.util.Arrays;

public class Triangle extends Shape {
    private String type = "";
    private Point A;
    private Point B;
    private Point C;

    private Line lineAB;
    private Line lineBC;
    private Line lineCA;

    public Triangle(Point A, Point B, Point C) {
        if (allPointsLieDifferentPlanes(A, B, C)) {
            if (twoOrThreeVerticesNotOnePoint(A, B, C)) {
                this.A = A;
                this.B = B;
                this.C = C;

                lineAB = new Line(A, B);
                lineBC = new Line(B, C);
                lineCA = new Line(C, A);
                isRectangule();
            }
        } else {
            type = "Wrong coonrdinates ";
        }

    }


    public String getType() {
        return type;
    }


    // all points in different planes
    private boolean allPointsLieDifferentPlanes(Point a, Point b, Point c) {
        boolean result = false;
        if (a.getX() != b.getX() || b.getX() != c.getX()) {
            if (a.getY() != b.getY() || b.getY() != c.getY()) {
                result = true;
            }
        }
        return result;
    }

    //checking two or three vertex not in one point
    private boolean twoOrThreeVerticesNotOnePoint(Point a, Point b, Point c) {
        boolean result = false;
        if (!a.equals(b) && !a.equals(c)) {
            if (!b.equals(c)) {
                result = true;
            }
        }
        return result;
    }


    //checking rectangle
    public boolean isRectangule() {
        boolean flag = false;
        double a = lineAB.constructSegmentTwoPoints();
        double b = lineBC.constructSegmentTwoPoints();
        double c = lineCA.constructSegmentTwoPoints();
        double[] array = sortAscending(a, b, c);
        if (Math.round(Math.pow(array[2], 2)) == Math.round(Math.pow(array[1], 2) + Math.pow(array[0], 2))) {
            flag = true;
            type = "Rectangle ";
        }
        return flag;
    }


    private double[] sortAscending(double a, double b, double c) {
        double[] result = new double[3];
        result[0] = a;
        result[1] = b;
        result[2] = c;
        Arrays.sort(result);
        return result;
    }

    @Override
    public void draw() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return getType() + "Triangle";
    }
}
