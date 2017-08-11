package Objects;

import Entity.EntityPlayer;
import Visual.Textures;

import java.awt.*;

public class Bullet extends GameObject implements EntityPlayer {

    private Textures tex;

    public Bullet(double x, double y, Textures tex){
        super(x, y);
        this.tex = tex;
    }

    @Override
    public void tick(){
        y -= 10;
    }

    @Override
    public void render(Graphics g){
       g.drawImage(tex.bullet[0],(int) x, (int) y, null);
    }

    @Override
    public double getX() {
        return x;
    }
    @Override
    public double getY(){
        return y;
    }
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
