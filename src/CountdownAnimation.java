import biuoop.DrawSurface;
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation{
    private double numOfSecond;
    private int countFrom;
    private  SpriteCollection gameScreen;

    /**
     * constructor. for count down screen.
     * @param numOfSeconds the time to count.
     * @param countFrom the starting number.
     * @param gameScreen the screen shown to the player.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen){
        this.numOfSecond = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
