package Cantos;

/**
 *
 * @author Nicolás Bosi.
 */
public abstract class Canto {
    
    protected int puntosEnJuego;
    
    protected String nombre;
    
    public int getPuntosEnJuego(){
        return this.puntosEnJuego;
    }
    
    public String getNombre(){
        return this.nombre;
    }
}
