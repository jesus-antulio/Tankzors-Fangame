import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    private final int rotationPlayer;
    
    public Projectile(int rotationPlayer){
        this.rotationPlayer = rotationPlayer;
    }
    
    public void act() 
    {
        projectileMove();
        removeFromWorld();
    }
    
    public void projectileMove(){
        if(rotationPlayer==0){
            setLocation(getX(), getY()-4);
        } else if(rotationPlayer==180){
            setLocation(getX(), getY()+4);
        } else if(rotationPlayer==270){
            setLocation(getX()-4, getY());
        } else if(rotationPlayer==90){
            setLocation(getX()+4, getY());
        }
    }
    
    public void removeFromWorld(){
        if (isTouching(Obstacle.class) || isTouching(TGTBase.class) || isTouching(Enemy.class)){
            getWorld().removeObject(this);
        }
    }
}
