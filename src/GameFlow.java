import biuoop.KeyboardSensor;

public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks){
        this.ar = ar;
        this.ks = ks;
    }

    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner,
               ...);

            level.initialize();

            while (level has more blocks and balls) {
                level.run();
            }

            if (no more balls) {
                break;
            }

        }
    }
}
