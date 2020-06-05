import greenfoot.*;

import java.util.*;

public class Player extends Actor{
    public static final String KEY_DOWN = "w";
    public static final String KEY_BUY_BULLETS = "r";
    private static Base pBase;
    private static Player player = null;
    private static int movement = 2;
    private static int direction = 0;
    private static int type = 1;
    private static int goX = 0;
    private static int goY = 0;
    private static List<FNDShot> shots = new ArrayList<>();
    private static int playerTime = 0;
    private static int points = 0;
    private static int hp = 3;
    private static int lives = 3;
    private static int rounds = 100;
    private static boolean kbHit = false;
    
    
    private Player(){
        setImage("spr_Tank_0.png");
    }
    
    public static Player getInstance(){
        if(player == null) player = new Player();
        return player;
    }
    
    public static void restartInstance(){
        movement = 2;
        direction = 0;
        type = 1;
        goX = 0;
        goY = 0;
        shots = new ArrayList<>();
        playerTime = 0;
        points = 0;
        hp = 3;
        lives = 3;
        rounds = 100;
    }
    
    public void act(){
        playerTime++;
        int x = getX();
        int y = getY();
        
        if(goX != 0 || goY != 0) move(x,y);
        else getKey(x,y);
        if(Greenfoot.isKeyDown("f") && playerTime > 30 && rounds > 0){
            shots.add(new FNDShot(type, direction));
            getWorld().addObject(shots.get(shots.size()-1),x,y);
            rounds--;
            playerTime = 0;
        }
        inBaseActions();
        isHPZero();
    }
    
    private void move(int x, int y){
        if(goY < 0){
            setRotation(0);
            if(isTouching(Obstacle.class)){
                setLocation(x, y + movement);
                goY=0;
            }else{
                setLocation(x, y - movement);
                goY += movement;
            }
        }
        else if(goY > 0){
            setRotation(180);
            if(isTouching(Obstacle.class)){
                setLocation(x, y - movement);
                goY=0;
            }else{
                setLocation(x, y + movement);
                goY -= movement;
            }
        }
        if(goX < 0){
            setRotation(270);
            if(isTouching(Obstacle.class)){
                setLocation(x + movement, y);
                goX=0;
            }else{
                setLocation(x - movement, y);
                goX += movement;
            }
        }
        else if(goX > 0){
            setRotation(90);
            if(isTouching(Obstacle.class)){
                setLocation(x - movement, y);
                goX=0;
            }else{
                setLocation(x + movement, y);
                goX -= movement;
            }
        }
    }
    
    private void getKey(int x, int y){
        if(Greenfoot.isKeyDown(KEY_DOWN)){
            goY -= TankzorsConstants.BLOCK_SIZE;
            direction = 0;
        }
        else if(Greenfoot.isKeyDown("s")){
            goY += TankzorsConstants.BLOCK_SIZE;
            direction = 1;
        }
        else if(Greenfoot.isKeyDown("a")){
            goX -= TankzorsConstants.BLOCK_SIZE;
            direction = 2;
        }
        else if(Greenfoot.isKeyDown("d")){
            goX += TankzorsConstants.BLOCK_SIZE;
            direction = 3;
        }
    }
    
    private void inBaseActions(){
        if(isTouching(FriendBase.class)){
            if(Greenfoot.isKeyDown(KEY_BUY_BULLETS) && rounds <= 90 && points >= 100 && !kbHit){
                rounds += 10;
                points -= 100;
                kbHit = true;
            }
            else if(Greenfoot.isKeyDown("u") && type == 1 && points >=2000){
                setImage("spr_SPTank_0.png");
                type = 2;
                movement = 1;
                points -= 2000;
                hp = hp * 2;
                kbHit = true;
            }
            else if(Greenfoot.isKeyDown("t") && points >=500 && hp < (3 * type)){
                hp++;
                points -= 500;
                kbHit = true;
            }
            else if(!Greenfoot.isKeyDown("t") && !Greenfoot.isKeyDown("u") && !Greenfoot.isKeyDown("r")) kbHit = false;
        }
    }
    
    private void isHPZero(){
        if(Player.getHp() <= 0 && Player.getLives() > 0){
            setLocation(pBase.getX(), pBase.getY());
            goX = 0;
            goY = 0;
            hp = 3;
            lives--;
            setImage("spr_Tank_0.png");
            movement = 2;
            type = 1;
        }
    }
    
    public static void removeBullet(Bullets shot){
        shots.remove(shot);
    }
    
    public static void scorePoints(int score){
        points += score;
    }
    
    public static int getPoints(){
        return points;
    }
    
    public static void hitPlayer(int dmg){
        hp -= dmg;
    }
    
    public static int getHp(){
        return hp;
    }
    
    public static int getLives(){
        return lives;
    }
    
    public static void setPlayerBase(Base base){
        pBase = base;
    }
    
    public static Base getPlayerBase(){
        return pBase;
    }
    
    public static int getRounds(){
        return rounds;
    }
}
