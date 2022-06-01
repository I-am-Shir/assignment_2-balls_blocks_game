import java.util.List;

/**
 * main.
 */
public class Ass5Game {
    private Counter score = new Counter(0);
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
        GameLevel game = new GameLevel(new LevelFour(), new Counter(0));
        game.initialize();
        game.run();
    }
}
