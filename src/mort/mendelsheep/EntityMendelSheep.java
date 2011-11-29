/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mort.mendelsheep;
import net.minecraft.server.EntitySheep;
import net.minecraft.server.EntityAnimal;
import net.minecraft.server.World;
import org.bukkit.entity.Sheep;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftSheep;
import org.bukkit.Location;
import net.minecraft.server.NBTTagCompound;
/**
 *
 * @author Martin
 */
public class EntityMendelSheep extends EntitySheep {
    
    public MendelGenome gene;
    
    public EntityMendelSheep(World world) {
        this(world, new MendelGenome() );
    }
    
    public EntityMendelSheep( World world, MendelGenome gnm ){
        super(world);
        gene = gnm;
        genColor();
    }
    /* WRITE */
    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("Genome", gene.genes );
    }

    /* READ */
    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        if( nbttagcompound.hasKey("Genome") )
            gene = new MendelGenome( nbttagcompound.d("Genome") );
    }
    
    public void genColor(){
        setColor( this.gene.toColor() );
    }
    
    @Override
    protected EntityAnimal createChild(EntityAnimal entityanimal) {
        return new EntityMendelSheep( this.world, new MendelGenome( this.gene, ((EntityMendelSheep)entityanimal).gene )  );
    }
    
}
