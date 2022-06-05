import biuoop.DrawSurface;
import javax.swing.Timer;
import java.awt.Color;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private long numOfMilliSecond;
    private int countFrom;
    //private  SpriteCollection gameScreen;
    private long initiationTime;

    /**
     * constructor. for count down screen.
     *
     * @param numOfSeconds the time to count.
     * @param countFrom    the starting number.
     *                     // * @param gameScreen the screen shown to the player.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom) {
        // SpriteCollection gameScreen
        //this.timer = new Timer(numOfSeconds,this.timer);
        this.numOfMilliSecond = (long) numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.initiationTime = System.currentTimeMillis();
        // this.gameScreen = gameScreen;
    }

    //    private static void sleep(long millis) throws InterruptedException{
//    };
    @Override
    public void doOneFrame(DrawSurface d) {
        for (int i = countFrom; i > 0; i--) {
            //   this.gameScreen.drawAllOn(d);
            d.setColor(Color.decode("#8F0200"));
            d.drawText(400 - 20, 300 - 20, "" + i, 60);
            d.setColor(Color.RED);
            d.drawText(400 - 20, 300 - 20, "" + i, 45);
            if (System.currentTimeMillis() - this.initiationTime
                    > this.numOfMilliSecond / this.countFrom) {
                this.initiationTime = System.currentTimeMillis();
            }
        }
    }
        @Override
        public boolean shouldStop () {
            return false;
        }
    }
}
