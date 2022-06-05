import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * main.
 */
public class Ass5Game {
    /**
     * running the main.
     *
     * @param args not used.
     */
    public static void main(String[] args) {  //turn static
        GUI gui = new GUI("DESTROY!!!", 800, 600);
        AnimationRunner ar = new AnimationRunner(60, gui, gui.getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());

        int numberLives = 7;

        GameFlow game = new GameFlow(ar, gui.getKeyboardSensor(), numberLives, 800, 600);
        game.runLevels(levels);
        gui.close();
    }
}
