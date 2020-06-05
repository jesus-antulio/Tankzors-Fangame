import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tank extends Actor
{
    private int xDirection = 1;
    private int velocity = 3;
    public static int modo;
    
    public Tank(String spr, int modo){
        setImage(spr);
        this.modo = modo;
    }

    /**
     *
     */
    public void act(){
        int x = getX();
        int y = getY();
        
        if(modo==1){
            setLocation(x+(velocity*xDirection), y);
            
            if(isAtEdge() || isTouching(Tank.class)){
                turn(180);
                xDirection *= -1;
            }
        }
    }
}
