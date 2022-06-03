import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * creating the game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AnimationRunner runner;
    //private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTracking;
    private ScoreIndicator scoreIndicator;
    private Paddle paddle;
    private List<Velocity> initialBallsVelocity;

    private boolean running;
    private Counter lives;
    private LevelInformation levelInfo;
    private KeyboardSensor ks;
    private int width;
    private int height;

    /**
     * @param levelInfo the specific level information.
     * @param ks        the keyboard sensor.
     * @param ar        the animation runner.
     * @param score     the players score till now.
     * @param lives     the amount of lives at the player disposal.
     * @param width     the width of the game surface.
     * @param height    the height of the game surface.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter score, Counter lives,
                     int width, int height) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(levelInfo.blocks().size());
        this.ballCounter = new Counter(levelInfo.numberOfBalls());
        this.blockRemover = new BlockRemover(this, blockCounter);
        this.ballRemover = new BallRemover(this, ballCounter);
        this.score = score;
        this.lives = lives;
        scoreTracking = new ScoreTrackingListener(score);
        this.levelInfo = levelInfo;
        scoreIndicator = new ScoreIndicator(score, this.lives, this.levelInfo.levelName());
        this.runner = ar;
        this.ks = ks;
        this.width = width;
        this.height = height;
        this.initialBallsVelocity = levelInfo.initialBallVelocities();
        this.running = false;
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
     *
     * @param c the collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removing a sprite.
     *
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

    private ArrayList<Block> frame(int widthSurface, int heightSurface, int size, Color color) {
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
        int size = width / 60;
        int blockHeight = height / 50;
        int paddleWidth = this.levelInfo.paddleWidth();
        int paddleHeight = height / 60;

        //GUI gui = new GUI("DESTROY!!!", 800, 600);
        this.paddle = new Paddle(
                new Rectangle(new Point(width / 2 - paddleWidth / 2, height - size - paddleHeight - 2),
                        levelInfo.paddleWidth(),
                        paddleHeight, Color.magenta), ks,this.width);
        paddle.setLimits(size, width - size);
        ArrayList<Block> frame = frame(width, height, size, this.levelInfo.frameColor());
        environment.addManyCollidable(frame);

        sprites.addSprite(this.levelInfo.getBackground());
        sprites.addManySprite(frame);

        for (int i = 0; i < levelInfo.numberOfBlocksToRemove(); i++) {
            levelInfo.blocks().get(i).addToGame(this);
            levelInfo.blocks().get(i).addHitListener(this.blockRemover);
            levelInfo.blocks().get(i).addHitListener(this.scoreTracking);
        }

        for (int i = 0; i < this.levelInfo.initialBallVelocities().size(); i++) {
            Ball ball = new Ball(width / 2, (height / 3) * 2, size / 2, levelInfo.initialBallVelocities().get(i),
                    getRandomColor());
            HitListener ballRemover = new BallRemover(this, this.ballCounter);
           // ballCounter.increase(1);
            ball.addToGame(this);
            ball.setGameEnvironment(environment);
        }
        paddle.addToGame(this);
    }

    /**
     * game-specific logic.
     *
     * @param d the draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // drawing.
        //d = this.gui.getDrawSurface();
        this.sprites.drawAllOn(d);
//        this.scoreIndicator.drawOn(d);
        this.sprites.notifyAllTimePassed();

    }

    /**
     * returns if the game should stop.
     *
     * @return true to stop false to continue.
     */
    @Override
    public boolean shouldStop() {
        if (wonLevel())
            running = false;
        if (lostLevel())
            running = false;
        return !this.running;
    }

    public boolean lostLevel(){
        return this.ballCounter.getValue() <= 0;
    }

    public int ballCount()
    {
        return ballCounter.getValue();
    }

    /**
     * returns if player won or not.
     *
     * @return true when there aren't any more blocks, meaning the level was cleared.
     */
    public boolean wonLevel() {
        return this.blockCounter.getValue() == 0;
        // return !this.running;
//        if (blockCounter.getValue() <= 0) {
//            this.wonLevel = true;
////            score.increase(100);
//            return true;
//        }
//        if (ballCounter.getValue() <= 0) {
//            this.lives.decrease(1);
//            return this.wonLevel;
//        }
//        return this.wonLevel;
    }

    /**
     * runs the level until the player loses or cleares the level.
     */
    public void run() {
        //  this.runner.run(new CountdownAnimation(2000,3,this.sprites)); // countdown before turn starts.
        //makes the running loop until the game is finished.
       // initialize();
        this.running = true;
        this.runner.run(this);

    }
}
