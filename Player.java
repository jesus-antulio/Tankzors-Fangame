import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int vel = 3;
    private int enemigos;
    private int vida;
    private int xDirection = 1;
    private int yDirection = 1;
    private Pared_v p1;
    private Pared_v p2;
    private Pared_h p3;
    private Pared_h p4;
    
    public Player (Pared_v p1, Pared_v p2, Pared_h p3, Pared_h p4, int enemigos){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.enemigos = enemigos;
    }
    
    public void act() 
    {   
        if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new Level2());
        }
        setDirection();
    }
        
    public void setDirection(){
        int x = getX();
        int y = getY();
        
        if(Greenfoot.isKeyDown("up")){
            setRotation(0);
            if (intersects(p1) || intersects(p2) || intersects(p3) || intersects(p4)){
                setLocation(x, y+=5);
            } else {
                setLocation(x, y-(yDirection*vel));
            }
        }
        if(Greenfoot.isKeyDown("down")){
            setRotation(180);
            if (intersects(p1) || intersects(p2) || intersects(p3) || intersects(p4)){
                setLocation(x, y-=5);
            } else {
                setLocation(x, y+(yDirection*vel));
            }
        }
        if(Greenfoot.isKeyDown("left")){
            setRotation(270);
            if (intersects(p1) || intersects(p2) || intersects(p3) || intersects(p4)){
                setLocation(x+=5, y);
            } else {
                setLocation(x-(xDirection*vel), y);
            }
        }
        if(Greenfoot.isKeyDown("right")){
            setRotation(90);
            if (intersects(p1) || intersects(p2) || intersects(p3) || intersects(p4)){
                setLocation(x-=5, y);
            } else {
                setLocation(x+(xDirection*vel), y);
            }
        }
    }
    
    public void gira(int g){
        turn(g);
    }
    
    public int getEnemigos(){
        return enemigos;
    }
}
