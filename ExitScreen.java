import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PauseScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitScreen extends World
{

    /**
     * Constructor for objects of class PauseScreen.
     * 
     */
    public ExitScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {        
        Message exit = new Message("exit.png");
        addObject(exit,450,225);
        
        Tank tank1 = new Tank("spr_Tank_0_startMenu.png", 1);
        addObject(tank1,783,483);
        tank1.turn(90);
        Tank tank2 = new Tank("spr_SPTank_0_startMenu.png", 1);
        addObject(tank2,120,483);
        tank2.turn(90);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new StartMenu());
        }  
        
    }
}
