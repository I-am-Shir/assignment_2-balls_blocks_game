import java.util.ArrayList;

public class GameEnvironment {

    private ArrayList<Collidable> gameEnviro = new ArrayList<Collidable>();

    public Collidable[] getGameEnviro(){
        return gameEnviro.toArray(new Collidable[gameEnviro.size()]);
    }
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        gameEnviro.add(c);
    }

    public void addManyCollidable(ArrayList<? extends Collidable> c) {
        gameEnviro.addAll(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collide = null;
        Collidable object = null;
        for (Collidable closer : gameEnviro) {
            Point check = trajectory.closestIntersectionToStartOfLine(closer.getCollisionRectangle());
            if (check != null)
                if ((collide == null) || (trajectory.start().distance(collide) > trajectory.start().distance(check))) {
                    collide = check;
                    object = closer;
                }
        }
        if (collide == null) {
            return null;
        }
        return new CollisionInfo(collide, object);
    }
}
