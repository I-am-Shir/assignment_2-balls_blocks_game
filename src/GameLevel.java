import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * creating the game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTracking;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private int lives;
    private String levelGame;
    private LevelInformation levelInfo;

    public GameLevel (LevelInformation levelInfo,Counter score){
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter(levelInfo.blocks().size());
        ballCounter = new Counter(levelInfo.numberOfBalls());
        blockRemover = new BlockRemover(this, blockCounter);
        ballRemover = new BallRemover(this, ballCounter);
       this.score = score;
        scoreTracking = new ScoreTrackingListener(score);
        this.levelInfo = levelInfo;
        scoreIndicator = new ScoreIndicator(score,lives,this.levelInfo.levelName());
    }

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
        //    this.blockCounter.increase(1);
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

    private ArrayList<Block> frame(int widthSurface, int heightSurface, int size,Color color) {
        ArrayList<Block> frame = new ArrayList<>();
        frame.add(new Block(new Rectangle(new Point(0, 25), widthSurface, size, color))); //top.
        frame.add(new Block(new Rectangle(new Point(0, 25), size, heightSurface, color))); //left
        frame.add(
                new Block(new Rectangle(new Point(widthSurface - size, 25), size, heightSurface, color))); //right
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
        int paddleWidth = this.levelInfo.paddleWidth();
        int paddleHeight = height / 60;

        gui = new GUI("DESTROY!!!", width, height);
        this.runner = new AnimationRunner(60, gui);

        Paddle paddle = new Paddle(
                new Rectangle(new Point(width / 2 - paddleWidth / 2, height - size - paddleHeight - 2), levelInfo.paddleWidth(),
                        paddleHeight, Color.magenta), gui);
        paddle.setLimits(size, width - size);
//        ArrayList<Block> wall = createWallStairs(new Point(width - size - 12 * (width / 16) - 3, size + 100),
//                height / 100, 1, height / 50, width / 16, blockHeight); //desired row? check
        ArrayList<Block> frame = frame(width, height, size,this.levelInfo.frameColor());
        environment.addManyCollidable(frame);
        for (int i = 0; i < levelInfo.numberOfBlocksToRemove(); i++) {
            levelInfo.blocks().get(i).addToGame(this);
            levelInfo.blocks().get(i).addHitListener(this.blockRemover);
            levelInfo.blocks().get(i).addHitListener(this.scoreTracking);
        }


        environment.addManyCollidable(levelInfo.blocks());
        sprites.addSprite( this.levelInfo.getBackground());
        sprites.addManySprite(frame);
        sprites.addManySprite(levelInfo.blocks());

        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(width / 2, (height / 3) * 2, size / 2, levelInfo.initialBallVelocities().get(i), getRandomColor());
            ballCounter.increase(1);
            ball.addToGame(this);
            ball.setGameEnvironment(environment);
        }
        paddle.addToGame(this);
    }

    /**
     * game-specific logic
     *
     * @param d the draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        KeyboardSensor k = gui.getKeyboardSensor();
        // drawing.
        d = gui.getDrawSurface();
        this.sprites.drawAllOn(d);
        this.scoreIndicator.drawOn(d);
        this.sprites.notifyAllTimePassed();
        gui.show(d);
        if (k.isPressed("p")) {
            this.runner.run(new PauseScreen(this.gui,k));
        }
    }
    /**
     * returns if the game should stop,
     * @return true to stop false to continue.
     */
    @Override
    public boolean shouldStop() {
       // return !this.running;
        if (blockCounter.getValue() <= 0) {
            score.increase(100);
            gui.close();
            return true;
        }
        if (ballCounter.getValue() <= 0) {
            gui.close();
            return true;
        }
        return false;
    }

    public void run (){
      //  this.runner.run(new CountdownAnimation(2000,3,this.sprites)); // countdown before turn starts.
        //makes the running loop until the game is finished.
        this.running = true;
        this.runner.run(this);
        initialize();
    }
}
