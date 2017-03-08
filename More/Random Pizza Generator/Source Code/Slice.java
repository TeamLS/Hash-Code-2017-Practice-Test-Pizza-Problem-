package randompizzagenerator;

/**
 *
 * @author TeamLS
 * @since 20/2/2017
 * @version 1.0
 */
public class Slice {
    
    public int startI;
    public int startJ;
    public int endI;
    public int endJ;
    
    public Slice(int startI, int startJ, int endI, int endJ){
        this.startI = startI;
        this.startJ = startJ;
        this.endI = endI;
        this.endJ = endJ;
    }
    
    @Override
    public String toString(){
        return startI + " " + startJ + " " + endI + " " + endJ;
    }
    
}
