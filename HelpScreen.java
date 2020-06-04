import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HelpScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpScreen extends World
{

    /**
     * Constructor for objects of class HelpScreen.
     * 
     */
    public HelpScreen()
    {    
        super(900, 600, 1);
        prepare();
    }
    
    public void prepare(){
        Message help = new Message("helpScreen.png");
        addObject(help,450,300);
        Message exit = new Message("message_Exit.png");
        addObject(exit,455,535);
    }
    
    public void act(){
        if (Greenfoot.isKeyDown("e")){
            Greenfoot.setWorld(new StartMenu());
        }
    }
}
