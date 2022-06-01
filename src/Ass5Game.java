import biuoop.GUI;

import java.util.List;

/**
 * main.
 */
public class Ass5Game {
    Gui gui = new GUI("destroy",800,600);
    AnimationRunner r = new AnimationRunner(gui);
    private LevelInformation levelInfo;

//  levelInfo  public Ass5Game(){
//       this.levelInfo = new LevelTwo();
//
//    }
    /**
     * running the main.
     *
     * @param args not used.
     */
    public static void main(String[] args) {  //turn static
     //   GameLevel game = new GameLevel(new LevelFour(), new Counter(0));
        GameFlow gameFlow = new GameFlow()
        game.initialize();
        game.run();
    }
}
