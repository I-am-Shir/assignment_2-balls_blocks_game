/**
 * the collision info class.
 */
public class CollisionInfo {
    private Point collision;
    private Collidable collisObject;

    /**
     * constructor.
     *
     * @param collisionPoint  point of  collision.
     * @param collisionObject point of object collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collision = collisionPoint;
        this.collisObject = collisionObject;
    }

    /**
     * the point at which the collision occurs.
     * @return the point of collision.
     */
    public Point collisionPoint() {
        return collision;
    }

    /**
     * the collidable object involved in the collision.
     * @return the object it collided with.
     */
    public Collidable collisionObject() {
        return collisObject;
    }
}
