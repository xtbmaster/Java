package Skeleton;

import Intermedia.Menu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Game game;
    Menu menu;

    public KeyInput(Game game, Menu menu){
        this.game = game;
        this.menu = menu;
    }

    public void keyPressed(KeyEvent e){
        game.keyPressed(e);
        menu.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        game.keyReleased(e);
    }

}
