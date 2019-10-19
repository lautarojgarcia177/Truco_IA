package Actores;

import Entidades.Carta;
import Cantos.Canto;
import Cantos.CantoTruco;
import truco_iar.GestorTrucoL;
import truco_iar.RespuestaCanto;


public class JugadorHumano extends JugadorTruco{

    
    
    public void removerCartaDeMano(Carta c){
        int i=mano.indexOf(c);
        mano.add(i, GestorTrucoL.cartaUtilizada);
        mano.remove(i+1);
    }
    
    @Override
    public Carta iniciarTurno() {
        return null;
    }

    @Override
    public RespuestaCanto responderCanto(Canto cantoRealizado) {
        return new RespuestaCanto(RespuestaCanto.RESPUESTA_CANTO_QUERIDO, null);
    }
    
}
