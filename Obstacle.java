import greenfoot.*;

public class Obstacle extends Actor{
    private int x;
    private int y;
    
    protected Obstacle(String spr, int x, int y){
        setImage(spr);
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
