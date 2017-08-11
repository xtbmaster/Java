package Objects;

import Entity.*;
import Intermedia.Sound;
import Skeleton.*;
import Visual.*;

import java.awt.*;

import static Skeleton.Game.*;

public class Player extends GameObject implements EntityPlayer {

    private double velX = 0;
    private double velY = 0;

    private Textures tex;

    Game game;
    Animation anim;
    Controller c;

    public Player(double x, double y, Textures tex, Game game, Controller c) {

        super(x, y);
        this.tex = tex;
        this.game = game;
        this.c = c;
        anim = new Animation(5, tex.player[0], tex.player[1], tex.player[2]);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0)
            x = 0;
        if (x >= 640 - 20)
            x = 640 - 20;
        if (y <= 0)
            y = 0;
        if (y >= 480 - 32)
            y = 480 - 32;

        for(int i = 0; i < entEnemy.size(); i++){
            EntityEnemy tempEnt = entEnemy.get(i);
            if (Physics.collision(this, tempEnt)){
                new Sound(Sound.SoundState.CRASH, false);
                c.removeEntity(tempEnt);
                HEALTH -= 20;

                game.setEnemyKilled(game.getEnemyKilled() + 1);
                enemyKilledCount++;
            }
        }

        for(int i = 0; i < entStuff.size(); i++){
            EntityStuff tempEntStuff = entStuff.get(i);
            if (Physics.collision(this, tempEntStuff)){
                new Sound(Sound.SoundState.AID, false);
                c.removeEntity(tempEntStuff);
                if(HEALTH + FIRSTAID > DEFAULTHEALTH) {
                    HEALTH = DEFAULTHEALTH;
                }
                else HEALTH += FIRSTAID;

            }
        }

        if (HEALTH <= 0 && state == GameState.GAME)
        {
            if (record < enemyKilledCount)
                record = enemyKilledCount;

            new Sound(Sound.SoundState.EXPLOSION, false);
            soundGame.stopSound();
            soundGameOver = new Sound(Sound.SoundState.GAMEOVER, true);
            state = GameState.GAMEOVER;

        }

        anim.runAnimation();
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    @Override
    public void render(Graphics g) {
        anim.drawAnimation(g, x, y, 0);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setVelX(double velX){
        this.velX = velX;
    }

    public void setVelY(double velY){
        this.velY = velY;
    }
}