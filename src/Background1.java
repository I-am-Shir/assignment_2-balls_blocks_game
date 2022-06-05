import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the background for level 1 in the game.
 */
public class Background1 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(10, 30, 780, 570);
        d.setColor(Color.red);
        d.drawLine(400, 50, 400, 270);
        d.drawLine(290, 160, 510, 160);
        d.drawCircle(400, 160, 35);
        d.setColor(Color.orange);
        d.drawCircle(400, 160, 55);
        d.setColor(Color.green);
        d.drawCircle(400, 160, 75);
    }

    @Override
    public void timePassed() {

    }
}
