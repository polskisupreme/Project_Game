package core;

import java.awt.*;

public class Settings {

    public Rectangle musicOn = new Rectangle(GameEngine.screenWidth / 2 + 150, 150, 200, 50);
    public Rectangle musicOff = new Rectangle(GameEngine.screenWidth / 2 + 150, 250, 200, 50);
    public Rectangle backButton = new Rectangle(GameEngine.screenWidth / 2 + 430, 450, 150, 50);

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
        g.drawString("MUSIC ON", musicOn.x + 30, musicOn.y  + 35);
        g2.draw(musicOn);

        g.setColor(Color.black);
        g.fillRect(GameEngine.screenWidth / 2 + 150, 250, 200, 50);

        g.setColor(Color.white);
        g.drawString("MUSIC OFF", musicOff.x + 30, musicOff.y  + 35);
        g2.draw(musicOff);

        g.setColor(Color.black);
        g.fillRect(GameEngine.screenWidth / 2 + 430, 450, 150, 50);

        g.setColor(Color.white);
        g.drawString("BACK", backButton.x + 40, backButton.y  + 35);
        g2.draw(backButton);



    }

}
