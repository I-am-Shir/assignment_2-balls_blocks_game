import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.util.ArrayList;

/**
 * a class for the pause screen, activated when p is pressed.
 */
public class PauseScreen implements Animation {
    private SpriteCollection sprites;
    private KeyboardSensor keyboard;
    private boolean stop;
    private Sprite background;
    private ArrayList<Block> frame;

    /**
     * constructor.
     * @param k Keyboard Sensor to kmow when to exit pause screen.
     * @param background the pause screen background- the levels background.
     * @param frame the frame for the background.
     */
    public PauseScreen(KeyboardSensor k, Sprite background, ArrayList<Block> frame) {
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
        d.drawText(300 - 70, d.getHeight() / 2 + 40, "press space to continue", 32);

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
