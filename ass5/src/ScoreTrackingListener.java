/**
 * a listener meant to track the players score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter will give the players score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * adds points when a block was hit- meaning it was removed.
     * @param beingHit the block the got hit.
     * @param hitter the ball that hit it.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
    currentScore.increase(5);
    }
}
