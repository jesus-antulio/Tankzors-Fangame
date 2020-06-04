import greenfoot.*;
import java.io.*;
import java.util.*;
    
public class Levels extends World{
    private Random rng = new Random();
    private int internalTime;
    private List <Base> base;
    private static List <Obstacle> obstacle;
    private static List <Enemy> enemy;
    private static int noEnemys;
    private static int enemysOnScreen;
    private static int level;
    
    public Levels(String map, int noEnemys, int level){
        super(1280, 736, 1);
        prepare(map);
        this.noEnemys = noEnemys;
        this.level = level;
        enemysOnScreen = 0;
        internalTime = 0;
    }
    
    private void prepare(String map){
        obstacle = new LinkedList<>();
        base = new ArrayList<>();
        enemy = new ArrayList<>();
        loadMap(obstacle, base, map);
        Base pBase = Player.getPlayerBase();
        Player player = Player.getInstance();
        drawMap(obstacle, base);
        addObject(player,pBase.getX(),pBase.getY());
    }
    
    private void loadMap(List<Obstacle> obstacle, List <Base> base, String map){
        int basePosition = 0;
        try {
            int pared;
            FileReader fr = new FileReader(map);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 23; i++) {
                for (int j = 0; j < 35; j++) {
                    pared = br.read();
                    switch (pared){
                        case 48:
                            break;
                        case 49:
                            Player.setPlayerBase(new FNDBase((j*32)+16, (i*32)+16));
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
        hq = Player.getPlayerBase();
        addObject(hq,hq.getX(),hq.getY());
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("e") || (noEnemys == 0 && level == 3) || (Player.getHp() <= 0 && Player.getLives() <= 0)){
            Greenfoot.setWorld(new ExitScreen());
        } else if(noEnemys == 0 && level == 1){
            Greenfoot.setWorld(new Level2());
        } else if(noEnemys == 0 && level == 2){
            Greenfoot.setWorld(new Level3());
        }
        
        Base eBase = base.get(rng.nextInt(base.size()));
        if(noEnemys > 0 && internalTime < 500) internalTime++;
        if(internalTime == 500 && enemysOnScreen < noEnemys && enemysOnScreen < 5){
            enemy.add(new Enemy(rng.nextInt(2)+1));
            addObject(enemy.get(enemysOnScreen),eBase.getX(),eBase.getY());
            enemysOnScreen++;
            internalTime = 0;
        }
        showText("Score: " + Player.getPoints(), 1200, 50);
        showText("HP: " + Player.getHp(), 1200, 100);
        showText("1UP: " + Player.getLives(), 1200, 150);
        showText("Rounds: " + Player.getRounds(), 1200, 200);
        showText("Enemys Left: " + noEnemys, 1200, 250);
        showText("Press E to exit", 1200, 700);
    }
    
    public static void removeSandbag(Obstacle bag){
        obstacle.remove(bag);
    }
    
    public static void removeEnemy(Enemy tgt){
        enemy.remove(tgt);
        enemysOnScreen--;
        noEnemys--;
    }
}
