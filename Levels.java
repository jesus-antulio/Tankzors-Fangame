import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.*;

/**
 * Write a description of class Levels here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Levels extends World
{

    public Levels(String map, String map2){    
        super(1280, 736, 1);
        prepare(map, map2);
    }
    
    private void prepare(String map, String map2){
        File f = new File(map);
        List <Obstacle> obstacle = new LinkedList<>();
        List <Base> base = new LinkedList<>();
        Base pBase = loadMap(obstacle, base, map2);
        Player player = Player.getInstance();
        drawMap(obstacle, base);
        addObject(player,pBase.getX(),pBase.getY());
        Message message = new Message("message_Exit.png");
        addObject(message,1200,42);
    }
    
    private Base loadMap(List<Obstacle> obstacle, List <Base> base, String map2){
        int basePosition = 0;
        try {
            int pared;
            FileReader fr = new FileReader(map2);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 23; i++) {
                for (int j = 0; j < 35; j++) {
                    pared = br.read();
                    switch (pared){
                        case 48:
                            break;
                        case 49:
                            basePosition=base.size();
                            base.add(new FNDBase((j*32)+16, (i*32)+16));
                            break;
                        case 50:
                            base.add(new TGTBase((j*32)+16, (i*32)+16));
                            break;
                        case 51:
                            obstacle.add(new Barricade((j*32)+16, (i*32)+16));
                            break;
                        case 52:
                            obstacle.add(new SandBag((j*32)+16, (i*32)+16));
                            break;
                        case 53:
                            obstacle.add(new Concrete((j*32)+16,(i*32)+16));
                            break;
                        case 54:
                            obstacle.add(new Wall((j*32)+16,(i*32)+16));
                            break;
                        case 55:
                            obstacle.add(new XWall((j*32)+16,(i*32)+16));
                            break;
                        default:
                            j--;
                            break;
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("File not found");
        }
        return base.get(basePosition);
    }
    
    private void drawMap(List<Obstacle> obstacle, List<Base> base){
        Obstacle obs = null;
        Base hq = null;
        for(int i=0; i<obstacle.size();i++){
            obs = obstacle.get(i);
            addObject(obs,obs.getX(),obs.getY());
        }
        for(int i=0; i<base.size();i++){
            hq = base.get(i);
            addObject(hq,hq.getX(),hq.getY());
        }
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("e")){
            Greenfoot.setWorld(new ExitScreen());
        } else if(Greenfoot.isKeyDown("2")){
            Greenfoot.setWorld(new Level2());
        }
    }
}