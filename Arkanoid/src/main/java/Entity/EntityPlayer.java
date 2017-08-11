package Entity;

import java.awt.*;

public interface EntityPlayer {

    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double getX();
    public double getY();


}
