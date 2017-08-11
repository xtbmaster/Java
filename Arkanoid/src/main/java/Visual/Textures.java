package Visual;

import Skeleton.Game;

import java.awt.image.BufferedImage;

public class Textures {

    public BufferedImage[] player = new BufferedImage[3];
    public BufferedImage[] bullet = new BufferedImage[1];
    public BufferedImage[] enemy = new BufferedImage[3];
    public BufferedImage[] stuff = new BufferedImage[1];
    private SpriteSheet ss;

    public Textures(Game game){
        ss = new SpriteSheet(game.getSpriteSheet());
        getTextures();
    }

    private void getTextures(){
        player[0] = ss.grabImage(1, 2, 32, 32);
        player[1] = ss.grabImage(2, 2, 32, 32);
        player[2] = ss.grabImage(3, 2, 32, 32);

        bullet[0] = ss.grabImage(3, 1, 32, 32);

        enemy[0] = ss.grabImage(4, 1, 32, 32);
        enemy[1] = ss.grabImage(5, 1, 32, 32);
        enemy[2] = ss.grabImage(6, 1, 32, 32);

        stuff[0] = ss.grabImage(6, 2, 32, 32);
    }
}
