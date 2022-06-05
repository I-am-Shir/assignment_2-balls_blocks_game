import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the level information for level 4.
 */
public class Level4 implements LevelInformation {

    private List<Velocity> velocity;
    private Background4 backSprite;
    private List<Block> blocks;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Color color;

    /**
     * constructor.
     */
    public Level4() {
        this.velocity = new ArrayList<Velocity>();
        this.velocity.add(new Velocity(-5, -3));
        this.velocity.add(new Velocity(0, -5));
        this.velocity.add(new Velocity(4, -3));
        this.blocks = createRow(new Point(14, 100), 16, 783 / 16, 20, 1);
//        Block block = new Block(new Rectangle(new Point(390, 150), 20, 20, Color.RED));
//        this.blocks.add(block);
    }

    private Color getRandomColor() {
        Random rand = new Random();
        float[] hSBvalue = Color.RGBtoHSB(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), null);
        return Color.getHSBColor(hSBvalue[0], hSBvalue[1], hSBvalue[2]);
    }

    private ArrayList<Block> createRow(Point beginningLeft, int numberOfBlocks, int width, int height,
                                       int distance) {
        ArrayList<Block> row = new ArrayList<>();
        for (int i = 0; i < 7; i++) { //7 is number of rows
            this.color = getRandomColor();
            for (int j = 0; j < numberOfBlocks; j++) {
                Block added = new Block(
                        new Rectangle(new Point(beginningLeft.getX() + (width * j) + distance,
                                beginningLeft.getY() + (i * (height + distance))),
                                width,
                                height, this.color));
                row.add(added);
            }
        }
        return row;
    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
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
        return new String("Final Four");
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

    @Override
    public Color frameColor() {
        return Color.decode("#2D324E");
    }
}
