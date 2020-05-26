import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartMenu extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public StartMenu()
    {    
        super(900, 600, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Message title = new Message("title.png");
        addObject(title,450,300);
        Tank tank1 = new Tank("spr_Tank_0_startMenu.png");
        addObject(tank1,783,483);
        Tank tank2 = new Tank("spr_SPTank_0_startMenu.png");
        addObject(tank2,120,483);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new Level1());
        }       
    }
}
