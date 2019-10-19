package truco_iar;

import Cantos.Canto;
import Actores.JugadorTruco;
import Cantos.CantoRetruco;
import Cantos.CantoTruco;
import Cantos.CantoVale4;
import Entidades.Carta;
import java.util.ArrayList;


public class EstadoMesa {
    
    private Carta cartaManoRonda1;
    private Carta cartaPieRonda1;
    
    private JugadorTruco ganadorRonda1;
    
    private Carta cartaManoRonda2;
    private Carta cartaPieRonda2;
    
    private JugadorTruco ganadorRonda2;
    
    private Carta cartaManoRonda3;
    private Carta cartaPieRonda3;
    
    private JugadorTruco ganadorRonda3;
    
    private int rondaActual;
    
    private Canto cantoActual;
    
    private JugadorTruco quienTieneElQuiero;
    private JugadorTruco jugadorMano;
    private JugadorTruco jugadorPie;
    
    private boolean terminoElJuego;
    
    private ArrayList<Carta> cartasJugadasHumano;
    private ArrayList<Carta> cartasJugadasMaquina;
    
    public EstadoMesa(){
        cartaManoRonda1 = null;
        cartaPieRonda1 = null;
        ganadorRonda1 = null;
        
        cartaManoRonda2 = null;
        cartaPieRonda2 = null;
        ganadorRonda2 = null;
        
        cartaManoRonda3 = null;
        cartaPieRonda3 = null;
        ganadorRonda3 = null;
        
        cantoActual = null;
        quienTieneElQuiero = null;
        jugadorMano=null;
        jugadorPie=null;
        
        cartasJugadasHumano=new ArrayList<>();
        cartasJugadasMaquina = new ArrayList<>();
        terminoElJuego=false;
    }

    public boolean isTerminoElJuego() {
        return terminoElJuego;
    }

    public void setTerminoElJuego(boolean terminoElJuego) {
        this.terminoElJuego = terminoElJuego;
    }

    public JugadorTruco getJugadorPie() {
        return jugadorPie;
    }

    public ArrayList<Carta> getCartasJugadasHumano() {
        return cartasJugadasHumano;
    }

    public void setJugadorPie(JugadorTruco jugadorPie) {
        this.jugadorPie = jugadorPie;
    }

    public JugadorTruco getJugadorMano() {
        return jugadorMano;
    }

    public void setJugadorMano(JugadorTruco jugadorMano) {
        this.jugadorMano = jugadorMano;
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public void setRondaActual(int rondaActual) {
        this.rondaActual = rondaActual;
    }

    public JugadorTruco getQuienTieneElQuiero() {
        return quienTieneElQuiero;
    }

    public void setQuienTieneElQuiero(JugadorTruco quienTieneElQuiero) {
        this.quienTieneElQuiero = quienTieneElQuiero;
    }

    public Carta getCartaManoRonda1() {
        return cartaManoRonda1;
    }

    public void setCartaManoRonda1(Carta cartaManoRonda1) {
        this.cartaManoRonda1 = cartaManoRonda1;
    }

    public Carta getCartaPieRonda1() {
        return cartaPieRonda1;
    }

    public void setCartaPieRonda1(Carta cartaPieRonda1) {
        this.cartaPieRonda1 = cartaPieRonda1;
    }

    public Carta getCartaManoRonda2() {
        return cartaManoRonda2;
    }

    public void setCartaManoRonda2(Carta cartaManoRonda2) {
        this.cartaManoRonda2 = cartaManoRonda2;
    }

    public Carta getCartaPieRonda2() {
        return cartaPieRonda2;
    }

    public void setCartaPieRonda2(Carta cartaPieRonda2) {
        this.cartaPieRonda2 = cartaPieRonda2;
    }

    public Carta getCartaManoRonda3() {
        return cartaManoRonda3;
    }

    public void setCartaManoRonda3(Carta cartaManoRonda3) {
        this.cartaManoRonda3 = cartaManoRonda3;
    }

    public Carta getCartaPieRonda3() {
        return cartaPieRonda3;
    }

    public void setCartaPieRonda3(Carta cartaPieRonda3) {
        this.cartaPieRonda3 = cartaPieRonda3;
    }

    public Canto getCantoActual() {
        return cantoActual;
    }

    public void setCantoActual(Canto cantoActual) {
        this.cantoActual = cantoActual;
    }
    
    public void setGanadorRonda1(JugadorTruco ganadorRonda1){
        this.ganadorRonda1 = ganadorRonda1;
    }
    
    public void setGanadorRonda2(JugadorTruco ganadorRonda2){
        this.ganadorRonda2 = ganadorRonda2;
    }
    
    public void setGanadorRonda3(JugadorTruco ganadorRonda3){
        this.ganadorRonda3 = ganadorRonda3;
    }
    
    public JugadorTruco getGanadorRonda1(){
        return this.ganadorRonda1;
    }
    
    public JugadorTruco getGanadorRonda2(){
        return this.ganadorRonda2;
    }
    
    public JugadorTruco getGanadorRonda3(){
        return this.ganadorRonda3;
    }
    
    public ArrayList<Carta> getCartasJugadasMaquina(){
        return this.cartasJugadasMaquina;
    }
    
    public String getProximoCanto() throws NullPointerException{
        Canto proximoCanto=null;        
        if(this.getCantoActual()==null) proximoCanto = CantoTruco.getInstancia();
        else if(this.getCantoActual()==CantoTruco.getInstancia()) proximoCanto = CantoRetruco.getInstancia();
        else if(this.getCantoActual()==CantoRetruco.getInstancia()) proximoCanto = CantoVale4.getInstancia();
        
        if(proximoCanto == null) return "vale 4";
        
        return proximoCanto.getNombre();
    }
}
