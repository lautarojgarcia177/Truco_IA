package Actores;

import Entidades.Carta;
import java.util.ArrayList;
import Cantos.Canto;
import truco_iar.GestorTrucoL;
import truco_iar.RespuestaCanto;

/**
 *
 * @author Nicolás Bosi.
 */
public abstract class JugadorTruco {
    
    protected GestorTrucoL gestorTruco;
    
    protected ArrayList<Carta> mano;
    protected boolean esMano;
    protected int cantidadPuntos;
   
    public JugadorTruco(){
        this.cantidadPuntos = 0;
    }
    
    public void setGestorTruco(GestorTrucoL gestorTruco){
        this.gestorTruco = gestorTruco;
    }
    
    public void repartirMano(Carta carta1, Carta carta2, Carta carta3){
    
        mano = new ArrayList<>();
        
        mano.add(carta1);
        mano.add(carta2);
        mano.add(carta3);
    }
    
    public ArrayList<Carta> getMano(){
        return mano;
    }
    
    public void setEsMano(boolean esMano){
        this.esMano = esMano;
    }
    
    public boolean esMano(){
        return this.esMano;
    }
    
    public void alternarManoPie(){
        esMano = !esMano;
    }
    
    public void sumarPuntos(int puntosResultadoMano){
        this.cantidadPuntos += puntosResultadoMano;
    }
    
    public int getCantidadPuntos(){
        return this.cantidadPuntos;
    }
    
    public void setCantidadPuntos(int puntaje){
        this.cantidadPuntos = puntaje;
    }
    
    public abstract Carta iniciarTurno();
    
    public abstract RespuestaCanto responderCanto(Canto cantoRealizado);
}
