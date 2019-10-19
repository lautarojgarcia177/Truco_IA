package Entidades;

/**
 *
 * @author Nicolás Bosi
 */
public class PaloBastos extends Palo{
    
    private static PaloBastos instancia;
    
    private PaloBastos(){
        super();
        nombre = "Bastos";
    }
    
    public static PaloBastos getInstancia(){
    
        if(instancia == null) instancia = new PaloBastos();
        return instancia;
    }
    
}
