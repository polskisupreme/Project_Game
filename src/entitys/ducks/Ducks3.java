package entitys.ducks;

import core.GameEngine;
import entitys.GameObjects;
import entitys.staff.Controller;
import entitys.staff.EntityEnemy;
import entitys.staff.EntityPlayer;
import graphics.Animation;
import graphics.SpriteTextures;
import physiscs.Physisc;

import java.awt.*;
import java.util.Random;

public class Ducks3 extends GameObjects implements EntityEnemy {

    Random random = new Random();
    Animation animation;
    GameEngine gameEngine;
    Controller controller;

    private int speed = random.nextInt(2) + 1;
    private int HP = 3;

    public Ducks3(double x, double y, SpriteTextures textures, Controller controller, GameEngine gameEngine){
        super(x, y);
        this.gameEngine = gameEngine;
        this.controller = controller;

        animation = new Animation(4, textures.ducks3[0], textures.ducks3[1], textures.ducks3[2]);
    }

    @Override
    public void update() {
        x += speed;

        if (x > (GameEngine.screenWidth * GameEngine.scale)){
            controller.removeEntity(this);
            GameEngine.Health -= 20;
            y = random.nextInt((GameEngine.screenHeight / 2) * GameEngine.scale);
            speed = random.nextInt(3) + 1;
        }

        for (int i = 0; i <gameEngine.entityPlayer.size(); i++) {
            EntityPlayer tempEnt = gameEngine.entityPlayer.get(i);

            if (Physisc.collision(this, tempEnt) && HP == 3) {
                controller.removeEntity(tempEnt);
                HP--;
            }else  if (Physisc.collision(this, tempEnt) && HP == 2){
                controller.removeEntity(tempEnt);
                HP--;
            }else if (Physisc.collision(this, tempEnt)){
                controller.removeEntity(tempEnt);
                GameEngine.score += 75;
                controller.removeEntity(this);
                gameEngine.setEnemyKilled(gameEngine.getEnemyKilled() + 1);
            }
        }


        animation.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        animation.drawAnimation(g, x, y, 0);
    }
    @Override
    public Rectangle getBounds() { return new Rectangle((int)x, (int)y, 32, 32); }
}
