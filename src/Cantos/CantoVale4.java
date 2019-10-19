package Cantos;

/**
 *
 * @author Nicolás Bosi.
 */
public class CantoVale4 extends CantoRetruco{
 
    private static CantoVale4 instancia;
    
    private CantoVale4(){
        super();
        super.nombre = "Vale 4";
        super.puntosEnJuego = 4;
    }
    
    public static CantoVale4 getInstancia(){
        if(instancia == null) instancia = new CantoVale4();
        return instancia;
    }
}
