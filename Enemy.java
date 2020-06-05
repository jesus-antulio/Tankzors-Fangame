import greenfoot.*;
import java.util.*;
        
public class Enemy extends Actor{
    private static Random rng = new Random();
    private int movement;
    private int direction;
    private int type;
    private int hp;
    private int goX;
    private int goY;
    private static List<TargetShot> shots = new ArrayList<>();
    private int enemyTime = 0;
    
    public Enemy(int type){
        this.type = type;
        if(type == 2){
            setImage("spr_TGTSPTank.png");
            movement = 1;
        }
        else{
            setImage("spr_TGTTank.png");
            movement = 2;
        }
        hp = type;
    }
            
    public void act(){
        enemyTime++;
        int x = getX();
        int y = getY();
        
        if(goX != 0 || goY != 0) move(x,y);
        else getDirection(x,y);
        shotPlayer();
        if(hp <= 0){
            removeEnemy();
        }
    }
            
    private void move(int x, int y){
        if(goY < 0){
            setRotation(0);
            if(isTouching(Obstacle.class) || isTouching(FriendBase.class)){
                setLocation(x, y + movement);
                goY=0;
            }else{
                setLocation(x, y - movement);
                goY += movement;
            }
        }
        else if(goY > 0){
            setRotation(180);
            if(isTouching(Obstacle.class) || isTouching(FriendBase.class)){
                setLocation(x, y - movement);
                goY=0;
            }else{
                setLocation(x, y + movement);
                goY -= movement;
            }
        }
        if(goX < 0){
            setRotation(270);
            if(isTouching(Obstacle.class) || isTouching(FriendBase.class)){
                setLocation(x + movement, y);
                goX=0;
            }else{
                setLocation(x - movement, y);
                goX += movement;
            }
        }
        else if(goX > 0){
            setRotation(90);
            if(isTouching(Obstacle.class) || isTouching(FriendBase.class)){
                setLocation(x - movement, y);
                goX=0;
            }else{
                setLocation(x + movement, y);
                goX -= movement;
            }
        }
    }
    
    private void getDirection(int x, int y){
        direction = rng.nextInt(4);
        switch(direction){
            case 0:
                goY -= TankzorsConstants.BLOCK_SIZE;
                break;
            case 1:
                goY += TankzorsConstants.BLOCK_SIZE;
                break;
            case 2:
                goX -= TankzorsConstants.BLOCK_SIZE;
                break;
            case 3:
                goX += TankzorsConstants.BLOCK_SIZE;
                break;
        }
    }
    
    private void shotPlayer(){
        if(Player.getInstance().getX() == getX() && ((type == 1 && enemyTime > 30) || (type == 2 && enemyTime > 45))){
            if(Player.getInstance().getY() < getY() && direction == 0){
                shots.add(new TargetShot(type, direction));
                getWorld().addObject(shots.get(shots.size()-1),getX(),getY());
                enemyTime = 0;
            }
            else if(Player.getInstance().getY() > getY() && direction == 1){
                shots.add(new TargetShot(type, direction));
                getWorld().addObject(shots.get(shots.size()-1),getX(),getY());
                enemyTime = 0;
            }
        }
        else if(Player.getInstance().getY() == getY() && ((type == 1 && enemyTime > 30) || (type == 2 && enemyTime > 45))){
            if(Player.getInstance().getX() < getX() && direction == 2){
                shots.add(new TargetShot(type, direction));
                getWorld().addObject(shots.get(shots.size()-1),getX(),getY());
                enemyTime = 0;
            }
            else if(Player.getInstance().getX() > getX() && direction == 3){
                shots.add(new TargetShot(type, direction));
                getWorld().addObject(shots.get(shots.size()-1),getX(),getY());
                enemyTime = 0;
            }
        }
    }
    
    public void hitEnemy(int dmg){
        hp -= dmg;
        Player.scorePoints(10);
    }
    
    private void removeEnemy(){
        Levels.removeEnemy(this);
        getWorld().removeObject(this);
        Player.scorePoints(50*type);
    }
    
    public static void removeBullet(Bullets shot){
        shots.remove(shot);
    }
}
