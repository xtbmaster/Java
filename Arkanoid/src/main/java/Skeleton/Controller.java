package Skeleton;

import Entity.*;
import Objects.Enemy;
import Objects.Stuff;
import Visual.Textures;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * The class manages game objects;
 */
public class Controller {

    private LinkedList<EntityPlayer> eP;
    private LinkedList<EntityEnemy> eE;
    private LinkedList<EntityStuff> eS;

    EntityPlayer entityPlayer;
    EntityEnemy entityEnemy;
    EntityStuff entityStuff;

    private Textures tex;
    private Random r;

    private Game game;

    public Controller(Textures tex, Game game){
        this.tex = tex;
        this.game = game;
        eE = new LinkedList<>();
        eP = new LinkedList<>();
        eS = new LinkedList<>();
        r = new Random();
    }

    public void createEnemy(int enemyCount){
        for(int i = 0; i < enemyCount; i++){
            addEntity(new Enemy(r.nextInt(640), -10, tex, this, game));
        }
    }

    public void createStuff(int stuffCount){
        for(int i = 0; i < stuffCount; i++){
            addEntity(new Stuff(r.nextInt(640), -10, tex, this, game));
        }
    }

    public void tick(){
        for(int i = 0; i < eP.size(); i++){
            entityPlayer = eP.get(i);
            entityPlayer.tick();
        }
        for(int i = 0; i < eE.size(); i++){
            entityEnemy = eE.get(i);
            entityEnemy.tick();
        }
        for(int i = 0; i < eS.size(); i++){
            entityStuff = eS.get(i);
            entityStuff.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < eP.size(); i++){
            entityPlayer = eP.get(i);
            entityPlayer.render(g);
        }
        for(int i = 0; i < eE.size(); i++){
            entityEnemy = eE.get(i);
            entityEnemy.render(g);
        }
        for(int i = 0; i < eS.size(); i++){
            entityStuff = eS.get(i);
            entityStuff.render(g);
        }
    }

    public void addEntity(EntityPlayer item){ eP.add(item); }
    public void addEntity(EntityStuff item){ eS.add(item); }
    public void addEntity(EntityEnemy item){ eE.add(item); }

    public void removeEntity(EntityPlayer item){ eP.remove(item); }
    public void removeEntity(EntityEnemy item){ eE.remove(item); }
    public void removeEntity(EntityStuff item){
        eS.remove(item);
    }

    public LinkedList<EntityPlayer> getEntityPlayer(){
        return eP;
    }
    public LinkedList<EntityEnemy> getEntityEnemy(){
        return eE;
    }
    public LinkedList<EntityStuff> getEntityStuff(){
        return eS;
    }
}