package Objects;

import Entity.*;
import Intermedia.Sound;
import Skeleton.*;
import Visual.Textures;

import java.awt.*;
import java.util.Random;

public class Stuff extends GameObject implements EntityStuff {

    private Random r;
    private Game game;
    private Controller c;

    private int speed;

    private Textures tex;

    public Stuff(double x, double y, Textures tex, Controller c, Game game){
        super(x, y);
        this.tex = tex;
        this.c = c;
        this.game = game;

        r = new Random();
        speed = r.nextInt(3) + 1;
    }

    @Override
    public void tick(){
        y += speed;
        if (y > Game.HEIGHT){
            c.removeEntity(this);
        }

        for(int i = 0; i < Game.entPlayer.size(); i++){
            EntityPlayer tempEnt = Game.entPlayer.get(i);

            if(Physics.collision(this, tempEnt)){
                new Sound(Sound.SoundState.HIT, false);
                c.removeEntity(tempEnt);
                c.removeEntity(this);
            }
        }
    }

    @Override
    public void render(Graphics g){
        g.drawImage(tex.stuff[0], (int) x, (int) y, null);
    }
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 32, 32);
    }
    @Override
    public double getX() {
        return x;
    }
    @Override
    public double getY(){
        return y;
    }

}
