import greenfoot.*;
import java.util.Random;
        
public class Enemy extends Actor{
    private static Random rng = new Random();
    private int movement;
    private int type;
    private int goX;
    private int goY;
    
    public Enemy(int type){
        this.type = type;
        if(type == 2){
            setImage("spr_TGTSPTank.png");
            movement = 1;
        }
        else{
            setImage("spr_TGTTank.png");
            movement = 2;
        }
    }
            
    public void act(){
        int x = getX();
        int y = getY();
        
        if(goX != 0 || goY != 0) move(x,y);
        else getDirection(x,y);
        removeEnemy();
    }
            
    public void move(int x, int y){
        if(goY < 0){
            setRotation(0);
            if(isTouching(Obstacle.class) || isTouching(FNDBase.class)){
                setLocation(x, y + movement);
                goY=0;
            }else{
                setLocation(x, y - movement);
                goY += movement;
            }
        }
        else if(goY > 0){
            setRotation(180);
            if(isTouching(Obstacle.class) || isTouching(FNDBase.class)){
                setLocation(x, y - movement);
                goY=0;
            }else{
                setLocation(x, y + movement);
                goY -= movement;
            }
        }
        if(goX < 0){
            setRotation(270);
            if(isTouching(Obstacle.class) || isTouching(FNDBase.class)){
                setLocation(x + movement, y);
                goX=0;
            }else{
                setLocation(x - movement, y);
                goX += movement;
            }
        }
        else if(goX > 0){
            setRotation(90);
            if(isTouching(Obstacle.class) || isTouching(FNDBase.class)){
                setLocation(x - movement, y);
                goX=0;
            }else{
                setLocation(x + movement, y);
                goX -= movement;
            }
        }
    }
    
    public void getDirection(int x, int y){
        switch(rng.nextInt(4)){
            case 0:
                goY -= 32;
                break;
            case 1:
                goY += 32;
                break;
            case 2:
                goX -= 32;
                break;
            case 3:
                goX += 32;
                break;
        }
    }
        
    public void removeEnemy(){
        if (isTouching(Projectile.class)){
            getWorld().removeObject(this);
        }
    }
}