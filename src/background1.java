import biuoop.DrawSurface;

import java.awt.Color;

public class background1 implements Sprite{

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(10,30,780,570);
        d.setColor(Color.GREEN);
        d.drawCircle(395,155,15);
        d.setColor(Color.yellow);
        d.drawCircle(395,155,25);
        d.setColor(Color.orange);
        d.drawCircle(395,155,35);
    }

    @Override
    public void timePassed() {

    }
}
