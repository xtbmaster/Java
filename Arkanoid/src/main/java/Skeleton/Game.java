package Skeleton;

import Entity.*;
import Intermedia.Menu;
import Intermedia.Sound;
import Objects.*;
import Visual.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int FRAMEPERSECOND = 60;
    public static final int BUFFEREDSCREEN = 3;
    public static final int FIRSTAID = 20;
    public static final int DEFAULTHEALTH = 200;

    public final String TITLE = "Alien Wave";

    private boolean running = false;
    private Thread thread;

    public static Sound soundGame;
    public static Sound soundMenu;
    public static Sound soundGameOver;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private boolean isShooting = false;

    private static int enemyCount = 5;
    private static int stuffCount = 1;
    public static int enemyKilled = 0;
    public static int enemyKilledCount = 0;
    public static int level = 1;
    public static int record = 0;

    private static Player p;
    private static Controller c;
    private Textures tex;
    private Menu menu;

    public static LinkedList<EntityPlayer> entPlayer;
    public static LinkedList<EntityEnemy> entEnemy;
    public static LinkedList<EntityStuff> entStuff;

    public static int HEALTH = DEFAULTHEALTH;

    public static GameState state = GameState.MENU;

    private BufferedImage player;

    public void init(){

        soundMenu = new Sound(Sound.SoundState.MENU, true);
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
        spriteSheet = loader.loadImage("/file/spritesheet.png");
            background = loader.loadImage("/file/background.jpg");

        }catch(IOException e){
            e.printStackTrace();
        }

        tex = new Textures(this);

        c = new Controller(tex, this);
        p = new Player(200, 200, tex, this, c);
        menu = new Menu();

        entPlayer = c.getEntityPlayer();
        entEnemy = c.getEntityEnemy();
        entStuff = c.getEntityStuff();

        this.addKeyListener(new KeyInput(this, menu));

        c.createEnemy(enemyCount);
        c.createStuff(stuffCount);
    }

    public synchronized void start(){

        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!running)
            return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void run(){
            init();
            long lastTime = System.nanoTime();
            int billion = 1_000_000_000;
            double ns = billion / (double) FRAMEPERSECOND;
            double delta = 0;
            int updates = 0;
            int frames = 0;
            long timer = System.currentTimeMillis();

        while (running){

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }

            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){

        if(state == GameState.GAME) {
            p.tick();
            c.tick();
        }

        if(enemyKilled >= enemyCount){
            new Sound(Sound.SoundState.NEWWAVE, false);
            level += 1;
            enemyCount += 2;
            enemyKilled = 0;
            c.createEnemy(enemyCount);
            c.createStuff(stuffCount);
        }
    }

    private void render(){

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null){
            createBufferStrategy(BUFFEREDSCREEN);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(player, 100, 100, this);

        g.drawImage(background, 0, 0, null);

        if(state == GameState.GAME) {

            p.render(g);
            c.render(g);

            g.setColor(Color.GREEN);
            g.fillRect(5, 5, HEALTH, 10);
            g.setColor(Color.WHITE);
            g.drawRect(5, 5, 200, 10);

            g.setFont(new Font("arial", Font.BOLD, 14));
            g.setColor(Color.WHITE);
            g.drawString("ALIENS: " + enemyKilledCount, 5, 32);
            g.drawString("LEVEL: " + level, 5, 48);

        }

        if (Game.HEALTH <= 0 | state == GameState.GAMEOVER) {
           menu.drawGameOverMenu(g);
        }

        if (state == GameState.MENU){
            menu.drawMenu(g);
        }

        if (state == GameState.HELP){
            menu.drawHelp(g);
        }

        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(state == GameState.GAME) {

            if (key == KeyEvent.VK_RIGHT | key == KeyEvent.VK_D) {
                p.setVelX(5);

            } else if (key == KeyEvent.VK_LEFT | key == KeyEvent.VK_A) {
                p.setVelX(-5);
            } else if (key == KeyEvent.VK_UP | key == KeyEvent.VK_W) {
                p.setVelY(-5);
            } else if (key == KeyEvent.VK_DOWN | key == KeyEvent.VK_S) {
                p.setVelY(+5);
            } else if ((key == KeyEvent.VK_SPACE) && !isShooting) {
                new Sound(Sound.SoundState.SHOT, false);
                isShooting = true;
                c.addEntity(new Bullet(p.getX(), p.getY() + 20, tex));
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT | key == KeyEvent.VK_D){
            p.setVelX(0);
        } else if (key == KeyEvent.VK_LEFT | key == KeyEvent.VK_A){
            p.setVelX(0);
        } else if (key == KeyEvent.VK_UP | key == KeyEvent.VK_W){
            p.setVelY(0);
        } else if (key == KeyEvent.VK_DOWN | key == KeyEvent.VK_S){
            p.setVelY(0);
        } else if (key == KeyEvent.VK_SPACE){
            isShooting = false;
        }
    }

    public static void refresh(){

        HEALTH = DEFAULTHEALTH;
        level = 1;
        enemyCount = 5;
        enemyKilled = 0;
        enemyKilledCount = 0;
        stuffCount = 1;


        entPlayer.clear();
        entEnemy.clear();
        entStuff.clear();

        entPlayer = c.getEntityPlayer();
        entEnemy = c.getEntityEnemy();
        entStuff = c.getEntityStuff();



        c.createEnemy(enemyCount);
        c.createStuff(stuffCount);



    }
    public static void main(String[] args) {

        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        game.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();

    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
    public int getEnemyKilled() {
        return enemyKilled;
    }
    public void setEnemyKilled(int enemyKilled) {
        Game.enemyKilled = enemyKilled;
    }
}
