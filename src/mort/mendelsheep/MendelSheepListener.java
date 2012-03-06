/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mort.mendelsheep;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.craftbukkit.entity.CraftSheep;
import net.minecraft.server.EntitySheep;
import org.bukkit.entity.Sheep;
import net.minecraft.server.World;
import org.bukkit.Material;
/**
 *
 * @author Martin
 */
public class MendelSheepListener implements Listener{
    
    @EventHandler
    public void onPlayerInteractEntity( PlayerInteractEntityEvent evnt ){
        
        if( !(evnt.getRightClicked() instanceof CraftSheep) )
            return;
        
        CraftSheep cshp = ((CraftSheep) evnt.getRightClicked());
        EntitySheep eshp = cshp.getHandle();
        
        if( eshp instanceof EntityMendelSheep && evnt.getPlayer().hasPermission("mendelsheep.inspect") ){
            evnt.getPlayer().sendMessage( ""+((EntityMendelSheep)eshp).gene );
        }
        
        if( eshp instanceof EntityMendelSheep && evnt.getPlayer().getItemInHand().getType()==Material.BONE && evnt.getPlayer().hasPermission("mendelsheep.restore") ){
            ((EntityMendelSheep)eshp).genColor();
        }
        
        if( evnt.getPlayer().getItemInHand().getType()==Material.WHEAT && evnt.getPlayer().hasPermission("mendelsheep.grow") ){
            cshp.setAge(0);
        }

        if( evnt.getPlayer().getItemInHand().getType()==Material.WATCH && evnt.getPlayer().hasPermission("mendelsheep.regrow") ){
            cshp.setSheared(false);
        }
        
        if( evnt.getPlayer().getItemInHand().getType()==Material.STRING && evnt.getPlayer().hasPermission("mendelsheep.pickup") ){
            if( evnt.getPlayer().getPassenger()==null ){
                evnt.getPlayer().setPassenger(cshp);
                evnt.getPlayer().sendMessage("You picked up a sheep");
            }
            else if( evnt.getPlayer().getPassenger() instanceof CraftSheep ){
                evnt.getPlayer().eject();
                evnt.getPlayer().sendMessage("You put down up a sheep");
            }
            else evnt.getPlayer().sendMessage("ELSE");
        }
        
    }
    
    @EventHandler
    public void onCreatureSpawn( CreatureSpawnEvent evnt ){
        if( ! (evnt.getEntity() instanceof Sheep)  )
            return;
        CraftSheep cs = (CraftSheep) evnt.getEntity();
        if( cs.getHandle() instanceof EntityMendelSheep )
            return;
        
        EntityMendelSheep ent = new EntityMendelSheep( cs.getHandle() );
        World w = cs.getHandle().world;
        w.addEntity(ent);
        evnt.setCancelled(true);
    }
    
}
