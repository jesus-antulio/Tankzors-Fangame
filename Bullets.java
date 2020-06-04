import greenfoot.*;

public class Bullets extends Actor
{
    private static final int movement = 4;
    protected int type;
    private int direction;
    protected int goX = 0;
    protected int goY = 0;
    
    public Bullets(int type, int direction){
        this.type = type;
        if(type == 2) setImage("spr_Dshot.png");
        else setImage("spr_shot.png");
        this.direction = direction;
    }
    
    protected void move(int x, int y){
        if(goY < 0){
            setRotation(0);
            setLocation(x, y - movement);
            goY += movement;
        }
        else if(goY > 0){
            setRotation(180);
            setLocation(x, y + movement);
            goY -= movement;
        }
        if(goX < 0){
            setRotation(270);
            setLocation(x - movement, y);
            goX += movement;
        }
        else if(goX > 0){
            setRotation(90);
            setLocation(x + movement, y);
            goX -= movement;
        }
    }
    
    protected void getDirection(int x, int y){
        switch(direction){
            case 0:
                goY -= 32;
                break;
            case 1:
                goY += 32;
                break;
            case 2:
                goX -= 32;
                break;
            case 3:
                goX += 32;
                break;
        }
    }
}
