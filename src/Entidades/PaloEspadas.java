package Entidades;

/**
 *
 * @author Nicolás Bosi
 */
public class PaloEspadas extends Palo{
    
    private static PaloEspadas instancia;
    
    private PaloEspadas(){
        super();
        nombre = "Espadas";
    }
    
    public static PaloEspadas getInstancia(){
    
        if(instancia == null) instancia = new PaloEspadas();
        return instancia;
    }
    
}
