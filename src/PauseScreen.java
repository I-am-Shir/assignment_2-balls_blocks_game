import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class PauseScreen implements Animation {
    private GUI gui;
    private  KeyboardSensor keyboard;
    private boolean stop;

    public PauseScreen(GUI gui,KeyboardSensor k) {
        this.gui = gui;
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {

//        d.setColor(Color.BLACK);
//        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
//        d.setColor(Color.decode("#1e7f00"));
//        d.fillRectangle(0, d.getHeight() / 2 - 160, d.getWidth(), 150);
//        d.setColor(Color.GREEN);
//        d.fillRectangle(0, d.getHeight() / 2 - 160, d.getWidth(), 3);
//        d.drawText(290, d.getHeight() / 2 - 100, "paused", 70);
//        d.drawText(270, d.getHeight() / 2 - 30, "press space to continue", 25);
//        d.fillRectangle(0, d.getHeight() / 2 - 10, d.getWidth(), 3);
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.stop = true;
//        }
//    }
        d.setColor(Color.CYAN);
        d.fillRectangle(d.getWidth() / 4, d.getHeight() / 4, d.getWidth() / 2, d.getHeight() / 2);
        d.setColor(Color.darkGray);
        d.drawRectangle(d.getWidth() / 4, d.getHeight() / 4, d.getWidth() / 2, d.getHeight() / 2);
        d.setColor(Color.gray);
        d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 20, d.getWidth() / 2 - 40, d.getHeight() / 2 - 40);
        d.setColor(Color.white);
        d.drawText(300 + 20, d.getHeight() / 2 - 80, "paused", 50);
        d.drawText(300 - 70, d.getHeight() / 2, "press space to continue", 32);
        this.gui.show(d);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

//        d.drawRectangle((int) pauseWindow.getUpperLeft().getX() - 1, (int) pauseWindow.getUpperLeft().getY() - 1,
//                (int) pauseWindow.getWidth() + 1, (int) pauseWindow.getHeight() + 1);
//        d.setColor(Color.CYAN);
//        d.drawText(400, d.getHeight() / 2, "paused -- press space to continue", 32);
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
