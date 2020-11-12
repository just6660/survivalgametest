
import java.awt.Button;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class mainGame extends BasicGameState {

    ArrayList<Enemy> enemies;
    Player p;

    int timer;

    

    
   

    
    int wave = 1;
    
    Rectangle redhp = new Rectangle(0, 1000, 1680, 50);
    Rectangle greenhp = new Rectangle(0, 1000, 1680, 50);
    boolean newwave = true;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        enemies = new ArrayList();
        p = new Player();

        timer = 0;
        Enemy.setGameSize(1680, 1050);
        Player.setGameSize(1680, 1050);

        for (int x = 0; x < 3; x++) {
            int z = (int) (Math.random() * 2 + 1);
            if (z == 1) {
                int rx = (int) (Math.random() * (1680 - 1550) + 1550);
                int ry = (int) (Math.random() * (100 - 0) + 0);
                enemies.add(new Enemy(rx, ry));
            } else if (z == 2) {
                int rx = (int) (Math.random() * (100 - 0) + 0);
                int ry = (int) (Math.random() * (100 - 0) + 0);
                enemies.add(new Enemy(rx, ry));
            }

        }
        

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input in = gc.getInput();

        if (enemies.size() == 0) {
            newwave = true;
            wave++;
            for (int x = 0; x < 3; x++) {
                int z = (int) (Math.random() * 2 + 1);
                if (z == 1) {
                    int rx = (int) (Math.random() * (1680 - 1550) + 1550);
                    int ry = (int) (Math.random() * (100 - 0) + 0);
                    enemies.add(new Enemy(rx, ry));
                } else if (z == 2) {
                    int rx = (int) (Math.random() * (100 - 0) + 0);
                    int ry = (int) (Math.random() * (100 - 0) + 0);
                    enemies.add(new Enemy(rx, ry));
                }

            }

        }
        if(wave == 1 && newwave){
            for(Enemy a:enemies){
                a.setDamage(10);
                a.setHealth(50);
                p.setHealth(100);
                p.setDamage(50);
            }
            newwave = false;
        }
        if(wave == 2 && newwave){
            for(Enemy a:enemies){
                a.setDamage(30);
                a.setHealth(100);
                p.setHealth(100);
                
            }
            newwave = false;
        }
        if(wave == 3 && newwave){
            for(Enemy a : enemies){
                a.setDamage(50);
                a.setHealth(120);
                p.setHealth(100);
                p.setDamage(40);
            }
            newwave = false;
        }
        

        for (Enemy a : enemies) {
            if (a.isMovePause() == false) {
                a.move(p.getXPos(), p.getYPos());
            }
        }

        if (enemies.size() > 0) {
            for (Enemy a : enemies) {
                if (in.isKeyDown(Input.KEY_SPACE) && p.isCooldown() == false) {
                    if (p.getPlayerCount() == 1) {
                        p.setPlayerCount(5);
                    } else if (p.getPlayerCount() == 2) {
                        p.setPlayerCount(6);
                    } else if (p.getPlayerCount() == 3) {
                        p.setPlayerCount(7);
                    } else if (p.getPlayerCount()== 4) {
                        p.setPlayerCount(8);
                    }
                }

                if (in.isKeyDown(Input.KEY_SPACE) && p.hit(a.getHitBox()) && p.isCooldown() == false) {

                    a.takeDamage(p.getDamage());
                    if (a.getHealth() == 0) {
                        enemies.remove(a);
                    }
                    p.setCooldown(true);
                    break;
                }
            }
        }
        if (in.isKeyDown(Input.KEY_SPACE)) {
            p.setCooldown(true);
        }

        for (Enemy a : enemies) {

            if (a.hit(p.getHitBox()) && a.isCooldown() == false && a.isMovePause() == false) {
                a.setMovePause(true);
                a.setAttackDelay(true);

            }
        }

        if (p.getPlayerMoveTimer()>= 20) {
            p.setPlayerMoveTimer(0);
        }
        if (p.getPlayerAttackAnimationTimer() >= 20) {
            p.setPlayerAttackAnimationTimer(0);
        }
        for(Enemy a : enemies){
        if (a.getEnemyMoveTimer() >= 20) {
            a.setEnemyMoveTimer(0);
        }
        }
        for(Enemy a:enemies){
        if (a.getEnemyHitTimer()>= 20) {
            a.setEnemyHitTimer(0);

        }
        }

        

        for (Enemy a : enemies) {
            a.setEnemyAttackTimer(a.getEnemyAttackTimer()+1);
            if (a.getEnemyAttackTimer() > 50) {
                a.setEnemyAttackTimer(0);
                a.setAttackDelay(false);
                a.setMovePause(false);
            }
        }

        for (Enemy a : enemies) {
            if (a.hit(p.getHitBox()) && a.isAttackDelay() == false && a.isCooldown() == false) {
                if (a.getEnemyCount() == 1) {

                    a.setEnemyCount(5);
                } else if (a.getEnemyCount() == 2) {
                    a.setEnemyCount(6);
                } else if (a.getEnemyCount()== 3) {
                    a.setEnemyCount(7);
                } else if (a.getEnemyCount() == 4) {
                    a.setEnemyCount(8);
                }

                p.takeDamage(a.getDamage());

                a.setCooldown(true);

            }
        }
        
        for (Enemy a : enemies) {
            a.setEnemyTimer(a.getEnemyTimer()+1);
            if (a.getEnemyTimer() >= 600) {
                a.setEnemyTimer(0);
                a.setCooldown(false);

            }
        }
        p.setPlayerHitTimer(p.getPlayerHitTimer()+1);
        if (p.getPlayerHitTimer() >= 200) {
            p.setPlayerHitTimer(0);
            p.setCooldown(false);
        }

        //player movements
        if (in.isKeyDown(Input.KEY_SPACE)) {
            if (!p.hitwall()) {
                p.setPlayerAttackAnimationTimer(p.getPlayerAttackAnimationTimer()+1);

            }
        } else if (in.isKeyDown(Input.KEY_W) && in.isKeyDown(Input.KEY_D)) {
            if (!p.hitwall()) {
                p.setPlayerMoveTimer(p.getPlayerMoveTimer()+1);
                p.setPlayerCount(2);
                p.move(5);
            }

        } else if (in.isKeyDown(Input.KEY_W) && in.isKeyDown(Input.KEY_A)) {
            if (!p.hitwall()) {
                p.setPlayerMoveTimer(p.getPlayerMoveTimer()+1);
                p.setPlayerCount(2);
                p.move(6);
            }
        } else if (in.isKeyDown(Input.KEY_S) && in.isKeyDown(Input.KEY_D)) {
            if (!p.hitwall()) {
                p.setPlayerMoveTimer(p.getPlayerMoveTimer()+1);
                p.setPlayerCount(1);
                p.move(7);
            }
        } else if (in.isKeyDown(Input.KEY_S) && in.isKeyDown(Input.KEY_A)) {
            if (!p.hitwall()) {
                p.setPlayerMoveTimer(p.getPlayerMoveTimer()+1);
                p.setPlayerCount(1);
                p.move(8);
            }
        } else if (in.isKeyDown(Input.KEY_S)) {
            if (!p.hitwall()) {
                p.setPlayerMoveTimer(p.getPlayerMoveTimer()+1);
                p.setPlayerCount(1);
                p.move(p.getPlayerCount());
            }
        } else if (in.isKeyDown(Input.KEY_W)) {
            if (!p.hitwall()) {
                p.setPlayerMoveTimer(p.getPlayerMoveTimer()+1);
                p.setPlayerCount(2);
                p.move(p.getPlayerCount());
            }

        } else if (in.isKeyDown(Input.KEY_D)) {
            if (!p.hitwall()) {
                p.setPlayerMoveTimer(p.getPlayerMoveTimer()+1);
                p.setPlayerCount(3);
                p.move(p.getPlayerCount());
            }
        } else if (in.isKeyDown(Input.KEY_A)) {
            if (!p.hitwall()) {
                p.setPlayerMoveTimer(p.getPlayerMoveTimer()+1);
                p.setPlayerCount(4);
                p.move(p.getPlayerCount());
            }
        }

        //enemy movments
        for (Enemy a : enemies) {

            if (a.hit(p.getHitBox())) {
                a.setEnemyHitTimer(a.getEnemyHitTimer()+1);
            } else if (!a.hitwall() && a.getYSpeed() < 0 && Math.abs(a.getYSpeed()) > Math.abs(a.getXSpeed()) && a.isMovePause() == false) {
                a.setEnemyMoveTimer(a.getEnemyMoveTimer()+1);
                a.setEnemyCount(4);
            } else if (!a.hitwall() && a.getYSpeed() > 0 && Math.abs(a.getYSpeed()) > Math.abs(a.getXSpeed()) && a.isMovePause() == false) {
                a.setEnemyMoveTimer(a.getEnemyMoveTimer()+1);
                a.setEnemyCount(1);
            } else if (!a.hitwall() && a.getXSpeed() < 0 && a.isMovePause() == false) {
                a.setEnemyMoveTimer(a.getEnemyMoveTimer()+1);
                a.setEnemyCount(2);
            } else if (!a.hitwall() && a.getXSpeed() > 0 && a.isMovePause() == false) {
                a.setEnemyMoveTimer(a.getEnemyMoveTimer()+1);
                a.setEnemyCount(3);
            }

        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.red);
        g.drawString("This is the main game", 100, 200);

        for (Enemy a : enemies) {
            a.draw(a.getEnemyCount(), a.getEnemyMoveTimer(), a.getEnemyHitTimer());
        }
        if (p.isCooldown() == true) {
            g.setColor(Color.red);
            g.fill(p.getAttackHitbox());
        }
        if (p.isCooldown() == false) {
            g.setColor(Color.green);
            g.fill(p.getAttackHitbox());
        }
        p.draw(p.getPlayerCount(), p.getPlayerMoveTimer(), p.getPlayerAttackAnimationTimer());
        g.setColor(Color.red);
        g.fill(redhp);
        greenhp = new Rectangle(0, 1000, 1680 * p.getHealth() / 100, 50);
        g.setColor(Color.green);
        g.fill(greenhp);

    }

    public int getID() {
        return 1;  //this id will be different for each screen
    }

}
