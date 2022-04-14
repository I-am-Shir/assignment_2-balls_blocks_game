import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class mainTesting {
    private void printBall(Ball ball, DrawSurface d) {
        ball.moveOneStep();
        ball.drawOn(d);
    }

    private void printBalls(Ball[] balls, DrawSurface d) {
        for (int i = 0; i < 1; i++) {
            printBall(balls[i], d);
        }
    }
    private void printCollidables(Collidable[] collidables, DrawSurface d) {
        for (int i = 0; i < collidables.length; i++) {
            printRectangle(d,
                    collidables[i].getCollisionRectangle().getUpperLeft(),
                    (int)collidables[i].getCollisionRectangle().getWidth(),
                    (int)collidables[i].getCollisionRectangle().getHeight(),
                    Color.CYAN
            );
        }
    }

    private void runSurface(Ball[] balls,  Collidable[] rect,GUI gui, GameEnvironment gameEnvironment) {
        Sleeper sleeper = new Sleeper();



        while (true) {
            DrawSurface d = gui.getDrawSurface();
            printCollidables(rect, d);
            printBalls(balls, d);
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }

    private void printRectangle(DrawSurface d, Point start, int width, int height, Color color) {
        d.setColor(color);
        d.fillRectangle((int) start.getX(), (int) start.getY(), width, height);
    }

    public static void main(String[] args) {
        int width = 700;
        int height = 700;
        int size = 10;
        GUI gui = new GUI("run", width, height);
        Random rand = new Random();
        Ball bouncer = new Ball(
                700/2,//rand.nextInt(size < width ? width - size : width - 1) + size % width,
                700/2,// rand.nextInt(width - (size < height ? size : 1)) + size % height,
                size,
                Color.RED);
        Ball bouncer2 = new Ball(
                700/2,//rand.nextInt(size < width ? width - size : width - 1) + size % width,
                700/2,// rand.nextInt(width - (size < height ? size : 1)) + size % height,
                size,
                Color.ORANGE);
        Ball bouncer3 = new Ball(
                700/2,//rand.nextInt(size < width ? width - size : width - 1) + size % width,
                700/2,// rand.nextInt(width - (size < height ? size : 1)) + size % height,
                size,
                Color.green);
        Ball bouncer4 = new Ball(
                700/2,//rand.nextInt(size < width ? width - size : width - 1) + size % width,
                700/2,// rand.nextInt(width - (size < height ? size : 1)) + size % height,
                size,
                Color.gray);
        GameEnvironment gameEnvironment = new GameEnvironment();
        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(0, 0), width, 50))); //top
        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(width - 50, 0), 50, height)));//right
        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(50, height - 50), width, 50)));//bottom
        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(0, 0), 50, height)));//left
        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(300, 300), 200, 200)));//rec


        bouncer.setVelocity(30/*rand.nextInt(10)*/, 40/*rand.nextInt(10)*/);
        bouncer2.setVelocity(rand.nextInt(5), rand.nextInt(50));
        bouncer3.setVelocity(rand.nextInt(15), rand.nextInt(10));
        bouncer4.setVelocity(rand.nextInt(10), rand.nextInt(10)-20);
        //bouncer.setVelocity(2, 6);

        bouncer.setGameEnvironment(gameEnvironment);
        bouncer2.setGameEnvironment(gameEnvironment);
        bouncer3.setGameEnvironment(gameEnvironment);
        bouncer4.setGameEnvironment(gameEnvironment);
        Collidable[] collidables = gameEnvironment.getGameEnviro();
                /*{
                new Block(new Rectangle(new Point(width - 100, 0), 100, height)),
                new Block(new Rectangle(new Point(0, 0), width, 100)),
                new Block(new Rectangle(new Point(0, height - 100), width, 100)),
                new Block(new Rectangle(new Point(0, 0), 100, height))
        };*/
        Ball[] array = new Ball[4];
        array[0] = bouncer;
//        array[1]=bouncer2;
//        array[2]=bouncer3;
//        array[3]=bouncer4;
        new mainTesting().runSurface(array, collidables, gui, gameEnvironment);
        System.out.println(Arrays.stream(gameEnvironment.getGameEnviro()));



        /*System.out.println(gameEnvironment.getClosestCollision(new Line(new Point (105,105), new Point (1,1))).collisionPoint().getX());
        System.out.println(gameEnvironment.getClosestCollision(new Line(new Point (105,105), new Point (1,1))).collisionPoint().getY());*/
    }
}
