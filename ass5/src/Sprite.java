import biuoop.DrawSurface;

/**
 * interface for sprite.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d the draw surface.
     */
   void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
