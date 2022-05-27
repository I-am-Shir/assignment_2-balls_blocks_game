/**
 * creates a point.
 * made of two integers,x and a y.
 * checks distance between them and whether two are equal to each other.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x x in point
     * @param y y in point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * function calculating the distance of this point to the other point.
     *
     * @param other the other point.
     * @return the distance we found.
     */
    public double distance(Point other) {
        return Math.sqrt((x - other.getX()) * (x - other.getX())
                + (y - other.getY()) * (y - other.getY())); //calculating the distance.
    }

    /**
     * function to check if the points are equal.
     *
     * @param other the other point,
     * @return if their equal or not.
     */
    public boolean equals(Point other) {
        if (x == other.getX()) {  //compares two point by comparing x and y variables.
            if (y == other.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * the x value of this point.
     *
     * @return x value of this point.
     */
    //
    public double getX() {
        return x;
    }

    /**
     * the y value of this point.
     *
     * @return y value of this point.
     */
    //
    public double getY() {
        return y;
    }
}
