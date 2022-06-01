import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelThree implements LevelInformation{
    private List<Velocity> velocity;
    private background3 backSprite;
    private List<Block> blocks;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;

    public LevelThree() {
        this.velocity = new ArrayList<Velocity>();
        this.velocity.add(new Velocity(-5, -5));
        this.velocity.add(new Velocity(5, -5));
        this.backSprite = new background3();
        this.blocks = createWallStairs(new Point(800 - 13 - 12 * (800 / 16) - 3, 13 + 100),
                600 / 100, 1, 600 / 50, 800 / 16, 600/50);
//        Block block = new Block(new Rectangle(new Point(390, 150), 20, 20, Color.RED));
//        this.blocks.add(block);
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

    public Color frameColor(){
        return Color.decode("#3430E8");
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocity;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return new String("Green 3");
    }

    @Override
    public Sprite getBackground() {
        return this.backSprite;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }
}
