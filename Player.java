import greenfoot.*;
import java.io.*;
import java.util.*;

public class Player extends Actor{
    private static Player player = null;
    private static final int movement = 2;
    private static int goX = 0;
    private static int goY = 0;
    private boolean canFire = true;
    
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
        
        fireProjectile();
    }
    
    public void move(int x, int y){
        if(goY < 0){
            setRotation(0);
            if(isTouching(Obstacle.class) || isTouching(TGTBase.class)){
                setLocation(x, y + movement);
                goY=0;
            }else{
                setLocation(x, y - movement);
                goY += movement;
            }
        }
        else if(goY > 0){
            setRotation(180);
            if(isTouching(Obstacle.class) || isTouching(TGTBase.class)){
                setLocation(x, y - movement);
                goY=0;
            }else{
                setLocation(x, y + movement);
                goY -= movement;
            }
        }
        if(goX < 0){
            setRotation(270);
            if(isTouching(Obstacle.class) || isTouching(TGTBase.class)){
                setLocation(x + movement, y);
                goX=0;
            }else{
                setLocation(x - movement, y);
                goX += movement;
            }
        }
        else if(goX > 0){
            setRotation(90);
            if(isTouching(Obstacle.class) || isTouching(TGTBase.class)){
                setLocation(x - movement, y);
                goX=0;
            }else{
                setLocation(x + movement, y);
                goX -= movement;
            }
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
    
    public void fireProjectile(){
        if(getRotation()==0){
            if(Greenfoot.isKeyDown("space") && canFire == true){
                getWorld().addObject(new Projectile(0), getX(), getY()-10);
                canFire = false;
            } else if (!Greenfoot.isKeyDown("space")) {
                canFire = true;
            }
        } else if(getRotation()==180){
            if(Greenfoot.isKeyDown("space") && canFire == true){
                getWorld().addObject(new Projectile(180), getX(), getY()+10);
                canFire = false;
            } else if (!Greenfoot.isKeyDown("space")) {
                canFire = true;
            }
        } else if(getRotation()==270){
            if(Greenfoot.isKeyDown("space") && canFire == true){
                getWorld().addObject(new Projectile(270), getX()-10, getY());
                canFire = false;
            } else if (!Greenfoot.isKeyDown("space")) {
                canFire = true;
            }
        } else if(getRotation()==90){
            if(Greenfoot.isKeyDown("space") && canFire == true){
                getWorld().addObject(new Projectile(90), getX()+10, getY());
                canFire = false;
            } else if (!Greenfoot.isKeyDown("space")) {
                canFire = true;
            }
        }
    }
}
