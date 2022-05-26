import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the indicator to the player for his score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle scoreTitle;

    /**
     * constructor for score indicator from score counter object.
     *
     * @param score the players score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.scoreTitle = new Rectangle(new Point(0, 0), 800, 25, Color.lightGray);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.scoreTitle.getColor());
        d.fillRectangle((int) scoreTitle.getUpperLeft().getX(), (int) scoreTitle.getUpperLeft().getY(),
                (int) scoreTitle.getWidth(), (int) scoreTitle.getHeight());
        d.drawRectangle((int) scoreTitle.getUpperLeft().getX() - 1, (int) scoreTitle.getUpperLeft().getY() - 1,
                (int) scoreTitle.getWidth() + 1, (int) scoreTitle.getHeight() + 1);
        d.setColor(Color.BLACK);
        d.drawText(((int) scoreTitle.getUpperLeft().getX() + (int) scoreTitle.getWidth() / 2),
                ((int) scoreTitle.getUpperLeft().getY() + (int) scoreTitle.getHeight() / 2 + 5),
                "Score: " + Integer.toString(this.score.getValue()), 13);
    }

    @Override
    public void timePassed() {

    }
}
