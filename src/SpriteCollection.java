import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * a collection of sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * adding a sprite to the collection.
     *
     * @param s the sprite were adding to the collection.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removes sprite from sprite collection.
     *
     * @param s the sprite that will be removed.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * adding a couple of tprites to the collection.
     *
     * @param s an array list of sprites we want to add.
     */
    public void addManySprite(List<? extends Sprite> s) {
        sprites.addAll(s);
    }

    /**
     * calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
       // List<Sprite> sprites = this.sprites;
        List<Sprite> sprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the draw surface for all the drawings.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
