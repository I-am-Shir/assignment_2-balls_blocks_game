import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * a collection of sprites.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * adding a sprite to the collection.
     * @param s the sprite were adding to the collection.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * adding a couple of tprites to the collection.
     * @param s an array list of sprites we want to add.
     */
    public void addManySprite(ArrayList<? extends Sprite> s) {
        sprites.addAll(s);
    }

    /**
     * calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d the draw surface for all the drawings.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
