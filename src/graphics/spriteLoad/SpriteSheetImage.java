package graphics.spriteLoad;

import java.awt.image.BufferedImage;

public class SpriteSheetImage {

    private final BufferedImage image;

    public SpriteSheetImage(BufferedImage bi){
        this.image = bi;
    }

    public BufferedImage grabImage(int ver, int hor, int width, int height){
        return image.getSubimage((ver * 32) - 32, (hor * 32) - 32, width, height);
    }

}
