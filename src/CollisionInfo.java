public class CollisionInfo {
    Point collision;
    Collidable collisObject;

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

    // the point at which the collision occurs.
    public Point collisionPoint() {
        return collision;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return collisObject;
    }

}
