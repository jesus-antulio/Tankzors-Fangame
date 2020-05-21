import greenfoot.*;

public class Player extends Actor{
    private static Player player = null;
    
    private Player(){
        setImage("spr_Tank_0.png");
    }
    
    public static Player getInstance(){
        if(player == null) player = new Player();
        return player;
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("w")){
            
        }
    }    
}
