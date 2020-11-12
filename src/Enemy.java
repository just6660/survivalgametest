
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Enemy {
    
    private Rectangle hitbox, attackhitbox;
    private Image bigImg;
    private float xSpeed, ySpeed, factor;
    private int health = 100;
    private int damage = 50;
    private boolean isCooldown, isMovePause, attackDelay;
    
    private int enemycount = 0;
    private int enemytimer = 0;
    private int enemymovetimer = 0;
    private int enemyattacktimer = 0;
    private int enemyhittimer = 0;
    
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    
    ArrayList<Image>sprites;
    
    final int imgrows = 4;
    final int imgcols = 12;
    final int imgwidth = 66;
    final int imgheight = 72;
    
    
    public Enemy(int x, int y) throws SlickException{
        //add image
        bigImg = new Image("images/enemy.png");
        sprites = new ArrayList();
        for (int i = 0; i < imgrows; i++) {
            for (int j = 0; j < imgcols; j++) {
                sprites.add(bigImg.getSubImage(j*imgwidth, i* imgheight, imgwidth, imgheight));
                
            }
            
        }
        hitbox = new Rectangle(x,y,sprites.get(0).getWidth(),sprites.get(0).getHeight());
        
        attackhitbox = new Rectangle((int)hitbox.getX(),(int)hitbox.getY() + imgheight, imgwidth, 10);
        
        
       
        
    }
    
    
    public Rectangle getHitBox(){
        return hitbox;
    }
    public Rectangle getAttackHitbox(){
        return attackhitbox;
    }
    public boolean hit(Rectangle r){
        if(hitbox.intersects(r)) return true;
        else return false;
    }
    
    public void takeDamage(int i){
        health = health - i;
    }
    public int getDamage(){
        return damage;
    }
    public void setDamage(int i){
        damage = i;
    }
    public void setCooldown(boolean b){
        isCooldown = b;
    }
    public boolean isCooldown(){
        return isCooldown;
    }
    public boolean hitwall(){
        if(hitbox.getX() <=0){
            hitbox.setX(hitbox.getX() + 1);
            
            return true;
        }
        else if(hitbox.getX() > GAME_WIDTH - sprites.get(0).getWidth()){
            hitbox.setX(hitbox.getX() -1);
            
            return true;
        }
        else if(hitbox.getY() <=0){
            hitbox.setY(hitbox.getY() + 1);
            return true;
        }    
        else if(hitbox.getY() > GAME_HEIGHT - sprites.get(0).getHeight()){
            hitbox.setY(hitbox.getY() - 1);
            return true;
        }
            
        
        else
            return false;
        
    }
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int i){
        health = i;
    }
    public static void setGameSize(int x, int y){
        GAME_WIDTH = x;
        GAME_HEIGHT = y;
    }
    
    
    public void move(float x,float y){
          
        xSpeed= (x - hitbox.getX());
        ySpeed= (y - hitbox.getY());
        
        factor = (float) (.5 / Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed));
        xSpeed*=factor;
        ySpeed*=factor;
        
        hitbox.setX(hitbox.getX()+xSpeed);
        hitbox.setY(hitbox.getY()+ySpeed);
        
        
        
       
        
    }
    
    public void draw(int i, int x, int z){
        if(i==1){
            if(x<=5){
                sprites.get(0).draw(hitbox.getX(), hitbox.getY());
            }
            else if(x<=10){
                sprites.get(1).draw(hitbox.getX(),hitbox.getY());
          
            }
            else if(x<=15){
                sprites.get(2).draw(hitbox.getX(),hitbox.getY());
                
            }
            else if(x<=20){
                sprites.get(3).draw(hitbox.getX(),hitbox.getY());
            }
            else
                sprites.get(0).draw(hitbox.getX(),hitbox.getY());
          attackhitbox.setBounds(hitbox.getX(), hitbox.getY() + imgheight, imgwidth, 10);
                
        }
        else if(i==2){
            if(x<=5){
                sprites.get(12).draw(hitbox.getX(), hitbox.getY());
            }
            else if(x<=10){
                sprites.get(13).draw(hitbox.getX(),hitbox.getY());
          
            }
            else if(x<=15){
                sprites.get(14).draw(hitbox.getX(),hitbox.getY());
                
            }
            else if(x<=20){
                sprites.get(15).draw(hitbox.getX(),hitbox.getY());
            }
            else
                sprites.get(12).draw(hitbox.getX(),hitbox.getY());
            attackhitbox.setBounds(hitbox.getX(),hitbox.getY()-10,imgwidth, 10);
                
        }
        else if(i==3){
            if(x<=5){
                sprites.get(24).draw(hitbox.getX(), hitbox.getY());
            }
            else if(x<=10){
                sprites.get(25).draw(hitbox.getX(),hitbox.getY());
          
            }
            else if(x<=15){
                sprites.get(26).draw(hitbox.getX(),hitbox.getY());
                
            }
            else if(x<=20){
                sprites.get(27).draw(hitbox.getX(),hitbox.getY());
            }
            else
                sprites.get(24).draw(hitbox.getX(),hitbox.getY());
            attackhitbox.setBounds(hitbox.getX()+imgwidth,hitbox.getY(),10,imgheight);
                
        }
        else if(i==4){
            if(x<=5){
                sprites.get(36).draw(hitbox.getX(), hitbox.getY());
            }
            else if(x<=10){
                sprites.get(37).draw(hitbox.getX(),hitbox.getY());
          
            }
            else if(x<=15){
                sprites.get(38).draw(hitbox.getX(),hitbox.getY());
                
            }
            else if(x<=20){
                sprites.get(39).draw(hitbox.getX(),hitbox.getY());
            }
            else
                sprites.get(36).draw(hitbox.getX(),hitbox.getY());
            attackhitbox.setBounds(hitbox.getX()-10,hitbox.getY(),10,imgheight);
                
        }
        else if(i==5){
            if(z<=5){
                sprites.get(5).draw(hitbox.getX(), hitbox.getY());
            }
            else if(z<=10){
                sprites.get(6).draw(hitbox.getX(),hitbox.getY());
          
            }
            else if(z<=15){
                sprites.get(7).draw(hitbox.getX(),hitbox.getY());
                
            }
            else if(z<=20){
                sprites.get(8).draw(hitbox.getX(),hitbox.getY());
            }
            else
                sprites.get(5).draw(hitbox.getX(),hitbox.getY());
        }
        else if(i==6){
            if(z<=5){
                sprites.get(16).draw(hitbox.getX(), hitbox.getY());
            }
            else if(z<=10){
                sprites.get(17).draw(hitbox.getX(),hitbox.getY());
          
            }
            else if(z<=15){
                sprites.get(18).draw(hitbox.getX(),hitbox.getY());
                
            }
            else if(z<=20){
                sprites.get(19).draw(hitbox.getX(),hitbox.getY());
            }
            else
                sprites.get(16).draw(hitbox.getX(),hitbox.getY());
        }
        else if(i==7){
             if(z<=5){
                sprites.get(28).draw(hitbox.getX(), hitbox.getY());
            }
            else if(z<=10){
                sprites.get(29).draw(hitbox.getX(),hitbox.getY());
          
            }
            else if(z<=15){
                sprites.get(30).draw(hitbox.getX(),hitbox.getY());
                
            }
            else if(z<=20){
                sprites.get(31).draw(hitbox.getX(),hitbox.getY());
            }
            else
                sprites.get(28).draw(hitbox.getX(),hitbox.getY());
        }
        else if(i==8){
            if(z<=5){
                sprites.get(40).draw(hitbox.getX(), hitbox.getY());
            }
            else if(z<=10){
                sprites.get(41).draw(hitbox.getX(),hitbox.getY());
          
            }
            else if(z<=15){
                sprites.get(42).draw(hitbox.getX(),hitbox.getY());
                
            }
            else if(z<=20){
                sprites.get(43).draw(hitbox.getX(),hitbox.getY());
            }
            else
                sprites.get(40).draw(hitbox.getX(),hitbox.getY());
        }
        else{
            sprites.get(0).draw(hitbox.getX(),hitbox.getY());
        }
        
    }
    
    public float getXPos(){
        return hitbox.getX();
    }
    public float getYPos(){
        return hitbox.getY();
    }
    public float getYSpeed(){
        return ySpeed;
    }
    public float getXSpeed(){
        return xSpeed;
    }
    public void setFactor(int i){
        factor = i;
    }
    public boolean isMovePause(){
        return isMovePause;
    }
    public void setMovePause(boolean i){
        isMovePause = i;
    }
    public boolean isAttackDelay(){
        return attackDelay;
    }
    public void setAttackDelay(boolean b){
        attackDelay = b;
    }
    
    public int getEnemyCount(){
        return enemycount;
    }
    public void setEnemyCount(int i){
        enemycount = i;
    }
    public int getEnemyTimer(){
        return enemytimer;
    }
    public void setEnemyTimer(int i){
        enemytimer = i;
    }
    public int getEnemyMoveTimer(){
        return enemymovetimer;
    }
    public void setEnemyMoveTimer(int i){
        enemymovetimer = i;
    }
    public int getEnemyAttackTimer(){
        return enemyattacktimer;
    }
    public void setEnemyAttackTimer(int i){
        enemyattacktimer = i;
    }
    public int getEnemyHitTimer(){
        return enemyhittimer;
    }
    public void setEnemyHitTimer(int i){
        enemyhittimer = i;
    }

    
    


    
    
    
}
