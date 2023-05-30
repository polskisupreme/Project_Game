package core;

import java.awt.*;

public class HighScore {

    public Rectangle backButton = new Rectangle(GameEngine.screenWidth / 2 + 430, 450, 150, 50);


    public void render(Graphics g){

        Font font = new Font("Bauhaus 93", Font.BOLD, 80);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("DUCK SHOOTER", GameEngine.screenWidth / 3, 100);

        Font f = new Font("Bauhaus 93", Font.BOLD, 40);
        g.setFont(f);
        g.drawString("WORK", GameEngine.screenWidth +50, 250);
        g.drawString("IN", GameEngine.screenWidth +90, 300);
        g.drawString("PROGRESS", GameEngine.screenWidth + 20, 350);

        Graphics2D g2 = (Graphics2D) g;
        Font font1 = new Font("Bauhaus 93", Font.BOLD, 30);
        g.setFont(font1);

        g.setColor(Color.black);
        g.fillRect(GameEngine.screenWidth / 2 + 430, 450, 150, 50);

        g.setColor(Color.white);
        g.drawString("BACK", backButton.x + 40, backButton.y  + 35);
        g2.draw(backButton);

    }

}
