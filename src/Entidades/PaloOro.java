package Entidades;

/**
 *
 * @author Nicolás Bosi
 */
public class PaloOro extends Palo{

    private static PaloOro instancia;
    
    private PaloOro(){
        super();
        nombre = "Oro";
    }
    
    public static PaloOro getInstancia(){
    
        if(instancia == null) instancia = new PaloOro();
        return instancia;
    }

}
