import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the background for level 3 in the game.
 */
public class Background3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#2036A2"));
        d.fillCircle(400, 700, 800);
        d.setColor(Color.decode("#2741C4"));
        d.fillCircle(400, 700, 775);
        d.setColor(Color.decode("#2A52D5"));
        d.fillCircle(400, 700, 750);
        d.setColor(Color.decode("#356DD4"));
        d.fillCircle(400, 700, 725);
        d.setColor(Color.decode("#456DD3"));
        d.fillCircle(400, 700, 700);
        d.setColor(Color.decode("#4A7FD3"));
        d.fillCircle(400, 700, 675);
        d.setColor(Color.decode("#2B82B1"));
        d.fillCircle(400, 700, 650);
        d.setColor(Color.decode("#4681B4"));
        d.fillCircle(400, 700, 625);
        d.setColor(Color.decode("#5C84B2"));
        d.fillCircle(400, 700, 600);
        d.setColor(Color.decode("#6B83B3"));
        d.fillCircle(400, 700, 575);
        d.setColor(Color.decode("#6B74B3"));
        d.fillCircle(400, 700, 550);
        d.setColor(Color.decode("#7C6BB3"));
        d.fillCircle(400, 700, 525);
        d.setColor(Color.decode("#836BB3"));
        d.fillCircle(400, 700, 500);
        d.setColor(Color.decode("#A481C5"));
        d.fillCircle(400, 700, 475);
        d.setColor(Color.decode("#B081C5"));
        d.fillCircle(400, 700, 450);
        d.setColor(Color.decode("#C081C5"));
        d.fillCircle(400, 700, 425);
        d.setColor(Color.decode("#D378C0"));
        d.fillCircle(400, 700, 400);
        d.setColor(Color.decode("#E382AF"));
        d.fillCircle(400, 700, 375);
        d.setColor(Color.decode("#E57B9B"));
        d.fillCircle(400, 700, 350);
        d.setColor(Color.decode("#E57B7F"));
        d.fillCircle(400, 700, 325);
        d.setColor(Color.decode("#E67556"));
        d.fillCircle(400, 700, 300);
        d.setColor(Color.decode("#E59E48"));
        d.fillCircle(400, 700, 275);
        d.setColor(Color.decode("#E5B348"));
        d.fillCircle(400, 700, 250);
        d.setColor(Color.decode("#E5C848"));
        d.fillCircle(400, 700, 225);
        d.setColor(Color.darkGray);
        d.fillRectangle(45, 425, 110, 175);
        d.fillRectangle(82, 370, 33, 60);
        d.fillRectangle(94, 190, 10, 180);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.setColor(Color.white);
                d.fillRectangle(55 + (j * 20), 435 + (i * 43), 10, 30);
            }
        }
        d.setColor(Color.decode("#E8B85E"));
        d.fillCircle(99, 185, 13);
        d.setColor(Color.decode("#E8785E"));
        d.fillCircle(99, 185, 11);
        d.setColor(Color.decode("#E84330"));
        d.fillCircle(99, 185, 7);
        d.setColor(Color.decode("#E9E3E2"));
        d.fillCircle(99, 185, 3);
    }

    @Override
    public void timePassed() {

    }
}
