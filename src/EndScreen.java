import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class EndScreen implements Animation{
    private boolean win;
    private Counter score;
    public EndScreen(boolean win, Counter score){
        this.win = win;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if(win) {
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
            d.drawText(300 - 20, d.getHeight() / 2 - 80, "YOU WIN!!!!", 50);
        d.drawText(300 - 30, d.getHeight() / 2, "your score is" + this.score.getValue(), 32);
        }
        else{
            d.setColor(Color.CYAN);
        d.fillRectangle(d.getWidth() / 4, d.getHeight() / 4, d.getWidth() / 2, d.getHeight() / 2);
        d.setColor(Color.darkGray);
        d.drawRectangle(d.getWidth() / 4, d.getHeight() / 4, d.getWidth() / 2, d.getHeight() / 2);
        d.setColor(Color.gray);
        d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 20, d.getWidth() / 2 - 40, d.getHeight() / 2 - 40);
        d.setColor(Color.white);
        d.drawText(300 - 20, d.getHeight() / 2 - 80, "GAME OVER", 50);
        d.drawText(300 - 40, d.getHeight() / 2, "Maybe Next Time", 32);
        }
//        d.setColor(Color.decode("#EAAC39"));
//        d.fillRectangle(10,500,780,530);
//        d.setColor(Color.decode("#EABB39"));
//        d.fillRectangle(10,530,780,555);
//        d.setColor(Color.decode("#EAC739"));
//        d.fillRectangle(10,555,780,570);
    }

//      d.setColor(Color.CYAN);
//        d.fillRectangle(d.getWidth() / 4, d.getHeight() / 4, d.getWidth() / 2, d.getHeight() / 2);
//        d.setColor(Color.darkGray);
//        d.drawRectangle(d.getWidth() / 4, d.getHeight() / 4, d.getWidth() / 2, d.getHeight() / 2);
//        d.setColor(Color.gray);
//        d.fillRectangle(d.getWidth() / 4 + 20, d.getHeight() / 4 + 20, d.getWidth() / 2 - 40, d.getHeight() / 2 - 40);
//        d.setColor(Color.white);
//        d.drawText(300 + 20, d.getHeight() / 2 - 80, "paused", 50);
//        d.drawText(300 - 70, d.getHeight() / 2, "press space to continue", 32);
//        this.gui.show(d);
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//        this.stop = true;

    @Override
    public boolean shouldStop() {
        return false;
    }
}
