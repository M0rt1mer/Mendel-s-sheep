/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mort.mendelsheep;
import net.minecraft.server.EntitySheep;
import net.minecraft.server.EntityAnimal;
import net.minecraft.server.World;
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
    public EntityMendelSheep(EntitySheep shp) {
        this( shp.world, new MendelGenome() );
        this.setPositionRotation(shp.locX, shp.locY, shp.locZ, shp.pitch, shp.yaw);
    }
    /* WRITE */
    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.setByte("Genome", gene.genes );
    }

    /* READ */
    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        if( nbttagcompound.hasKey("Genome") )
            gene = new MendelGenome( nbttagcompound.getByte("Genome") );
    }
    
    public void genColor(){
        setColor( this.gene.toColor() );
    }
    
    @Override
    protected EntityAnimal createChild(EntityAnimal entityanimal) {
        return new EntityMendelSheep( this.world, new MendelGenome( this.gene, ((EntityMendelSheep)entityanimal).gene )  );
    }
    
    @Override
    public void setSheared(boolean flag) {
        boolean prev = isSheared();
        super.setSheared(flag);
        if(!flag && prev )
            genColor();
    }
    
}
