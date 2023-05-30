package entitys;

import core.GameEngine;
import entitys.staff.Controller;
import entitys.staff.EntityEnemy;
import entitys.staff.EntityPlayer;
import graphics.Animation;
import graphics.SpriteTextures;
import physiscs.Physisc;

import java.awt.*;

public class Player extends GameObjects implements EntityPlayer {

    private double velX = 0;

    Animation animation;
    GameEngine gameEngine;
    Controller controller;

    public Player(double x, double y, SpriteTextures textures, GameEngine gameEngine, Controller controller){
        super(x, y);
        this.gameEngine = gameEngine;
        this.controller = controller;
        animation = new Animation(10, textures.player[0], textures.player[1], textures.player[2]);

    }

    public void update(){

        x += velX;

        if (x <= -6) x =-6;
        if (x >= 720 - 25) x = 720 - 25;
        if (y <= 0) y =0;
        if (y >= 540 - 32) y = 540 - 32;

        animation.runAnimation();

    }
    public void render(Graphics g){
        animation.drawAnimation(g, x, y, 0);
    }

    public Rectangle getBounds(){ return new Rectangle((int)x, (int)y, 32, 32); }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void setVelX(double velX){
        this.velX = velX;
    }
}
