import greenfoot.*;

public class SandBag extends Obstacle{
    public SandBag(int x, int y){
        super("spr_Sandbag.png", x, y);
    }
    
    public void removeSandBag(){
        Levels.removeSandbag(this);
        getWorld().removeObject(this);
    }
}
