import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;

    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    private Color getRandomColor(){
        Random rand = new Random();
       float[] HSBvalue = Color.RGBtoHSB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255),null);
        return Color.getHSBColor(HSBvalue[0],HSBvalue[1],HSBvalue[2]);
    }

    private ArrayList<Block> createRow(Point beginningLeft, int numberOfBlocks, int width, int height,
                                       int distance, Color color) {
        ArrayList<Block> row = new ArrayList<Block>();
        for (int i = 0; i < numberOfBlocks; i++) {
            row.add(new Block(
                    new Rectangle(new Point(beginningLeft.getX() + (width * i) + distance, beginningLeft.getY()), width,
                            height,color)));
        }
        return row;
    }

    private ArrayList<Block> createWallStairs(Point upperLeft, int numberRows, int distance, int longestRow,
                                              int width, int height) {
        ArrayList<Block> wall = new ArrayList<Block>();
        for (int i = 0; i < numberRows; i++) {
            wall.addAll(createRow(new Point(upperLeft.getX() + (width * i)
                    , upperLeft.getY() + (height * i) + distance*i), longestRow - i, width, height, distance, getRandomColor()));
        }
        return wall;
    }

    private ArrayList<Block> Frame(int widthSurface, int heightSurface, int size) {
        ArrayList<Block> frame = new ArrayList<Block>();
        frame.add(new Block(new Rectangle(new Point(0, 0), widthSurface, size,Color.CYAN))); //top.
        frame.add(new Block(new Rectangle(new Point(0, heightSurface - size), widthSurface, size,Color.CYAN))); //bottom.
        frame.add(new Block(new Rectangle(new Point(0, 0), size, heightSurface,Color.CYAN))); //left
        frame.add(new Block(new Rectangle(new Point(widthSurface - size, 0), size, heightSurface,Color.CYAN))); //right
        return frame;
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        int width = 800;
        int height = 600;
        int size = width/60;
        int blockHeight = height/50;
        int paddleWidth = width/12;
        int paddleHeight = height/40;

        gui = new GUI("DESTROY!!!", width, height);
        Ball ball1 = new Ball(width/2, (height/3)*2, size/3,new Velocity(5,5), Color.GREEN);
        Ball ball2 = new Ball(width/2, (height/3)*2, size/3,new Velocity(7,7), Color.red);
        Paddle paddle = new Paddle(new Rectangle(new Point(width/2-paddleWidth/2,height-size-paddleHeight-2),paddleWidth,paddleHeight,Color.magenta),gui);
        paddle.setLimits(size,width-size);
        ArrayList<Block> wall = createWallStairs(new Point(width - size - 12 * (width/16) - 3, size + 100)
                , height/100, 1, height/50, width/16, blockHeight); //desired row? check
        ArrayList<Block> frame = Frame(width, height, size);
        environment.addManyCollidable(frame);
        environment.addManyCollidable(wall);
        sprites.addManySprite(frame);
        sprites.addManySprite(wall);
        ball1.addToGame(this);
        ball1.setGameEnvironment(environment);
        ball2.addToGame(this);
        ball2.setGameEnvironment(environment);
        paddle.addToGame(this);
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
