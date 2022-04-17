import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * creats multiple balls that jump around two different surfaces.
 * multi colored balls.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static Color[] colors =
            {Color.RED, Color.BLACK, Color.CYAN, Color.magenta, Color.PINK, Color.DARK_GRAY, Color.WHITE, Color.GREEN,
                    Color.ORANGE, Color.BLUE};

    private void printBall(Ball ball, DrawSurface d) {
        ball.timePassed();
        ball.drawOn(d);
    }

    private void printBalls(Ball[] balls, DrawSurface d) {
        for (int i = 0; i < balls.length; i++) {
            printBall(balls[i], d);
        }
    }

    private void printRectangle(DrawSurface d, Point start, int width, int height, Color color) {
        d.setColor(color);
        d.fillRectangle((int) start.getX(), (int) start.getY(), width, height);
    }

    private void runSurface(Ball[] balls, GUI gui) {
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            printRectangle(d, new Point(50, 50), 450, 450, Color.gray);
            printRectangle(d, new Point(450, 450), 150, 150, Color.yellow);
            printBalls(balls, d);
            gui.show(d);
            sleeper.sleepFor(50);
        }

    }

    /**
     * a main creating all the balls and controlling their speed in a way fitting their size.
     * splitting them into two different surfaces.
     *
     * @param args the string received from command' detailing how many balls and of what size.
     */
    public static void main(String[] args) {
        int width = 700;
        int height = 600;
        int i = 0;
        GUI gui = new GUI("run", width, height);
        Random rand = new Random();
        Ball[] juggle = new Ball[args.length];
        for (; i < args.length / 2; i++) {
            int size = Integer.parseInt(args[i]);
            juggle[i] =
                    new Ball(rand.nextInt(500 - 50 - size) + 50 + size, rand.nextInt(500 - 50 - size) + 50 + size, size,
                            colors[rand.nextInt(colors.length)]);
            juggle[i].setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), (50 - size > 0 ? 50 - size : 1)));
            juggle[i].setLimit(500, 500, 50, 50);
        }
        for (; i < args.length; i++) {
            int size = Integer.parseInt(args[i]);
            juggle[i] = new Ball(rand.nextInt(600 - 450 - size) + 450
                    + size, rand.nextInt(600 - 450 - size) + 450 + size, size,
                    colors[rand.nextInt(colors.length)]);
            juggle[i].setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), (50 - size > 0 ? 50 - size : 1)));
            juggle[i].setLimit(600, 600, 450, 450);
        }
        new MultipleFramesBouncingBallsAnimation().runSurface(juggle, gui);
    }
}
