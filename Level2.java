import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends World
{

    /**
     * Constructor for objects of class Level2.
     * 
     */
    public Level2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1);
        prepare();
    }
    
    public void prepare(){
        int x=0, y=120;
        
        Pared_v pared_v1 = new Pared_v();
        Pared_v pared_v2 = new Pared_v();
        pared_v1.setImage("Pared_v_lvl2.png");
        pared_v2.setImage("Pared_v_lvl2.png");
        addObject(pared_v1,25,300);
        addObject(pared_v2,(900-25),300);
        
        Pared_h pared_h1 = new Pared_h();
        Pared_h pared_h2 = new Pared_h();
        pared_h1.setImage("Pared_h_lvl2.png");
        pared_h2.setImage("Pared_h_lvl2.png");
        addObject(pared_h1,450,5);
        addObject(pared_h2,450,(600-5));
        
        for (int i=0; i<4; i++){
            for (int j=0; j<6; j++){      
                Pared_c pared = new Pared_c();
                pared.setImage("Pared_lvl2.png");
                addObject(pared, (x+=130), y);
            }
            
            y=y+120;
            x=0;
        }
        
        Player player = new Player(pared_v1, pared_v2, pared_h1, pared_h2,5);
        addObject(player,80,525);
    }
}
