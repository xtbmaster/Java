package Skeleton;

import Entity.*;

/**
 * The class detects whether there was a contact between game objects.
 */

public class Physics {

    public static boolean collision (EntityPlayer eP, EntityEnemy eE){
        return eP.getBounds().intersects(eE.getBounds());
    }

    public static boolean collision (EntityEnemy eE, EntityPlayer eP){
        return eE.getBounds().intersects(eP.getBounds());
    }

    public static boolean collision (EntityPlayer eP, EntityStuff eS){
        return eP.getBounds().intersects(eS.getBounds());
    }

    public static boolean collision (EntityStuff eS, EntityPlayer eP){
        return eS.getBounds().intersects(eP.getBounds());
    }
}
