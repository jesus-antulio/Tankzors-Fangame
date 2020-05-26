import greenfoot.*;
import java.io.*;
import java.util.*;

public class Level1 extends World{
    
    public Level1(){    
        super(1280, 736, 1);
        prepare();
    }
    
    private void prepare(){
        File f = new File("map_Test.txt");
        List <Obstacle> obstacle = new LinkedList<>();
        List <Base> base = new LinkedList<>();
        Base pBase = loadMap(obstacle, base);
        Player player = Player.getInstance();
        drawMap(obstacle, base);
        addObject(player,pBase.getX(),pBase.getY());
    }
    
    private Base loadMap(List<Obstacle> obstacle, List <Base> base){
        int basePosition = 0;
        try {
            int pared;
            FileReader fr = new FileReader("maps/map_Test.txt");
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
}
