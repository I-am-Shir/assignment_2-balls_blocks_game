import biuoop.DrawSurface;

import java.awt.Color;

public class background4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#3A4164"));
        d.fillRectangle(10, 30, 780, 570);
        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(100 + (i * 10), 370, 70 + (i * 10), 600);
        }
        for (int i = 0; i < 10; i++) {
            d.drawLine(605 + (i * 10), 490, 588 + (i * 10), 600);
        }
        d.setColor(Color.decode("#70DBFF"));
        for (int i = 0; i < 6; i++) {
            d.drawLine(140 + i, 370, 100, 450);
        }
        for (int i = 0; i < 6; i++) {
            d.drawLine(140 + i, 435, 100, 450);
        }
        for (int i = 0; i < 6; i++) {
            d.drawLine(140 + i, 435, 115, 500);
        }
        for (int i = 0; i < 6; i++) {
            d.drawLine(155 + i, 370, 100, 450);
        }
        for (int i = 0; i < 6; i++) {
            d.drawLine(155 + i, 435, 100, 450);
        }
        for (int i = 0; i < 6; i++) {
            d.drawLine(155 + i, 435, 115, 500);
        }
        //first cloud.
        d.setColor(Color.decode("#CFCFCF"));  //first color
        d.fillCircle(105, 370, 25);
        d.fillCircle(125, 390, 25);
        d.setColor(Color.decode("#A6A8B5")); //second color
        d.fillCircle(138, 360, 25);
        d.setColor(Color.decode("#9799A5")); //third color
        d.fillCircle(170, 375, 30);
        d.fillCircle(145, 390, 23);
        //second cloud
        d.setColor(Color.decode("#CFCFCF"));  //first color
        d.fillCircle(600, 480, 25);
        d.fillCircle(625, 520, 28);
        d.setColor(Color.decode("#A6A8B5")); //second color
        d.fillCircle(633, 487, 28);
        d.setColor(Color.decode("#9799A5")); //third color
        d.fillCircle(675, 493, 30);
        d.fillCircle(650, 517, 23);


    }

    @Override
    public void timePassed() {

    }
}
