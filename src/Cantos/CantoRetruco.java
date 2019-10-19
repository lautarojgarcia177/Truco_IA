package Cantos;

/**
 *
 * @author Nicolás Bosi.
 */
public class CantoRetruco extends CantoTruco{
    
    private static CantoRetruco instancia;
    
    protected CantoRetruco(){
        super();
        super.nombre = "Retruco";
        super.puntosEnJuego = 3;
    }
    
    public static CantoRetruco getInstancia(){
        if(instancia == null) instancia = new CantoRetruco();
        return instancia;
    }
}
