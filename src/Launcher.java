import core.GameEngine;

import javax.swing.*;
import java.awt.*;

import static core.GameEngine.*;

public class Launcher {

    public static void main(String[] args){

        GameEngine gameEngine = new GameEngine();

        gameEngine.setPreferredSize(new Dimension(screenWidth * scale, screenHeight * scale));
        gameEngine.setMaximumSize(new Dimension(screenWidth * scale, screenHeight * scale));
        gameEngine.setMinimumSize(new Dimension(screenWidth * scale, screenHeight * scale));

        JFrame frame = new JFrame("Duck Shooter");

        frame.add(gameEngine);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gameEngine.startThread();

    }

}
