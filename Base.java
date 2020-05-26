import greenfoot.*;

public class Base extends Actor{
    private int x;
    private int y;
    
    protected Base(String spr, int x, int y){
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

