import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter lives;
    private Counter score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks){
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner.this.score,this.lives);

            level.initialize();

            while (levelInfo.numberOfBalls() > 0 && levelInfo.numberOfBlocksToRemove() > 0) {
                level.run();
            }

            if (levelInfo.numberOfBalls() <=0) {
                break;
            }

        }
    }
}
