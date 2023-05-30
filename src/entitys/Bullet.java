package entitys;

import core.GameEngine;
import entitys.staff.EntityPlayer;
import graphics.Animation;
import graphics.SpriteTextures;

import java.awt.*;

public class Bullet extends GameObjects implements EntityPlayer {

    SpriteTextures textures;
    GameEngine gameEngine;
    Animation animation;

    public Bullet(double x, double y, SpriteTextures textures, GameEngine gameEngine){
        super(x, y);
        this.textures = textures;
        this.gameEngine = gameEngine;

        animation = new Animation(4, textures.missile[0], textures.missile[1], textures.missile[2] );
    }

    @Override
    public void update() {
        y -= 8;

        animation.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        animation.drawAnimation(g, x, y, 0);
    }
    @Override
    public Rectangle getBounds() { return new Rectangle((int)x, (int)y, 32, 32); }
    @Override
    public double getX() {
        return x;
    }
    @Override
    public double getY() {
        return y;
    }
}
