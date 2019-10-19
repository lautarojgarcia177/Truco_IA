package Entidades;

/**
 *
 * @author Nicolás Bosi
 */
public class Carta implements Comparable{
    
    private final int numero;
    private final Palo palo;
    private final int jerarquia;
    private String imgdir;
    
    private static final int CARTA_SUPERIOR = 1;
    private static final int CARTA_INFERIOR = -1;
    private static final int CARTA_IGUAL = 0;
    
    private final int cantCartasAQueLeGanaDe40;
    
    public Carta(int numeroCarta, Palo paloCarta, int jerarquiaCarta, String imgdir,int cantCartasAQueLeGanaDe40){
        this.numero = numeroCarta;
        this.palo = paloCarta;
        this.jerarquia = jerarquiaCarta;
        this.imgdir = imgdir;
        this.cantCartasAQueLeGanaDe40 = cantCartasAQueLeGanaDe40;
    }

    public int getCantCartasAQueLeGanaDe40() {
        return cantCartasAQueLeGanaDe40;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    public Palo getPalo(){
        return this.palo;
    }
    
    public int getJerarquia(){
        return this.jerarquia;
    }
    
    public String getImgDir(){
        return this.imgdir;
    }
    
    @Override
    public String toString(){
        String resultado = "Número: " + numero + "\nPalo: " + palo.getNombre();
        return resultado;
    }

    @Override
    public int compareTo(Object o) {
        Carta cartaAComparar = (Carta)o;
        
        if(this.jerarquia > cartaAComparar.getJerarquia()) return Carta.CARTA_SUPERIOR;
        if(this.jerarquia < cartaAComparar.getJerarquia()) return Carta.CARTA_INFERIOR;
        return Carta.CARTA_IGUAL;
    }
}
