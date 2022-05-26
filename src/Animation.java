import biuoop.DrawSurface;

/**
 * a general interface for animating.
 */
public interface Animation {

    /**
     * specific logic.
     * @param d the specific effect on the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * stopping condition.
     * @return true when needs to stop and false otherwise.
     */
    boolean shouldStop();
}
