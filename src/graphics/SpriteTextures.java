package graphics;

import core.GameEngine;
import graphics.spriteLoad.SpriteSheetImage;

import java.awt.image.BufferedImage;

public class SpriteTextures {

    public BufferedImage[] player = new BufferedImage[3];
    public BufferedImage[] missile = new BufferedImage[3];
    public BufferedImage[] ducks = new BufferedImage[3];
    public BufferedImage[] ducks2 = new BufferedImage[3];
    public BufferedImage[] ducks3 = new BufferedImage[3];
    public BufferedImage[] ducks4 = new BufferedImage[3];
    public BufferedImage[] cloud = new BufferedImage[3];

    private final SpriteSheetImage spriteSheetImage;

    public SpriteTextures(GameEngine gameEngine){
        spriteSheetImage = new SpriteSheetImage(gameEngine.getSpriteSheet());

        getTextures();
    }

    private void getTextures(){
        player[0] = spriteSheetImage.grabImage(1,1,64,64);
        player[1] = spriteSheetImage.grabImage(1,3,64,64);
        player[2] = spriteSheetImage.grabImage(1,5,64,64);

        missile[0] = spriteSheetImage.grabImage(3,1,32,32);
        missile[1] = spriteSheetImage.grabImage(3,2,32,32);
        missile[2] = spriteSheetImage.grabImage(3,3,32,32);

        ducks[0] = spriteSheetImage.grabImage(4,1,64,64);
        ducks[1] = spriteSheetImage.grabImage(4,3,64,64);
        ducks[2] = spriteSheetImage.grabImage(4,5,64,64);

        ducks2[0] = spriteSheetImage.grabImage(4,7,64,64);
        ducks2[1] = spriteSheetImage.grabImage(4,9,64,64);
        ducks2[2] = spriteSheetImage.grabImage(4,11,64,64);

        ducks3[0] = spriteSheetImage.grabImage(6,1,64,64);
        ducks3[1] = spriteSheetImage.grabImage(6,3,64,64);
        ducks3[2] = spriteSheetImage.grabImage(6,5,64,64);

        ducks4[0] = spriteSheetImage.grabImage(6,7,64,64);
        ducks4[1] = spriteSheetImage.grabImage(6,9,64,64);
        ducks4[2] = spriteSheetImage.grabImage(6,11,64,64);

        cloud[0] = spriteSheetImage.grabImage(8,1,128,96);
        cloud[1] = spriteSheetImage.grabImage(8,1,128,96);
        cloud[2] = spriteSheetImage.grabImage(8,1,128,96);
    }

}
