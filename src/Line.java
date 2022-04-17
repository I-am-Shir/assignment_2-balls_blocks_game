/**
 * creates the base for line segments.
 * including the check for their intersection and the retrieval of where,
 * their middle point,
 * if their equal.
 */
public class Line {
    private Point s;
    private Point e;

    // constructors

    /**
     * constructors for lines- start and end point of line.
     *
     * @param start - where the line begins.
     * @param end   - where the line ends.
     */
    public Line(Point start, Point end) {  //
        this.s = start;
        this.e = end;
    }

    /**
     * creating a line object.
     *
     * @param x1 x of one side.
     * @param y1 y of one side.
     * @param x2 x of other side.
     * @param y2 y of other side.
     */
    public Line(double x1, double y1, double x2, double y2) {
        s = new Point(x1, y1);  //creation of the start point.
        e = new Point(x2, y2);  //creation of the end point.
    }

    /**
     * finding the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return s.distance(e);
    }  //calculating length of the line by calculating distance between its two points.

    /**
     * finds the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point(((s.getX() + e.getX()) / 2), ((s.getY() + e.getY()) / 2));
    }  //returns the location of the middle of the line.

    /**
     * finds the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return s;
    }

    /**
     * finds the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return e;
    }

    /**
     * function checks if the lines intersect or not.
     *
     * @param other the line were comparing to.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) != null) { //checks if a point of intersection was found, returns true if it was.
            return true;
        }
        return false;
    }

    /**
     * function checking if the point is inside a line segment.
     * sees if a point is between two other points- x and y alike.
     *
     * @param s starting point of the line.
     * @param e end point of the line.
     * @param b middle point of the line.
     * @return if it is between or isn't.
     */
    public boolean checkBetween(Point s, Point e, Point b) {
        if (((s.getX() <= b.getX())  //checks for a case of s x variable being on the left of the point.
                && (b.getX() <= e.getX()))
                || ((s.getX() >= b.getX()) //checks for a case of s x variable being on the right of the point.
                && (b.getX() >= e.getX()))) {
            if (((s.getY() <= b.getY())  //checks for a case of s y variable being on the left of the point.
                    && (b.getY() <= e.getY()))
                    || ((s.getY() >= b.getY())  //checks for a case of s y variable being on the right of the point.
                    && (b.getY() >= e.getY()))) {
                return true;
            }
        }
        return false;
    }

    private Point parallelIntersect(Line line, double m, double c, Point s, Point e) {
        double x;
        double y;

        if (line.start().getX() == line.end().getX()) {
            if (line.start().getY() != line.end().getY()) {
                x = line.start().getX();
                y = m * x + c;

                Point intersect = new Point(x, y);  //creates a new point for the intersection.

                if (checkBetween(s, e, intersect)) {  // checks that the intersection is between the segment.
                    if (checkBetween(line.start(), line.end(), intersect)) {
                        return intersect;
                    }
                }
            }
        }
        return null;
    }

    /**
     * function finds the intersection point if the lines intersect,
     * and null otherwise.
     * we are using the determination technique.
     *
     * @param other the line were checking intersection with.
     * @return the intersection point if the lines intersect or not.
     */
    public Point intersectionWith(Line other) {
        double A1 = other.end().getY() - other.start().getY();
        double B1 = other.start().getX() - other.end().getX();
        double C1 = (A1*other.start().getX())+(B1* other.start().getY());
        double A2 = end().getY() - start().getY();
        double B2 = start().getX() - end().getX();
        double C2 = (A2*start().getX())+(B2* start().getY());

       double equation1 = A1*other.start().getX()+B1*other.start().getY();
       double equation2 = A2*start().getX()+B1*start().getY();
       double determinant = A2*B1-A1*B2;
       if(determinant == 0){
           //lines are parallel- no intersection in our case.
           return null;
       }
       double interX = (B1*C2-B2*C1)/determinant;
       double interY = (A2*C1-A1*C2)/determinant;
       Point intersect = new Point(interX,interY);
       if(checkBetween(start(),end(),intersect)) {
           if (checkBetween(other.start(), other.end(), intersect)) {
               return intersect;
           }
       }
       return null;
    }

    /**
     * function checks whether the lines are the same line.
     *
     * @param other the line were comparing to.
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.length() == other.length())) {  //compares length.
            if (this.middle().equals(other.middle())) {  //compares middle point.
                if ((s.equals(other.start())) || (s.equals(other.end()))) { //compares edges.
                    return true;
                }
            }
        }
        return false;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersect = rect.intersectionPoints(this);

        Point closest = null;
        for (Point point : intersect) {
            if (closest == null || s.distance(closest) > s.distance(point)) {
                closest = point;
            }
        }
        return closest;
    }
}
