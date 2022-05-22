import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the block class which implements collidable and sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    List<HitListener> hitListeners = new ArrayList<HitListener>();
    private Rectangle collide;

    /**
     * constructor.
     *
     * @param block the block.
     */
    public Block(Rectangle block) {
        this.collide = block;
    }

    /**
     * gets the rectangle for block.
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return collide;
    }

    /**
     * draws the block on draw-surface (part of sprite).
     *
     * @param d the draw-surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(collide.getColor());
        d.fillRectangle((int) collide.getUpperLeft().getX(), (int) collide.getUpperLeft().getY(),
                (int) collide.getWidth(), (int) collide.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) collide.getUpperLeft().getX() - 1, (int) collide.getUpperLeft().getY() - 1,
                (int) collide.getWidth() + 1, (int) collide.getHeight() + 1);
    }

    /**
     * does nothing for now (part of sprite).
     */
    public void timePassed() {

    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Double newVx = currentVelocity.getVx();
        Double newVy = currentVelocity.getVy();
        notifyHit(hitter);
        Point rectLeft = getCollisionRectangle().getUpperLeft();
        Point rectBottomRight = new Point(rectLeft.getX() + getCollisionRectangle().getWidth(), rectLeft.getY()
                + getCollisionRectangle().getHeight());

        if ((collisionPoint.getX() == rectLeft.getX())
                || (collisionPoint.getX() == rectBottomRight.getX())) {
            newVx *= (-1);
        }
        if ((collisionPoint.getY() == rectLeft.getY())
                || (collisionPoint.getY() == rectBottomRight.getY())) {
            newVy *= (-1);
        }
        return new Velocity(newVx, newVy);
    }

    /**
     * adds the block to the game,
     * as collidable and sprite.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removes the block from the game,
     * from collidables and sprites.
     *
     * @param game the game were working on.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);

    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
