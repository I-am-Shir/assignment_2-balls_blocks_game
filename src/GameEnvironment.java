import java.util.ArrayList;

/**
 * the game environment class.
 */
public class GameEnvironment {

    private ArrayList<Collidable> gameEnviro = new ArrayList<Collidable>();

    /**
     * array of collidables.
     * @return the array of collidables.
     */
    public Collidable[] getGameEnviro() {
        return gameEnviro.toArray(new Collidable[gameEnviro.size()]);
    }

    /**
     * add the given collidable to the environment.
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        gameEnviro.add(c);
    }

    /**
     * adding a couple of collidables.
     * @param c the arraylist to add.
     */
    public void addManyCollidable(ArrayList<? extends Collidable> c) {
        gameEnviro.addAll(c);
    }

    /**
     * gets the closest collision and its info-collisionInfo.
     * @param trajectory the way to ball will advance.
     * @return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collide = null;
        Collidable object = null;
        for (Collidable closer : gameEnviro) {
            Point check = trajectory.closestIntersectionToStartOfLine(closer.getCollisionRectangle());
            if (check != null) {
                if ((collide == null) || (trajectory.start().distance(collide) > trajectory.start().distance(check))) {
                    collide = check;
                    object = closer;
                }
            }
        }
        if (collide == null) {
            return null;
        }
        return new CollisionInfo(collide, object);
    }
}
