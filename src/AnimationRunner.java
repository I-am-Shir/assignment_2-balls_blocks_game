import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
//    private SpriteCollection sprites = new SpriteCollection();
//    private Counter blockCounter = new Counter();
//    private Counter ballCounter = new Counter();
//    private Counter score = new Counter();
//    private ScoreIndicator scoreIndicator = new ScoreIndicator(score);
    public AnimationRunner() {
    }


    /**
     * game-specific logic
     *
     * @param d the draw surface.
     */

    public boolean shouldStop() {
        if (blockCounter.getValue() <= 0) {
            score.increase(100);
            gui.close();
            return true;
        }
        if (ballCounter.getValue() <= 0) {
            gui.close();
            return true;
        }
        return false;

        /**
         * Run the game -- start the animation loop.
         */
        public void run () {
            int framesPerSecond = 30;
            int millisecondsPerFrame = 1000 / framesPerSecond;
            KeyboardSensor keyboard = gui.getKeyboardSensor();

            while (!shouldStop()) {

                long startTime = System.currentTimeMillis(); // timing
                DrawSurface d = gui.getDrawSurface();

                this.doOneFrame(d);


                // timing
                Sleeper sleeper = new Sleeper();
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    sleeper.sleepFor(milliSecondLeftToSleep);
                }

                int resume = 0;
                if (keyboard.isPressed("w")) {
                    while (resume == 0) {
                        if (keyboard.isPressed("s")) {
                            resume = 1;
                        }
                    }
                }
            }
        }
    }
}
