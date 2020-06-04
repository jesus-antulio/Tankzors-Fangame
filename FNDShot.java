import greenfoot.*;
import java.util.*;

public class FNDShot extends Bullets{
    public FNDShot(int type, int direction){
        super(type, direction);
    }
    
    public void act(){
        int x = getX();
        int y = getY();
        
        if(goX != 0 || goY != 0) move(x,y);
        else getDirection(x,y);
        if(isTouching(Obstacle.class) && !isTouching(Barricade.class)){
            if(isTouching(SandBag.class)){
                List<SandBag> wall = getIntersectingObjects(SandBag.class);
                wall.get(0).removeSandBag();
                wall.remove(wall.get(0));
            }
            Player.removeBullet(this);
            getWorld().removeObject(this);
        }
        else if(isTouching(Enemy.class)){
            List<Enemy> tgt = getIntersectingObjects(Enemy.class);
            tgt.get(0).hitEnemy(type);
            tgt.remove(tgt.get(0));
            Player.removeBullet(this);
            getWorld().removeObject(this);
        }
    }
}
