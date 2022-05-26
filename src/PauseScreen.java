import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class PauseScreen implements Animation {
    private  KeyboardSensor keyboard;
    private boolean stop;
    private Rectangle pauseWindow;
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    public PauseScreen(Rectangle pauseWindow){
        this.pauseWindow = new Rectangle(new Point(300,200),200,200,Color.black);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.fillRectangle((int) pauseWindow.getUpperLeft().getX(), (int) pauseWindow.getUpperLeft().getY(),
                (int) pauseWindow.getWidth(), (int) pauseWindow.getHeight());
        d.drawRectangle((int) pauseWindow.getUpperLeft().getX() - 1, (int) pauseWindow.getUpperLeft().getY() - 1,
                (int) pauseWindow.getWidth() + 1, (int) pauseWindow.getHeight() + 1);
        d.setColor(Color.CYAN);
        d.drawText(400, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
