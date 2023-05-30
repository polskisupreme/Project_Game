package physiscs;

import entitys.staff.EntityEnemy;
import entitys.staff.EntityPlayer;

public class Physisc {

    public static boolean collision(EntityEnemy entityEnemy, EntityPlayer entityPlayer){
        return entityEnemy.getBounds().intersects(entityPlayer.getBounds());
    }
}
