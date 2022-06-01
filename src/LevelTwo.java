import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelTwo implements LevelInformation{
    private List<Velocity> velocity;
    private background2 backSprite;
    private List<Block> blocks;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;


    public LevelTwo() {
        this.velocity = new ArrayList<Velocity>();
        this.velocity.add(new Velocity(-10, -1));
        this.velocity.add(new Velocity(-9, -2));
        this.velocity.add(new Velocity(-8, -3));
        this.velocity.add(new Velocity(-7, -4));
        this.velocity.add(new Velocity(-6, -5));
        this.velocity.add(new Velocity(10, -1));
        this.velocity.add(new Velocity(9, -2));
        this.velocity.add(new Velocity(8, -3));
        this.velocity.add(new Velocity(7, -4));
        this.velocity.add(new Velocity(6, -5));

        this.backSprite = new background2();
        this.blocks = createRow(new Point(14,250),16,783/16,20,1);
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
        for (int i = 0; i < numberOfBlocks; i+=2) {
            Color color = getRandomColor();
            Block added1 = new Block(
                    new Rectangle(new Point(beginningLeft.getX() + (width * i) + distance, beginningLeft.getY()), width,
                            height, color));
            Block added2 = new Block(
                    new Rectangle(new Point(beginningLeft.getX() + (width * (i+1) + distance), beginningLeft.getY()), width,
                            height, color));
            row.add(added1);
            row.add(added2);
            //    this.blockCounter.increase(1);
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
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return new String("Wide Easy");  //row breaker
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
        return Color.CYAN;
    }
}
