import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond = 60;
    KeyboardSensor ks;
    private Animation pauseScreen;

    public AnimationRunner(int framesPerSecond, GUI gui,KeyboardSensor ks) {

        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
        this.ks = ks;
    }

        /**
         * Run the game -- start the animation loop.
         */
        public void run (Animation animation) {
            while (!animation.shouldStop()) {
                int millisecondsPerFrame = 1000 / framesPerSecond;
                long startTime = System.currentTimeMillis();

                    DrawSurface d = this.gui.getDrawSurface();
                    animation.doOneFrame(d);
                    this.gui.show(d);

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
