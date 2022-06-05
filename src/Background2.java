import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the background for level 2 in the game.
 */
public class Background2 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#66AEDB"));
        d.fillRectangle(10, 30, 780, 570);
        d.setColor(Color.decode("#EDD88C"));
        int rayStartY = 80;
        for (int i = 0; i < 100; i++) {  //drawing sun rays
            d.drawLine(150, 130, 0 + (i * 8), 250);
        }
        d.fillCircle(150, 130, 60);
        d.setColor(Color.decode("#EEDA44"));
        d.fillCircle(150, 130, 50);
        d.setColor(Color.decode("#EDE435"));
        d.fillCircle(150, 130, 40);

    }

    @Override
    public void timePassed() {

    }
}
