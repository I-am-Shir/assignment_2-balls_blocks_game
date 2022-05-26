/**
 * it's the interface used for updating the relevant object when an update happened (in our case it has been hit).
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl the listener added.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the listener removed.
     */
    void removeHitListener(HitListener hl);
}
