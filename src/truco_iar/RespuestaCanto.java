package truco_iar;

import Cantos.Canto;

/**
 *
 * @author Nicolás Bosi.
 */
public class RespuestaCanto {

    public static int RESPUESTA_CANTO_QUERIDO = 1;
    public static int RESPUESTA_CANTO_NO_QUERIDO = -1;
    
    private int respuestaAceptacion;
    private Canto cantoContraoferta;

    public RespuestaCanto() {
        this.respuestaAceptacion = RespuestaCanto.RESPUESTA_CANTO_NO_QUERIDO;
        this.cantoContraoferta = null;
    }
    
    public RespuestaCanto(int respuestaAceptacion, Canto cantoContraoferta){
        this.respuestaAceptacion = respuestaAceptacion;
        this.cantoContraoferta = cantoContraoferta;
    }
    
    public void setRespuestaAceptacion(int respuestaAceptacion){
        this.respuestaAceptacion = respuestaAceptacion;
    }
    
    public void setCantoContraoferta(Canto cantoContraoferta){
        this.cantoContraoferta = cantoContraoferta;
    }
    
    public int getRespuestaAceptacion(){
        return this.respuestaAceptacion;
    }
    
    public Canto getCantoContraoferta(){
        return this.cantoContraoferta;
    }
}
