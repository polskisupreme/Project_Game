package entitys.staff;

import java.awt.*;

public interface EntityPlayer {

    void update();
    void  render(Graphics g);
    Rectangle getBounds();
    double getX();
    double getY();

}
