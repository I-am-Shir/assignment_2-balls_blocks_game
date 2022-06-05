import biuoop.KeyboardSensor;

import java.util.List;

/**
 * runs the game flow from level to level.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private ScoreIndicator scoreIndicator;
    private Counter lives;
    private Counter score;
    private boolean result;
    private int width;
    private int height;

    /**
     * constructor.
     * @param ar the animation runner.
     * @param ks keyboard sensor.
     * @param lives num of lives to start with.
     * @param width width of game.
     * @param height height of game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives, int width, int height) {
        this.score = new Counter(0);
        this.lives = new Counter(lives);
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.result = true;
        this.width = width;
        this.height = height;
    }

    /**
     * runs the levels from one to the other.
     * @param levels the list of levels the game has.
     */
    public void runLevels(List<LevelInformation> levels) {
        EndScreen endScreen;
        for (LevelInformation levelInfo : levels) {


            //level.initialize();

            while (this.lives.getValue() > 0) {
                GameLevel level = new GameLevel(levelInfo,
                        this.keyboardSensor,
                        this.animationRunner, this.score, this.lives, this.width, this.height);
                this.scoreIndicator = new ScoreIndicator(this.score, this.lives, levelInfo.levelName());
//                SpriteCollection gameScreen = new SpriteCollection();
//                gameScreen.addSprite(levelInfo.getBackground());
//                gameScreen.addSprite(scoreIndicator);
//                gameScreen.addSprite(lev);
                level.initialize();
                // this.animationRunner.run(new CountdownAnimation(2,3));
                level.addSprite(this.scoreIndicator);
                level.run();
                if (level.wonLevel()) {
                    this.score.increase(100);
                    break;
                } else {
                    this.lives.decrease(1);
                }
            }
        }
        if (this.lives.getValue() <= 0) {
            endScreen = new EndScreen(false, this.score, keyboardSensor);
            animationRunner.run(endScreen);
        } else {
            endScreen = new EndScreen(true, this.score, keyboardSensor);
            animationRunner.run(endScreen);
        }
    }
}

