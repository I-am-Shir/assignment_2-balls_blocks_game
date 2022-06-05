import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * the class for creating the screen shown at the end of the game.
 */
public class EndScreen implements Animation {
    private boolean win;
    private Counter score;
    private boolean running;
    private KeyboardSensor ks;

    /**
     * constructor.
     * @param win the result of the game, true-won, false-lost.
     * @param score the players end score.
     * @param ks the keyboard sensor (for closing the game).
     */
    public EndScreen(boolean win, Counter score, KeyboardSensor ks) {
        this.win = win;
        this.score = score;
        this.running = true;
        this.ks = ks;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.ks.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.running = false;
        }

        if (win) {
            d.setColor(Color.decode("#3242B8"));
            d.fillRectangle(d.getWidth() / 4, d.getHeight() / 4, d.getWidth() / 2, d.getHeight() / 2);
            d.setColor(Color.decode("#6632B8"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 25, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);
            d.setColor(Color.decode("#A032B8"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 50, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);
            d.setColor(Color.decode("#B83290"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 75, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);
            d.setColor(Color.decode("#AC2F46"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 100, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);
            d.setColor(Color.decode("#AC402F"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 125, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);
            d.setColor(Color.decode("#D35F31"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 150, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);
            d.setColor(Color.decode("#DB4C0F"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 175, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);
            d.setColor(Color.decode("#EA8E39"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 200, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);
            d.setColor(Color.decode("#EAAC39"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 225, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);
            d.setColor(Color.decode("#EABB39"));
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 250, d.getWidth() / 2 - 40,
                    d.getHeight() / 24);

            d.setColor(Color.BLACK);
            d.drawText(300 - 40, d.getHeight() / 2 - 80, "YOU WIN!!!!", 50);
            d.drawText(300 - 60, d.getHeight() / 2, "your score is" + this.score.getValue(), 32);
        } else {
            d.setColor(Color.CYAN);
            d.fillRectangle(d.getWidth() / 4, d.getHeight() / 4, d.getWidth() / 2, d.getHeight() / 2);
            d.setColor(Color.darkGray);
            d.drawRectangle(d.getWidth() / 4, d.getHeight() / 4, d.getWidth() / 2, d.getHeight() / 2);
            d.setColor(Color.gray);
            d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 20, d.getWidth() / 2 - 40,
                    d.getHeight() / 2 - 40);
            d.setColor(Color.white);
            d.drawText(300 - 50, d.getHeight() / 2 - 50, "GAME OVER", 50);
            d.drawText(300 - 32, d.getHeight() / 2+30, "Maybe Next Time", 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
