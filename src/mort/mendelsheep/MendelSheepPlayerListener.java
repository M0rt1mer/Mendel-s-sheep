/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mort.mendelsheep;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.entity.Animals;
import org.bukkit.craftbukkit.entity.CraftSheep;
import net.minecraft.server.EntitySheep;
import org.bukkit.Material;
/**
 *
 * @author Martin
 */
public class MendelSheepPlayerListener extends PlayerListener{
    
    @Override
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
    }
    
}
