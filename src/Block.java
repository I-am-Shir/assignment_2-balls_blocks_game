import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable, Sprite {
    Rectangle collide;

    // Return the "collision shape" of the object.
    @Override
    public Rectangle getCollisionRectangle() {
        return collide;
    }

    public Block(Rectangle block) {
        this.collide = block;
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
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Double newVx = currentVelocity.getVx();
        Double newVy = currentVelocity.getVy();
        Point rectLeft = getCollisionRectangle().getUpperLeft();
        Point rectBottomRight = new Point(rectLeft.getX() + getCollisionRectangle().getWidth(), rectLeft.getY() +
                getCollisionRectangle().getHeight());

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

    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
