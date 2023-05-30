package entitys;

import core.GameEngine;
import entitys.staff.Controller;
import entitys.staff.EntityEnemy;
import entitys.staff.EntityPlayer;
import graphics.Animation;
import graphics.SpriteTextures;
import physiscs.Physisc;

import java.awt.*;
import java.util.Random;

public class Cloud extends GameObjects implements EntityEnemy {

    Random random = new Random();
    Animation animation;
    GameEngine gameEngine;
    Controller controller;

    private int speed = random.nextInt(3) + 1;

    public Cloud(double x, double y, SpriteTextures textures, Controller controller, GameEngine gameEngine){
        super(x, y);
        this.gameEngine = gameEngine;
        this.controller = controller;

        animation = new Animation(4, textures.cloud[0], textures.cloud[0], textures.cloud[0]);
    }

    @Override
    public void update() {
        x += speed;

        if (x > (GameEngine.screenWidth * GameEngine.scale)){
            x = 0;
            y = 300;
            speed = random.nextInt(4) + 1;
        }

        for (int i = 0; i <gameEngine.entityPlayer.size(); i++) {
            EntityPlayer tempEnt = gameEngine.entityPlayer.get(i);

            if (Physisc.collision(this, tempEnt)){
                controller.removeEntity(tempEnt);
            }
        }

        animation.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        animation.drawAnimation(g, x, y, 0);
    }
    @Override
    public Rectangle getBounds() { return new Rectangle((int)x, (int)y, 80, 96); }
}
