/**
 * hit listener interface.
 * meaning it's the interface used for what the listener does when it receives its block has been hit.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit beingHit object is hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
