import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    public void addCollidable(Collidable c) {
        addCollidable(c);
    }

    public void addSprite(Sprite s) {
        addSprite(s);
    }

    private ArrayList<Block> createRow(Point beginningLeft, int numberOfBlocks, int width, int height,
                                       int distance) {
        ArrayList<Block> row = new ArrayList<Block>();
        for (int i = 0; i < numberOfBlocks; i++) {
            row.add(new Block(
                    new Rectangle(new Point(beginningLeft.getX() + (width * i) + distance, beginningLeft.getY()), width,
                            height)));
        }
        return row;
    }

    private ArrayList<Block> createWallStairs(Point upperLeft, int numberRows, int distance, int longestRow,
                                              int width, int height) {
        ArrayList<Block> wall = new ArrayList<Block>();
        for (int i = 0; i < numberRows; i++) {
            wall.addAll(createRow(new Point(upperLeft.getX() + (width * i)
                    , upperLeft.getY() + (height * i) + distance), longestRow - i, width, height, distance));
        }
        return wall;
    }

    private ArrayList<Block> Frame(int widthSurface, int heightSurface, int size) {
        ArrayList<Block> frame = new ArrayList<Block>();
        frame.add(new Block(new Rectangle(new Point(0, 0), widthSurface, size))); //top.
        frame.add(new Block(new Rectangle(new Point(0, heightSurface - size), widthSurface, size))); //bottom.
        frame.add(new Block(new Rectangle(new Point(0, 0), size, heightSurface))); //left
        frame.add(new Block(new Rectangle(new Point(widthSurface - size, 0), size, heightSurface))); //right
        return frame;
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public GUI initialize() {
        int width = 800;
        int height = 600;
        int size = 10;
        gui = new GUI("DESTROY!!!", width, height);
        Ball ball = new Ball(400, 480, size, Color.GREEN);
        ArrayList<Block> wall = createWallStairs(new Point(width - size - 12 * 40, size + 100), 6, 1, 12, 40, 10);
        ArrayList<Block> frame = Frame(width, height, size);
        environment.addManyCollidable(frame);
        environment.addManyCollidable(wall);
        //environment.addManyCollidable(paddle); //add
        sprites.addManySprite(frame);
        sprites.addManySprite(wall);
        sprites.addSprite(ball);
        //sprites.addSprite(paddle); //add
        return gui;
    }

    // Run the game -- start the animation loop.
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            Sleeper sleeper = new Sleeper();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
