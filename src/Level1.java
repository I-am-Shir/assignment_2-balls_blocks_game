import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the level information for level 1.
 */
public class Level1 implements LevelInformation {
    private List<Velocity> velocity;
    private Background1 backSprite;
    private List<Block> block;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;

    /**
     * constructor.
     */
    public Level1() {
        this.velocity = new ArrayList<Velocity>();
        this.velocity.add(new Velocity(0, -3));
        this.backSprite = new Background1();
        this.block = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(390, 150), 20, 20, Color.RED));
        this.block.add(block);
    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return velocity;
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
        return new String("Direct Hit");
    }  //Bullseye

    @Override
    public Sprite getBackground() {
        return this.backSprite;
    }

    @Override
    public List<Block> blocks() {
        return this.block;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.block.size();
    }

    @Override
    public Color frameColor() {
        return Color.red;
    }
}
