import biuoop.DrawSurface;
//import biuoop.GUI;

/**
 * creation of a ball.
 * includes its size, filling color,
 * velocity and direction,
 * surface and its limits.
 */
public class Ball implements Sprite {

    private Point locat;  //locat=location
    private int radius;
    private java.awt.Color colors;
    private Velocity velocity;
    private int limitWidth;
    private int limitHeight;
    private int limitStartX = 0;  //limits where the ball should be.
    private int limitStartY = 0;  //limits where the ball should be.
    private GameEnvironment gameEnvironment;

    /**
     * constructor for making a ball.
     *
     * @param center the center of the ball-its location.
     * @param r      the radius of the ball.
     * @param color  the balls color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.locat = center;
        this.radius = r;
        this.colors = color;
    }

    /**
     * makes a ball (different format).
     *
     * @param centerX  the x variable for the center of the ball.
     * @param centerY  the y variable for the center of the ball.
     * @param r        r the radius of the ball.
     * @param velocity the velocity of the ball.
     * @param color    the balls color.
     */
    public Ball(int centerX, int centerY, int r, Velocity velocity, java.awt.Color color) {
        this.locat = new Point(centerX, centerY);
        this.radius = r;
        this.colors = color;
        this.velocity = velocity;
    }

    // accessors

    /**
     * gets the x variable.
     *
     * @return the x.
     */
    public int getX() {
        return (int) locat.getX();
    }

    /**
     * gets the y variable.
     *
     * @return the y.
     */
    public int getY() {
        return (int) locat.getY();
    }

    /**
     * gets the size= radius of the ball.
     *
     * @return the size.
     */
    public int getSize() {
        return radius;
    }

    /**
     * getting the center of the ball, which equels the location.
     *
     * @return the location.
     */
    public Point getCenter() {
        return locat;
    }

    /**
     * gets the color of the ball.
     *
     * @return the balls color.
     */
    public java.awt.Color getColor() {
        return colors;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the draw surface, meaning our board.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(colors);
        surface.fillCircle(getX(), getY(), radius);
    }

    /**
     * puts the velocity into the space we created for it on the constructor.
     *
     * @param v the velocity we set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * sets the parameters for the velocity.
     *
     * @param dx the x velocity.
     * @param dy the y velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * gets the velocity we've set.
     *
     * @return that velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * sets the limit for the surface that starts with the board.
     *
     * @param x the limit of the width.
     * @param y the limit of the height.
     */
    public void setLimit(int x, int y) {
        this.limitStartX = 0;
        this.limitStartY = 0;
        this.limitWidth = x;
        this.limitHeight = y;
        if (x < 0) {
            this.limitStartX = x;
            this.limitWidth = 0;
        }
        if (y < 0) {
            this.limitStartY = y;
            this.limitHeight = 0;
        }
    }

    /**
     * sets the limit for the surface.
     *
     * @param x           the limit of the width of the drawing surface.
     * @param y           the limit of the height of the drawing surface.
     * @param limitStartX where the surface starts from- x variable.
     * @param limitStartY where the surface starts from- y variable.
     */
    public void setLimit(int x, int y, int limitStartX, int limitStartY) {
        this.limitWidth = x;
        this.limitHeight = y;
        this.limitStartX = limitStartX;
        this.limitStartY = limitStartY;
        if (x < limitStartX) {
            this.limitStartX = x;
            this.limitWidth = limitStartX;
        }
        if (y < limitStartY) {
            this.limitStartY = y;
            this.limitHeight = limitStartY;
        }
    }

    /**
     * sets game environment.
     *
     * @param gameEnvironment game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * the advancing of the ball, one step at a time.
     * controls the balls movement,
     * making it jump inside its border.
     */
    public void timePassed() {
        Velocity v = new Velocity(this.velocity.getVx(), this.velocity.getVy());
        CollisionInfo collide = gameEnvironment.getClosestCollision(new Line(locat, v.applyToPoint(this.locat)));
        //System.out.println(collide.collisionPoint());

        if (collide != null) {
            if (((this.locat.distance(collide.collisionPoint())) <= 6)
                    && (this.locat.distance(collide.collisionPoint()) > 0)) {
                System.out.println(
                        "V before is (" + v.getVx() + "," + v.getVy() + ") " + locat.getX() + " , " + locat.getY());
                v = collide.collisionObject().hit(collide.collisionPoint(), v);
                System.out.println(
                        "V after is (" + v.getVx() + "," + v.getVy() + ")" + locat.getX() + " , " + locat.getY());
                setVelocity(v);
                //System.out.println("inter close" + v.toString());
            } else {
                double totalVelo = Math.abs(v.getVx()) + Math.abs(v.getVy());
                double percentageVx = Math.abs(v.getVx()) / totalVelo;

                double newVx =
                        (v.getVx() < 0 ? -1 : 1) * ((this.locat.distance(collide.collisionPoint()) - 2) * percentageVx);
                double newVy =
                        ((v.getVy() < 0 ? -1 : 1) * ((this.locat.distance(collide.collisionPoint()) - 2)
                                * (1 - percentageVx))); //totalVelo-percentageVx==percentageVy
                setVelocity(collide.collisionObject().hit(collide.collisionPoint(), v));
                v = new Velocity(newVx, newVy);

                System.out.println("the x %= " + percentageVx + " newX = " + v.getVx() + " " + "newY = " + v.getVy());
                //System.out.println("inter far" + v.toString());
            }
        }
        System.out.println("newX = " + v.getVx() + " " + "newY = " + v.getVy());
        this.locat = v.applyToPoint(this.locat);

    }

    /**
     * adding ball sprite to the game.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
