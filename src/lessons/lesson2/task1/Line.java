package lessons.lesson2.task1;

public class Line extends Shape {
    private Point a;
    private Point b;

    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    //    build segment for two points
    public double constructSegmentTwoPoints() {
        double result;
        double powResult = Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2);
        result = Math.sqrt(powResult);
        return result;
    }

    @Override
    public String toString() {
        return "Length of the line: " + (int) constructSegmentTwoPoints();
    }

    @Override
    public void draw() {
        System.out.println(this.toString());
    }
}
