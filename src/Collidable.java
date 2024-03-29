/**
 * interface for collidables.
 */
public interface Collidable {
    /**
     * the rectangle to check collision with.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * changes the velocity of the ball for when is collides.
     *
     * @param hitter the ball that hit.
     * @param collisionPoint  the point of collision.
     * @param currentVelocity the velocity before collision.
     * @return the new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
