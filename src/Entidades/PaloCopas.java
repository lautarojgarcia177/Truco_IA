package Entidades;

/**
 *
 * @author Nicolás Bosi
 */
public class PaloCopas extends Palo{
    
    private static PaloCopas instancia;
    
    private PaloCopas(){
        nombre = "Copas";
    }
    
    public static PaloCopas getInstancia(){
        
        if(instancia == null) instancia = new PaloCopas();
        return instancia;
    }
    
}
