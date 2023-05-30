package core;

import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(GameEngine.screenWidth / 2 + 150, 150, 200, 50);
    public Rectangle highScoreButton = new Rectangle(GameEngine.screenWidth / 2 + 150, 250, 200, 50);
    public Rectangle settingsButton = new Rectangle(GameEngine.screenWidth / 2 + 150, 350, 200, 50);
    public Rectangle quitButton = new Rectangle(GameEngine.screenWidth / 2 + 150, 450, 200, 50);

    public void render(Graphics g){

        Graphics2D g2 = (Graphics2D) g;

        Font font = new Font("Bauhaus 93", Font.BOLD, 80);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("DUCK SHOOTER", GameEngine.screenWidth / 3, 100);


        Font font1 = new Font("Bauhaus 93", Font.BOLD, 30);
        g.setFont(font1);

        g.setColor(Color.black);
        g.fillRect(GameEngine.screenWidth / 2 + 150, 150, 200, 50);

        g.setColor(Color.white);
        g.drawString("PLAY", playButton.x + 70, playButton.y  + 35);
        g2.draw(playButton);

        g.setColor(Color.black);
        g.fillRect(GameEngine.screenWidth / 2 + 150, 250, 200, 50);

        g.setColor(Color.white);
        g.drawString("HIGHSCORE", highScoreButton.x + 20, highScoreButton.y  + 35);
        g2.draw(highScoreButton);

        g.setColor(Color.black);
        g.fillRect(GameEngine.screenWidth / 2 + 150, 350, 200, 50);

        g.setColor(Color.white);
        g.drawString("SETTINGS", settingsButton.x + 45, settingsButton.y  + 35);
        g2.draw(settingsButton);

        g.setColor(Color.black);
        g.fillRect(GameEngine.screenWidth / 2 + 150, 450, 200, 50);

        g.setColor(Color.white);
        g.drawString("QUIT", quitButton.x + 70, quitButton.y  + 35);
        g2.draw(quitButton);

    }
}
