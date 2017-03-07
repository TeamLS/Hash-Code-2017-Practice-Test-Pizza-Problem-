package pizza;

/**
 *
 * @author TeamLS
 * @since 20/2/2017
 * @version 1.0
 */
public class Data {
    
    public double probability;
    public boolean horizontal;
    public int i;
    
    public Data(int i, boolean horizontal, double tasosTipos){
        this.i = i;
        this.horizontal = horizontal;
        this.probability = tasosTipos;        
    }
    
    @Override
    public String toString(){
        return "" + i + " " + horizontal + " " + probability;
    }
    
}
