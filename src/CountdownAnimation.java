import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen.
 * it will do so for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 *  appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private long numOfMilliSecond;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long initiationTime;
    private boolean running;
    private int initialTime;

    /**
     * constructor. for count down screen.
     *
     * @param numOfSeconds the time to count.
     * @param countFrom    the starting number.
     * @param gameScreen the screen shown to the player.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom, SpriteCollection gameScreen) {
        //this.timer = new Timer(numOfSeconds,this.timer);
        this.numOfMilliSecond = (long) numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.initialTime = countFrom;
        this.initiationTime = System.currentTimeMillis();
        this.running = true;
        this.gameScreen = gameScreen;
    }

    //    private static void sleep(long millis) throws InterruptedException{
//    };
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.decode("#FFEEE5"));
        d.fillCircle(400 + 5, 300 - 50, 50);
        d.setColor(Color.decode("#8F0200"));
        d.drawText(400 - 22, 300 - 20, "" + this.countFrom, 105);
        d.setColor(Color.RED);
        d.drawText(400 - 19, 300 - 23, "" + this.countFrom, 95);
        if (System.currentTimeMillis() - this.initiationTime
                > this.numOfMilliSecond / this.initialTime) {
            this.initiationTime = System.currentTimeMillis();
            this.countFrom--;
        }
        if (this.countFrom == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}

