package Objects;

import Entity.*;
import Intermedia.Sound;
import Skeleton.*;
import Visual.*;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject implements EntityEnemy {

    private Random r;
    private Game game;
    private Controller c;

    private int speed;

    Animation anim;
    private Textures tex;

    public Enemy(double x, double y, Textures tex, Controller c, Game game){
        super(x, y);
        this.tex = tex;
        this.c = c;
        this.game = game;

        anim = new Animation(5, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
        r = new Random();
        speed = r.nextInt(3) + 1;
    }

    @Override
    public void tick(){
        y += speed;
        if (y > Game.HEIGHT){
            speed = r.nextInt(3) + 1;
            y = -10;
            x = r.nextInt(640);

            Game.HEALTH -= 10;

            new Sound(Sound.SoundState.HIT, false);
        }

        for(int i = 0; i < Game.entPlayer.size(); i++){
            EntityPlayer tempEnt = Game.entPlayer.get(i);

            if(Physics.collision(this, tempEnt)){
                new Sound(Sound.SoundState.HIT, false);
                c.removeEntity(tempEnt);
                c.removeEntity(this);
                Game.enemyKilledCount++;
                game.setEnemyKilled(game.getEnemyKilled() + 1);
            }
        }
        anim.runAnimation();
    }

    @Override
    public void render(Graphics g){
        anim.drawAnimation(g, x, y, 0);
    }
    @Override
    public Rectangle getBounds(){ return new Rectangle((int) x, (int) y, 32, 32); }
    @Override
    public double getX() {
        return x;
    }
    @Override
    public double getY(){ return y; }

}
