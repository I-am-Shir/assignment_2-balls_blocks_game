import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * creats multiple balls that jump around the same surface limits.
 * multi colored balls,
 * in a width of 500 and height of 300.
 */
public class MultipleBouncingBallsAnimation {

    private static Color[] colors =
            {Color.RED, Color.BLACK, Color.CYAN, Color.magenta, Color.PINK, Color.DARK_GRAY, Color.yellow, Color.GREEN,
                    Color.ORANGE, Color.BLUE}; //colors for the balls.

    private void printBall(Ball ball, DrawSurface d) {
        ball.timePassed();
        ball.drawOn(d);
    }

    private void printBalls(Ball[] balls, DrawSurface d) {
        for (int i = 0; i < balls.length; i++) {
            printBall(balls[i], d);
        }
    }

    private void runSurface(Ball[] balls, GUI gui) {
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            printBalls(balls, d);
            gui.show(d);
            sleeper.sleepFor(50);
        }

    }

    /**
     * a main creating all the balls and controlling their speed in a way fitting their size.
     *
     * @param args the string received from command' detailing how many balls and of what size.
     */
    public static void main(String[] args) {
        int width = 500;
        int height = 300;
        GUI gui = new GUI("run", width, height);
        Random rand = new Random();
        Ball[] juggle = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            int size = Integer.parseInt(args[i]);
            juggle[i] = new Ball(rand.nextInt(size < width ? width - size : width - 1)
                    + size % width, rand.nextInt(width - (size < height ? size : 1)) + size % height, size,
                    new Velocity(1, 1),
                    colors[rand.nextInt(colors.length)]);
            juggle[i].setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), (50 - size > 0 ? 50 - size : 1)));
            juggle[i].setLimit(width, height);
        }
        new MultipleBouncingBallsAnimation().runSurface(juggle, gui);
    }

}
