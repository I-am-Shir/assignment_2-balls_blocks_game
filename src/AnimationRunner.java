import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond = 60;
//    private SpriteCollection sprites = new SpriteCollection();
//    private Counter blockCounter = new Counter();
//    private Counter ballCounter = new Counter();
//    private Counter score = new Counter();
//    private ScoreIndicator scoreIndicator = new ScoreIndicator(score);
    public AnimationRunner(int framesPerSecond, GUI gui) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;

    }

        /**
         * Run the game -- start the animation loop.
         */
        public void run (Animation animation) {
            int millisecondsPerFrame = 1000 / framesPerSecond;

            while (!animation.shouldStop()) {
                long startTime = System.currentTimeMillis();
                DrawSurface d = gui.getDrawSurface();

                animation.doOneFrame(d);

                // timing
                Sleeper sleeper = new Sleeper();
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    sleeper.sleepFor(milliSecondLeftToSleep);
                }
            }
        }
}
