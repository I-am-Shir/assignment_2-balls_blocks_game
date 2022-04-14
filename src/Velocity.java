/**
 * dictates the velocity of the balls.
 * depending on speed and degree.
 */
public class Velocity {
    // Velocity specifies the change in position on the `x` and the `y` axes.
    private static final double TO_RAD = Math.PI / 180; //converts between radians and degrees.
    /**
     * vx variable.
     */
    private double vx;
    /**
     * vy variable.
     */
    private double vy;

    /**
     * methods for getting the value of velocity x.
     *
     * @return returns the x value.
     */
    public double getVx() {
        return this.vx;
    }

    /**
     * sets the x value for the velocity.
     *
     * @param vx the velocity x value we're setting.
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * methods for getting the value of velocity y.
     *
     * @return returns the y value.
     */
    public double getVy() {
        return this.vy;
    }

    /**
     * sets the y value for the velocity.
     *
     * @param vy the velocity y value we're setting.
     */
    public void setVy(double vy) {
        this.vy = vy;
    }

    /**
     * @param angle the degree in which the ball will move in the beginning.
     * @param speed the speed of the ball (velocity).
     * @return an object containing the velocity including the speed and angle.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(TO_RAD * angle);
        double dy = speed * Math.sin(TO_RAD * angle);
        return new Velocity(dx, dy);
    }

    /**
     * constructor
     * receives two doubles, representing x&y.
     *
     * @param dx x variable
     * @param dy y variable
     */

    public Velocity(double dx, double dy) {
        this.vx = dx;
        this.vy = dy;
    }

    /**
     * Take a point with position (x,y).
     *
     * @param p the received point.
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + vx, p.getY() + vy);
    }
}
