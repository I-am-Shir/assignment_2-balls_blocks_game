import java.awt.Color;
import java.util.ArrayList;

public class Rectangle {

    private Line top;
    private Line leftSide;
    private Line rightSide;
    private Line bottom;
    private double width;
    private double height;
    private Point upperLeft;
    private Color color;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the top left point of the rectangle.
     * @param width     gives us the width of the rectangle.
     * @param height    gives us the height of the rectangle.
     *                  we created points for each corner of the rectangle.
     *                  then we created lines for the rectangle that connect these outer points.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        Point topRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point bottomRight = new Point(topRight.getX(), bottomLeft.getY());

        top = new Line(upperLeft, topRight);
        leftSide = new Line(upperLeft, bottomLeft);
        rightSide = new Line(topRight, bottomRight);
        bottom = new Line(bottomLeft, bottomRight);
        this.color=color;

        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }

    private boolean compareIntersectList(ArrayList<Point> list, Line lineFromRec, Line toCheck) {
        if (toCheck.isIntersecting(lineFromRec)) {
            if (list.isEmpty()) {
                list.add(toCheck.intersectionWith(lineFromRec));
                return false;
            }
            if (toCheck.intersectionWith(lineFromRec).equals(list.get(0))) {
                return false;
            }
            list.add(toCheck.intersectionWith(lineFromRec));
            return true;
        }
        return false;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> listOfIntersects = new ArrayList<Point>();  //the maximum times a line van intersect is twice.

        if (line.isIntersecting(this.top)) {
            listOfIntersects.add(line.intersectionWith(this.top));
        }
        if (compareIntersectList(listOfIntersects, line, this.leftSide)) {
            return listOfIntersects;
        }
        if (compareIntersectList(listOfIntersects, line, this.bottom)) {
            return listOfIntersects;
        }
        compareIntersectList(listOfIntersects, line, this.rightSide);
        return listOfIntersects;
    }


    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    public Color getColor() {
        return color;
    }
}
