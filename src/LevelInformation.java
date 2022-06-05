import java.awt.Color;
import java.util.List;

/**
 * the interface for the level information.
 */
public interface LevelInformation {
    /**
     * the number of balls in the level.
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return a list of velocity's for the balls in the level.
     */
    List<Velocity> initialBallVelocities();

    /**
     * the speed of the paddle.
     * @return paddle speed for level.
     */
    int paddleSpeed();

    /**
     * the width of the paddle in the level.
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * the level name which will be displayed at the top of the screen.
     * @return a string which is the levels name.
      */
    String levelName();

    /**
     * the levels background.
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return a list of the levels blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed,
     * before the level is considered to be "cleared".
     * @return the number of blocks left to remove in the level.
     */
    int numberOfBlocksToRemove();

    /**
     * gets color for the frame- only for aesthetics purpose.
     * @return the chosen color.
     */
    Color frameColor();
}
