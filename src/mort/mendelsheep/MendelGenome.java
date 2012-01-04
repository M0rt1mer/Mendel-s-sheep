/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mort.mendelsheep;
import java.util.Random;
import org.bukkit.DyeColor;
/**
 *
 * @author Martin
 */
public class MendelGenome {
    
    public byte genes;
    public static Random rand = new Random();
    
    public MendelGenome(){
        int intGen = (0x55 | (rand.nextInt(256)&0x2A) );
        genes = (byte) (intGen);
    }
    
    public MendelGenome( byte genome ){
        genes = genome;
    }
    
    public MendelGenome( MendelGenome mother, MendelGenome father ){
        genes = 0;
        for(int i = 0; i<4;i++){
            genes |= (( mother.genes & (1<<(i*2+rand.nextInt(2)) ) )>0)?(1<<(i*2) ):0;
            genes |= (( (father.genes & (1<<(i*2+rand.nextInt(2)) ) )<< 1 )>0)?(1<<(i*2+1)):0;
        }
    }
    
    public int toColor(){
        int color = 0;
        for(int i = 0; i<4;i++){
            color |= ( (genes&( 1<<i*2 ))>>i ) | ((genes&(1<<(i*2+1)))>>(i+1) );
        }
        //System.out.println( this + "->"+color );
        return colorMap[color];
    }
    
    public String toString(){
        String ret = "";// "Genome: ";
        for(int i = 0; i<8;i++){
            ret += "" + ( (genes&(1<<i)) >>i);
            if( i%2==1 )
                ret += " ";
        }
        return ret;
    }
    
    public enum Attribute{
        COLOR, NATURAL, HUE, LIGHTNESS;
    }
    
    public static int[] colorMap = {
        DyeColor.BLUE.getData(), DyeColor.BROWN.getData(), DyeColor.GREEN.getData(), DyeColor.BLACK.getData(),
        DyeColor.MAGENTA.getData(), DyeColor.MAGENTA.getData(), DyeColor.ORANGE.getData(), DyeColor.SILVER.getData(),
        DyeColor.LIGHT_BLUE.getData(), DyeColor.RED.getData(), DyeColor.LIME.getData(), DyeColor.GRAY.getData(),
        DyeColor.CYAN.getData(), DyeColor.PINK.getData(), DyeColor.YELLOW.getData(), DyeColor.WHITE.getData() };
    
    
}
