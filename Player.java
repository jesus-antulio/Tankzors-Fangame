import greenfoot.*;

public class Player extends Actor{
    private static Player player = null;
    private static final int movement = 2;
    private static int goX = 0;
    private static int goY = 0;
    
    private Player(){
        setImage("spr_Tank_0.png");
    }
    
    public static Player getInstance(){
        if(player == null) player = new Player();
        return player;
    }
    
    public void act(){
        int x = getX();
        int y = getY();
        
        if(goX != 0 || goY != 0) move(x,y);
        else getKey(x,y);
    }
    
    public void move(int x, int y){
        if(goY < 0){
            setLocation(x, y - movement);
            goY += movement;
        }
        else if(goY > 0){
            setLocation(x, y + movement);
            goY -= movement;
        }
        if(goX < 0){
            setLocation(x - movement, y);
            goX += movement;
        }
        else if(goX > 0){
            setLocation(x + movement, y);
            goX -= movement;
        }
    }
    
    public void getKey(int x, int y){
        if(Greenfoot.isKeyDown("w")){
            goY -= 32;
        }
        else if(Greenfoot.isKeyDown("s")){
            goY += 32;
        }
        else if(Greenfoot.isKeyDown("a")){
            goX -= 32;
        }
        else if(Greenfoot.isKeyDown("d")){
            goX += 32;
        }
    }
}
