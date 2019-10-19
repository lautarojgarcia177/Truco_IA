package truco_iar;

/**
 *
 * @author Nicolás Bosi
 */
public class Parametros {


    private static Parametros instancia;
    
    private double umbralJugarNinguno = 0.5;
    private double umbralJugarTruco = 0.5;
    private double umbralJugarRetruco = 0.5;
    private double umbralJugarValeCuatro = 0.5; 
    
    private String nivelBajo = "Conservador";
    private String nivelMedio = "Intermedio";
    private String nivelAlto = "Arriesgado";
    
    private int cantidadPuntosPartido;
    
    private Parametros(){
        this.cantidadPuntosPartido = 30;
    }
    
    public static Parametros getInstancia()
    {
        if(instancia == null) Parametros.instancia = new Parametros();
        return instancia;
    }
    
    public void setCantidadPuntosPartido(int cantidadPuntos){
        this.cantidadPuntosPartido = cantidadPuntos;
    }
    
    public int getCantidadPuntosPartido(){
        return this.cantidadPuntosPartido;
    }

    public double getUmbralJugarNinguno() {
        return umbralJugarNinguno;
    }

    public double getUmbralJugarTruco() {
        return umbralJugarTruco;
    }

    public double getUmbralJugarRetruco() {
        return umbralJugarRetruco;
    }

    public double getUmbralJugarValeCuatro() {
        return umbralJugarValeCuatro;
    }

    public void setUmbrales(int nivelMaquina) {
        this.umbralJugarNinguno += this.valorSumar(nivelMaquina);
        this.umbralJugarTruco += this.valorSumar(nivelMaquina);
        this.umbralJugarRetruco += this.valorSumar(nivelMaquina);
        this.umbralJugarValeCuatro += this.valorSumar(nivelMaquina);
    }
    
    private double valorSumar(int nivelMaquina){
        switch (nivelMaquina){
            case 1: return 0.3;
            case 2: return 0;
            case 3: return -0.2;
            default: return 0;
        }
    }
    
    public String getNivelMaquina(int valor){
        switch (valor){
            case 1: return this.nivelBajo;
            case 2: return this.nivelMedio;
            case 3: return this.nivelAlto;
            default: return this.nivelMedio;
        }
    }
    
}
