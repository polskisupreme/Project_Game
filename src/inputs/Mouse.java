package inputs;

import core.GameEngine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (GameEngine.state == GameEngine.STATE.MENU){
        if (mx >= GameEngine.screenWidth / 2 + 150 && mx <= GameEngine.screenWidth / 2 + 250){
            if (my >= 150 && my <= 200){
                GameEngine.state = GameEngine.STATE.GAME;
            }
        }
        if (mx >= GameEngine.screenWidth / 2 + 150 && mx <= GameEngine.screenWidth / 2 + 250){
            if (my >= 250 && my <= 300){
                GameEngine.state = GameEngine.STATE.HIGHSCORE;
            }
        }
        if (mx >= GameEngine.screenWidth / 2 + 150 && mx <= GameEngine.screenWidth / 2 + 250){
            if (my >= 350 && my <= 400){
                GameEngine.state = GameEngine.STATE.SETTINGS;
            }
        }
        if (mx >= GameEngine.screenWidth / 2 + 150 && mx <= GameEngine.screenWidth / 2 + 250){
            if (my >= 450 && my <= 500){
                System.exit(0);
            }
        }}

        if (GameEngine.state == GameEngine.STATE.HIGHSCORE || GameEngine.state == GameEngine.STATE.SETTINGS)
        if (mx >= GameEngine.screenWidth / 2 + 430 && mx <= GameEngine.screenWidth / 2 + 530){
            if (my >= 450 && my <= 500){
                GameEngine.state = GameEngine.STATE.MENU;
            }
        }
    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
}
