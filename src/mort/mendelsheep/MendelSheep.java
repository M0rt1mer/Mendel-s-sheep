/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mort.mendelsheep;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import net.minecraft.server.EntityTypes;
import org.bukkit.entity.EntityType;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
/**
 *
 * @author Martin
 */
public class MendelSheep extends JavaPlugin {
    
    @Override
    public void onEnable(){
        getCommand("ms").setExecutor(this);
        
        try{
            Class[] args = { Class.class, String.class, Integer.TYPE };
            java.lang.reflect.Method mtd = EntityTypes.class.getDeclaredMethod("a", args );
            mtd.setAccessible(true);
            Object[] args2 = { EntityMendelSheep.class, "Sheep", 91 };
            mtd.invoke(null, args2);
        }catch (Exception e){e.printStackTrace();}
        
        try{
            getServer().getPluginManager().registerEvents( new MendelSheepListener(), this);
        }catch(Exception e){System.out.println("Entity hack failed - spawning won't work.");e.printStackTrace();}
        
        Permission pms = new Permission( "mendelsheep" );
        getServer().getPluginManager().addPermission( pms );
        pms.setDefault( PermissionDefault.OP );
        for( String str: new String[] { "inspect", "grow", "spawn", "restore", "regrow", "pickup" } ){
            Permission perm = new Permission( "mendelsheep."+str );
            perm.addParent(pms, true);
            getServer().getPluginManager().addPermission( perm );
        }
        
    }
    
    @Override
    public void onDisable(){
    
    
    }
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings){
        
        if(cs instanceof org.bukkit.entity.Player && cs.hasPermission("mendelsheep.spawn")){
            org.bukkit.entity.Player plr = (org.bukkit.entity.Player) cs;
            plr.getWorld().spawnCreature(plr.getLocation(), EntityType.SHEEP);
        }
        
        return true;
    }
    
}
