package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int index = 0;
    private int count = 0;
    private int speed;

    private final int frames;
    private final BufferedImage img1;
    private final BufferedImage img2;
    private final BufferedImage img3;

    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3){
        this.speed = speed;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;

        frames = 3;
    }

    public void runAnimation(){
        index++;
        if (index > speed){
            index = 0;
            nextFrame();
        }
    }
    public void nextFrame(){
        if (frames == 3) {
            if (count == 0)
                currentImg = img1;
            if (count == 1)
                currentImg = img2;
            if (count == 2)
                currentImg = img3;
            count++;
            if (count > frames)
                count = 0;
        }
    }
    public void drawAnimation(Graphics g, double x, double y, int offset){
        g.drawImage(currentImg, (int)x + offset, (int)y, null);
    }
}
