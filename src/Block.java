public class Block implements Collidable {
    Rectangle collide;


    // Return the "collision shape" of the object.
    @Override
    public Rectangle getCollisionRectangle() {
        return collide;
    }

    public Block(Rectangle block) {
        this.collide = block;
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

}
