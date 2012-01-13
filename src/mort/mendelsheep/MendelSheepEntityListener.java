/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mort.mendelsheep;
import org.bukkit.entity.CreatureType;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.craftbukkit.entity.CraftSheep;
import net.minecraft.server.World;
/**
 *
 * @author Martin
 */
public class MendelSheepEntityListener extends EntityListener{
    
    public java.lang.reflect.Field bE;
    
    /*public MendelSheepEntityListener() throws java.lang.NoSuchFieldException{
        super();
        bE = net.minecraft.server.Entity.class.getDeclaredField("bukkitEntity");
        bE.setAccessible(true);
    }*/
    
    @Override
    public void onCreatureSpawn( CreatureSpawnEvent evnt ){
        if( evnt.getCreatureType()!=CreatureType.SHEEP)
            return;
        CraftSheep cs = (CraftSheep) evnt.getEntity();
        if( cs.getHandle() instanceof EntityMendelSheep )
            return;
        
        EntityMendelSheep ent = new EntityMendelSheep( cs.getHandle() );
        World w = cs.getHandle().world;
        w.addEntity(ent);
        evnt.setCancelled(true);
        
        /*try{
            bE.set(cs.getHandle(), null);
            bE.set(ent, cs);
            cs.setHandle(ent);*/

        //}catch(Exception e){e.printStackTrace();}
    }
    
}
