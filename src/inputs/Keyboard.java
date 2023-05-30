package inputs;

import core.GameEngine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {

    GameEngine gameEngine;

    public Keyboard(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    public void keyPressed(KeyEvent e){
       gameEngine.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        gameEngine.keyReleased(e);
    }
}
