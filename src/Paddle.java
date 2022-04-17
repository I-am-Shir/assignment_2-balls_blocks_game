import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    private GUI gui;
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private int limitRight = (int) Double.POSITIVE_INFINITY;
    private int limitLeft = 0;

    public void setLimits(int limitLeft, int limitRight) {
        this.limitLeft = limitLeft;
        this.limitRight = limitRight-(int)paddle.getWidth();
    }

    public Paddle(Rectangle paddle, GUI gui) {
        this.paddle = paddle;
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
    }

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

    // Sprite
    public void timePassed() {
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    public void drawOn(DrawSurface d) {
        d.setColor(this.paddle.getColor());
        d.drawRectangle((int) paddle.getUpperLeft().getX() - 1, (int) paddle.getUpperLeft().getY() - 1,
                (int) paddle.getWidth() + 1, (int) paddle.getHeight() + 1);
        d.setColor(Color.black);
        d.fillRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY(), (int) paddle.getWidth(),
                (int) paddle.getHeight());
    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return paddle;
    }

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

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
