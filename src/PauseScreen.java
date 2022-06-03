import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

public class PauseScreen implements Animation {
    private SpriteCollection sprites;
    private  KeyboardSensor keyboard;
    private boolean stop;
    private Sprite background;
    private ArrayList<Block> frame;

    public PauseScreen(KeyboardSensor k,Sprite background,ArrayList<Block> frame) {
        this.keyboard = k;
        this.stop = false;
        this.background = background;
        this.frame = frame;
        this.sprites = new SpriteCollection();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
//        sprites.addManySprite(frame);
//        this.sprites.drawAllOn(d);
        d.drawText(300 + 20, d.getHeight() / 2 - 40, "paused", 50);
        d.drawText(300 - 70, d.getHeight() / 2+40, "press space to continue", 32);

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
