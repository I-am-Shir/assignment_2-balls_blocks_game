/**
 * a ball remover listener build.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor for the ball remover listener.
     * @param game the game its in.
     * @param remainingBalls keeps track of the anount of balls in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * removes blocks that were hit.
     * @param beingHit beingHit object is hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
