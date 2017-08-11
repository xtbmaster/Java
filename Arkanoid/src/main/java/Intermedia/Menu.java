package Intermedia;

import Skeleton.Game;
import Skeleton.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import static Skeleton.Game.*;

public class Menu {

    private String[] menuOptions = {"NEW GAME", "HELP", "QUIT"};
    private String[] gameOverMenuOptions = {"PLAY AGAIN", "MENU"};
    private int currentSelectionMenu = 0;
    private int currentSelectionGameOverMenu = 0;


    public void drawGameOverMenu(Graphics g){

        g.setFont(new Font("Diagonal", Font.BOLD, 45));
        g.setColor(Color.WHITE);
        g.drawString("GAME OVER", 190, 173);

        g.setFont(new Font("Diagonal", Font.BOLD, 28));
        g.setColor(Color.WHITE);
        g.drawString("KILLED : " + enemyKilledCount + "       RECORD : " + record, 140, 230);

        for(int i = 0; i < gameOverMenuOptions.length; i++){
            if (i == currentSelectionGameOverMenu){
                g.setColor(Color.ORANGE);
            }
            else g.setColor(Color.WHITE);
            g.setFont(new Font("Diagonal", Font.BOLD, 27));
            g.drawString(gameOverMenuOptions[i], 75, 320 + i * 40);
        }

    }

    public void drawMenu(Graphics g){

        g.setFont(new Font("Diagonal", Font.BOLD, 45));
        g.setColor(Color.WHITE);
        g.drawRect(70, 130, 440, 55);
        g.drawString("A L I E N | W A V E", 92, 173);

        for(int i = 0; i < menuOptions.length; i++){
            if (i == currentSelectionMenu){
                g.setColor(Color.ORANGE);
            }
            else g.setColor(Color.WHITE);
            g.setFont(new Font("Diagonal", Font.BOLD, 27));
            g.drawString(menuOptions[i], 75, 275 + i * 40);
        }

    }

    public void drawHelp(Graphics g){

        g.setFont(new Font("Diagonal", Font.BOLD, 25));
        g.setColor(Color.WHITE);
        g.drawRect(70, 115, 500, 270);
        g.drawString("To move press :", 92, 150);
        g.drawString("A, W, S, D or LEFT, UP, DOWN, RIGHT", 92, 190);
        g.drawString("To shoot press :", 92, 255);
        g.drawString("SPACE", 92, 295);
        g.drawString("Press ESC to exit", 92, 360);

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN) {
            currentSelectionMenu++;
            currentSelectionGameOverMenu++;

            if (currentSelectionMenu >= menuOptions.length) {
                currentSelectionMenu = 0;
            }
            if (currentSelectionGameOverMenu >= gameOverMenuOptions.length) {
                currentSelectionGameOverMenu = 0;
            }

        } else if (key == KeyEvent.VK_UP) {
            currentSelectionMenu--;
            currentSelectionGameOverMenu--;
            if (currentSelectionMenu < 0) {
                currentSelectionMenu = menuOptions.length - 1;
            }
            if (currentSelectionGameOverMenu < 0) {
                currentSelectionGameOverMenu = gameOverMenuOptions.length - 1;
            }
        }
        if (key == KeyEvent.VK_ENTER | key == KeyEvent.VK_SPACE) {
            if (state == GameState.MENU) {
                if (currentSelectionMenu == 0) {
                    soundMenu.stopSound();
                    soundGame = new Sound(Sound.SoundState.GAME, true);
                    state = GameState.GAME;
                } else if (currentSelectionMenu == 1){
                    state = GameState.HELP;
                }
                else if (currentSelectionMenu == 2) {
                    System.exit(0);
                }
            }

            if (state == GameState.GAMEOVER) {
                refresh();
                if (currentSelectionGameOverMenu == 0) {
                    soundGameOver.stopSound();
                    soundGame = new Sound(Sound.SoundState.GAME, true);
                    state = GameState.GAME;
                } else if (currentSelectionGameOverMenu == 1) {
                    soundGameOver.stopSound();
                    soundMenu = new Sound(Sound.SoundState.MENU, true);
                    state = GameState.MENU;
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            if (state == GameState.HELP) {
                state = GameState.MENU;
                currentSelectionMenu = 0;
            }
        }
    }
}
