import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private GUI gui;
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private int limitRight = (int) Double.POSITIVE_INFINITY;
    private int limitLeft = 0;

    /**
     * setting the limits for the paddle.
     * @param limitLeft left limit (x).
     * @param limitRight right limit (x).
     */
    public void setLimits(int limitLeft, int limitRight) {
        this.limitLeft = limitLeft;
        this.limitRight = limitRight - (int) paddle.getWidth();
    }

    /**
     * constructor.
     * @param paddle the paddle, which is a rectangle.
     * @param gui the gui for the paddle (and game).
     */
    public Paddle(Rectangle paddle, GUI gui) {
        this.paddle = paddle;
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * moving the paddle left step.
     */
    public void moveLeft() {
        if (paddle.getUpperLeft().getX() <= limitLeft) {
            return;
        }
        Rectangle reapearedPaddle = new Rectangle(
                new Point(paddle.getUpperLeft().getX() - gui.getDrawSurface().getWidth() / 100,
                        paddle.getUpperLeft().getY()),
                paddle.getWidth(), paddle.getHeight(), paddle.getColor());
        paddle = reapearedPaddle;
    }

    /**
     * moving the paddle right step.
     */
    public void moveRight() {
        if (paddle.getUpperLeft().getX() >= limitRight) {
            return;
        }
        Rectangle reapearedPaddle = new Rectangle(
                new Point(paddle.getUpperLeft().getX() + gui.getDrawSurface().getWidth() / 100,
                        paddle.getUpperLeft().getY()),
                paddle.getWidth(), paddle.getHeight(), paddle.getColor());
        paddle = reapearedPaddle;
    }

    /**
     * part of the sprite implementation.
     * registers the command from the player.
     */
    public void timePassed() {
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawing the paddle on the draw surface.
     * @param d the draw surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddle.getColor());
        d.drawRectangle((int) paddle.getUpperLeft().getX() - 1, (int) paddle.getUpperLeft().getY() - 1,
                (int) paddle.getWidth() + 1, (int) paddle.getHeight() + 1);
        d.setColor(Color.black);
        d.fillRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY(), (int) paddle.getWidth(),
                (int) paddle.getHeight());
    }

    /**
     * part of collidable implementation.
     * gets the paddle rectangle- which is also a collidable.
     * @return the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return paddle;
    }

//    @Override
//    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
//        return null;
//    }

    /**
     * the hit method of the block.
     * @param collisionPoint where it's expected to collide.
     * @param currentVelocity the current velocity.
     * @return the new velocity of the ball.
     */
    public Velocity hit(Ball hitter,Point collisionPoint, Velocity currentVelocity) {
        Double newVx = currentVelocity.getVx();
        Double newVy = currentVelocity.getVy();
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
     * Add this paddle to the game.
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
