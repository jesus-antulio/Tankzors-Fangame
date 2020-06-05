import java.util.List;

public class TargetShot extends Bullets {
    public TargetShot(int type, int direction) {
        super(type, direction);
    }

    public void act() {
        int x = getX();
        int y = getY();

        if (goX != 0 || goY != 0) move(x, y);
        else getDirection(x, y);
        if (isTouching(Obstacle.class) && !isTouching(Barricade.class)) {
            if (isTouching(SandBag.class)) {
                List<SandBag> wall = getIntersectingObjects(SandBag.class);
                wall.get(0).removeSandBag();
                wall.remove(wall.get(0));
            }
            Enemy.removeBullet(this);
            getWorld().removeObject(this);
        } else if (isTouching(Player.class)) {
            Player.hitPlayer(type);
            Enemy.removeBullet(this);
            getWorld().removeObject(this);
        }
    }
}
