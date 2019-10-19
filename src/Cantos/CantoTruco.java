package Cantos;

/**
 *
 * @author Nicolás Bosi.
 */
public class CantoTruco extends Canto{
    
    private static CantoTruco instancia;
    
    protected CantoTruco(){
        super();
        super.nombre = "Truco";
        super.puntosEnJuego = 2;
    }
    
    public static CantoTruco getInstancia(){
        if(instancia == null) instancia = new CantoTruco();
        return instancia;
    }
    
}
