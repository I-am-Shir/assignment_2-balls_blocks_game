import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * creating the game.
 */
public class Game implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private Counter blockCounter = new Counter();
    private Counter ballCounter = new Counter();
    private BlockRemover blockRemover = new BlockRemover(this, blockCounter);
    private BallRemover ballRemover = new BallRemover(this, ballCounter);
    private Counter score = new Counter();
    private ScoreTrackingListener scoreTracking = new ScoreTrackingListener(score);
    private ScoreIndicator scoreIndicator = new ScoreIndicator(score);
    private AnimationRunner runner = new AnimationRunner();
    private boolean running;

    /**
     * adding a collidable to the environment.
     *
     * @param c the collidable we'll add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }


    /**
     * adding a sprite to the environment.
     *
     * @param s the sprite we'll add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removing a collidable.
     * @param c the collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }
    /**
     * removing a sprite.
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    private Color getRandomColor() {
        Random rand = new Random();
        float[] hSBvalue = Color.RGBtoHSB(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), null);
        return Color.getHSBColor(hSBvalue[0], hSBvalue[1], hSBvalue[2]);
    }

    private ArrayList<Block> createRow(Point beginningLeft, int numberOfBlocks, int width, int height,
                                       int distance, Color color) {
        ArrayList<Block> row = new ArrayList<>();
        for (int i = 0; i < numberOfBlocks; i++) {
            Block added = new Block(
                    new Rectangle(new Point(beginningLeft.getX() + (width * i) + distance, beginningLeft.getY()), width,
                            height, color));
            added.addHitListener(blockRemover);
            added.addHitListener(scoreTracking);
            row.add(added);
            this.blockCounter.increase(1);
        }
        return row;
    }

    private ArrayList<Block> createWallStairs(Point upperLeft, int numberRows, int distance, int longestRow,
                                              int width, int height) {
        ArrayList<Block> wall = new ArrayList<>();
        for (int i = 0; i < numberRows; i++) {
            wall.addAll(createRow(new Point(upperLeft.getX() + (width * i), upperLeft.getY() + (height * i)
                            + distance * i), longestRow - i, width, height, distance,
                    getRandomColor()));
        }

        return wall;
    }

    private ArrayList<Block> frame(int widthSurface, int heightSurface, int size) {
        ArrayList<Block> frame = new ArrayList<>();
        frame.add(new Block(new Rectangle(new Point(0, 25), widthSurface, size, Color.CYAN))); //top.
        frame.add(new Block(new Rectangle(new Point(0, 25), size, heightSurface, Color.CYAN))); //left
        frame.add(
                new Block(new Rectangle(new Point(widthSurface - size, 25), size, heightSurface, Color.CYAN))); //right
        Block deathRegion = new Block(
                new Rectangle(new Point(0, heightSurface - 1), widthSurface, 1, Color.magenta)); //deathRegion.
        frame.add(deathRegion);
        deathRegion.addHitListener(ballRemover);
        return frame;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle),
     * and add them to the game.
     */
    public void initialize() {
        int width = 800;
        int height = 600;
        int size = width / 60;
        int blockHeight = height / 50;
        int paddleWidth = width / 10;
        int paddleHeight = height / 60;

        gui = new GUI("DESTROY!!!", width, height);
        Ball ball1 = new Ball(width / 2, (height / 3) * 2, size / 2, new Velocity(9, 3), Color.GREEN);
        ballCounter.increase(1);
        Ball ball2 = new Ball(width / 2, (height / 3) * 2, size / 2, new Velocity(-5, -5), Color.pink);
        ballCounter.increase(1);
        Ball ball3 = new Ball(width / 2, (height / 3) * 2, size / 2, new Velocity(10, -4), Color.red);
        ballCounter.increase(1);
        Paddle paddle = new Paddle(
                new Rectangle(new Point(width / 2 - paddleWidth / 2, height - size - paddleHeight - 2), paddleWidth,
                        paddleHeight, Color.magenta), gui);
        paddle.setLimits(size, width - size);
        ArrayList<Block> wall = createWallStairs(new Point(width - size - 12 * (width / 16) - 3, size + 100),
                height / 100, 1, height / 50, width / 16, blockHeight); //desired row? check
        ArrayList<Block> frame = frame(width, height, size);
        environment.addManyCollidable(frame);
        environment.addManyCollidable(wall);
        sprites.addManySprite(frame);
        sprites.addManySprite(wall);
        ball1.addToGame(this);
        ball1.setGameEnvironment(environment);
        ball2.addToGame(this);
        ball2.setGameEnvironment(environment);
        ball3.addToGame(this);
        ball3.setGameEnvironment(environment);
        paddle.addToGame(this);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.scoreIndicator.drawOn(d);
        gui.show(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
