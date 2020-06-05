import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import greenfoot.Greenfoot;
import greenfoot.World;

/**
 * This class represents all the levels. It takes care of the process to load a map
 */
public class Levels extends World {
    public static final int EMPTY_BLOCK = 48;
    public static final int PLAYER_BASE = 49;
    public static final int ENEMY_BASE = 50;
    public static final int BARRICADE = 51;
    public static final int SAND_BAG = 52;
    public static final int CONCRETE = 53;
    public static final int WALL = 54;
    public static final int WALL_BORDER = 55;
    public static final int BLOCK_OFFSET = 16;
    private Random rng = new Random();
    private int internalTime;
    private List<Base> base;
    private static List<Obstacle> obstacle;
    private static List<Enemy> enemy;
    private static int enemiesCount;
    private static int enemiesOnScreen;
    private static int level;
    private static final int MAX_ROWS_PER_MAP = 23;
    private static final int MAX_COLUMNS_PER_MAP = 35;

    public Levels(String map, int enemiesCount, int level) {
        super(1280, 736, 1);
        prepare(map);
        this.enemiesCount = enemiesCount;
        this.level = level;
        enemiesOnScreen = 0;
        internalTime = 0;
    }

    private void prepare(String map) {
        obstacle = new ArrayList<>();
        base = new ArrayList<>();
        enemy = new ArrayList<>();
        loadMap(obstacle, base, map);
        Base pBase = Player.getPlayerBase();
        Player player = Player.getInstance();
        drawMap(obstacle, base);
        addObject(player, pBase.getX(), pBase.getY());
    }

    /**
     * Load a map into the current world
     * 
     * @param obstacle List of obstables to be located in the map
     * @param base List of bases to be located on the map
     * @param map Name of the file to read the information of the map
     */
    private void loadMap(List<Obstacle> obstacle, List<Base> base, String map) {
        int basePosition = 0;

        try (FileReader fr = new FileReader(map);
             BufferedReader br = new BufferedReader(fr)) {
            int pared;

            for (int i = 0; i < MAX_ROWS_PER_MAP; i++) {
                for (int j = 0; j < MAX_COLUMNS_PER_MAP; j++) {
                    pared = br.read();
                    int xPosition = (j * TankzorsConstants.BLOCK_SIZE) + BLOCK_OFFSET;
                    int yPosition = (i * TankzorsConstants.BLOCK_SIZE) + BLOCK_OFFSET;
                    switch (pared) {
                        case EMPTY_BLOCK:
                            break;
                        case PLAYER_BASE:
                            Player.setPlayerBase(new FriendBase(xPosition, yPosition));
                            break;
                        case ENEMY_BASE:
                            base.add(new TargetBase(xPosition, yPosition));
                            break;
                        case BARRICADE:
                            obstacle.add(new Barricade(xPosition, yPosition));
                            break;
                        case SAND_BAG:
                            obstacle.add(new SandBag(xPosition, yPosition));
                            break;
                        case CONCRETE:
                            obstacle.add(new Concrete(xPosition, yPosition));
                            break;
                        case WALL:
                            obstacle.add(new Wall(xPosition, yPosition));
                            break;
                        case WALL_BORDER:
                            obstacle.add(new XWall(xPosition, yPosition));
                            break;
                        default:
                            j--;
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("File not found");
        }
    }

    private void drawMap(List<Obstacle> obstacle, List<Base> base) {
        Obstacle obs = null;
        Base hq = null;

        for (Obstacle value : obstacle) {
            obs = value;
            addObject(obs, obs.getX(), obs.getY());
        }

        for (Base value : base) {
            hq = value;
            addObject(hq, hq.getX(), hq.getY());
        }

        hq = Player.getPlayerBase();
        addObject(hq, hq.getX(), hq.getY());
    }

    public void act() {
        if (Greenfoot.isKeyDown("e") || (enemiesCount == 0 && level == 3) || (Player.getHp() <= 0 && Player.getLives() <= 0)) {
            Greenfoot.setWorld(new ExitScreen());
        } else if (enemiesCount == 0 && level == 1) {
            Greenfoot.setWorld(new Level2());
        } else if (enemiesCount == 0 && level == 2) {
            Greenfoot.setWorld(new Level3());
        }

        Base eBase = base.get(rng.nextInt(base.size()));
        if (enemiesCount > 0 && internalTime < 500) internalTime++;
        if (internalTime == 500 && enemiesOnScreen < enemiesCount && enemiesOnScreen < 5) {
            enemy.add(new Enemy(rng.nextInt(2) + 1));
            addObject(enemy.get(enemiesOnScreen), eBase.getX(), eBase.getY());
            enemiesOnScreen++;
            internalTime = 0;
        }
        showText("Score: " + Player.getPoints(), 1200, 50);
        showText("HP: " + Player.getHp(), 1200, 100);
        showText("1UP: " + Player.getLives(), 1200, 150);
        showText("Rounds: " + Player.getRounds(), 1200, 200);
        showText("Enemys Left: " + enemiesCount, 1200, 250);
        showText("Press E to exit", 1200, 700);
    }

    public static void removeSandbag(Obstacle bag) {
        obstacle.remove(bag);
    }

    public static void removeEnemy(Enemy tgt) {
        enemy.remove(tgt);
        enemiesOnScreen--;
        enemiesCount--;
    }
}
