import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * animation of one black ball bouncing around the board with no one but its lone self.
 */
public class BouncingBallAnimation {
    /**
     * starting a ball from a destines place,
     * from there it keeps bouncing around inside the limits.
     *
     * @param args receives two integers which specify where to start from.
     */
    public static void main(String[] args) {
        Point start = new Point((double) Integer.parseInt(args[0]),
                (double) Integer.parseInt(args[1]));
        int dx = Integer.parseInt(args[2]);
        int dy = Integer.parseInt(args[3]);
        drawAnimation(start, dx, dy);
    }

    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30,new Velocity(dx, dy), java.awt.Color.BLACK);
        ball.setLimit(200, 200);
        while (true) {
            ball.timePassed();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
