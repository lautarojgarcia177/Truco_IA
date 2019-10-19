package truco_iar;

import Actores.JugadorHumano;
import Actores.JugadorMaquina;
import Actores.JugadorTruco;
import Cantos.*;
import Entidades.Carta;
import Entidades.Palo;
import Entidades.PaloBastos;
import Entidades.PaloCopas;
import Entidades.PaloEspadas;
import Entidades.PaloOro;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import pantallas.Partido;


public class GestorTrucoL {
    
    private final ArrayList<Carta> mazo;
    private final ArrayList<Palo> palos;

    private JugadorMaquina jugadorMaquina;
    private JugadorHumano jugadorHumano;
    
    private Random generadorNumerosAleatorios;
    
    private EstadoMesa estadoActualMesa;
    static final int GANADOR_RONDA_MANO = 1;
    static final int GANADOR_RONDA_PIE = -1;
    static final int RONDA_EMPARDADA = 0;
    
    public static Carta cartaUtilizada = new Carta(-1,null,-1,null, -1);
    
    private enum ganadorRonda{
        HUMANO,
        MAQUINA
    }
    
    Parametros parametrosJuego;
    
    Partido pantalla2;
    String mensajeResultadoRonda="El ganador de esta ronda es ";
    
    public GestorTrucoL() {

        jugadorMaquina = null;

        mazo = new ArrayList<>();
        palos = new ArrayList<>();

        Palo paloEspadas = PaloEspadas.getInstancia();
        Palo paloBastos = PaloBastos.getInstancia();
        Palo paloOro = PaloOro.getInstancia();
        Palo paloCopas = PaloCopas.getInstancia();

        palos.add(paloEspadas);
        palos.add(paloBastos);
        palos.add(paloOro);
        palos.add(paloCopas);

        mazo.add(new Carta(1, paloEspadas, 1, "/pantallas/img/Cartas/1-espada.jpg", 39));
        
        mazo.add(new Carta(1, paloBastos, 2,"/pantallas/img/Cartas/1-basto.jpg", 38));
        
        mazo.add(new Carta(7, paloEspadas, 3,"/pantallas/img/Cartas/7-espada.jpg", 37));
        
        mazo.add(new Carta(7, paloOro, 4,"/pantallas/img/Cartas/7-oro.jpg", 36));
        
        mazo.add(new Carta(3, paloBastos, 5,"/pantallas/img/Cartas/3-basto.jpg", 32));
        mazo.add(new Carta(3, paloCopas, 5,"/pantallas/img/Cartas/3-copa.jpg", 32));
        mazo.add(new Carta(3, paloEspadas, 5, "/pantallas/img/Cartas/3-espada.jpg", 32));
        mazo.add(new Carta(3, paloOro, 5,"/pantallas/img/Cartas/3-oro.jpg", 32));
        
        mazo.add(new Carta(2, paloBastos, 6,"/pantallas/img/Cartas/2-basto.jpg", 28));
        mazo.add(new Carta(2, paloCopas, 6,"/pantallas/img/Cartas/2-copa.jpg", 28));
        mazo.add(new Carta(2, paloEspadas, 6, "/pantallas/img/Cartas/2-espada.jpg", 28));
        mazo.add(new Carta(2, paloOro, 6,"/pantallas/img/Cartas/2-oro.jpg", 28));
        
        mazo.add(new Carta(1, paloCopas, 7,"/pantallas/img/Cartas/1-copa.jpg", 26));
        mazo.add(new Carta(1, paloOro, 7,"/pantallas/img/Cartas/1-oro.jpg", 26));
        
        mazo.add(new Carta(12, paloBastos, 8,"/pantallas/img/Cartas/12-basto.jpg", 22));
        mazo.add(new Carta(12, paloCopas, 8,"/pantallas/img/Cartas/12-copa.jpg", 22));
        mazo.add(new Carta(12, paloEspadas, 8,"/pantallas/img/Cartas/12-espada.jpg", 22));
        mazo.add(new Carta(12, paloOro, 8,"/pantallas/img/Cartas/12-oro.jpg", 22));

        mazo.add(new Carta(11, paloBastos, 9,"/pantallas/img/Cartas/11-basto.jpg", 18));
        mazo.add(new Carta(11, paloCopas, 9,"/pantallas/img/Cartas/11-copa.jpg", 18));
        mazo.add(new Carta(11, paloEspadas, 9,"/pantallas/img/Cartas/11-espada.jpg", 18));
        mazo.add(new Carta(11, paloOro, 9,"/pantallas/img/Cartas/11-oro.jpg", 18));
        
        mazo.add(new Carta(10, paloBastos, 10,"/pantallas/img/Cartas/10-basto.jpg", 14));
        mazo.add(new Carta(10, paloCopas, 10,"/pantallas/img/Cartas/10-copa.jpg", 14));
        mazo.add(new Carta(10, paloEspadas, 10,"/pantallas/img/Cartas/10-espada.jpg", 14));
        mazo.add(new Carta(10, paloOro, 10,"/pantallas/img/Cartas/10-oro.jpg", 14));
        
        mazo.add(new Carta(7, paloBastos, 11,"/pantallas/img/Cartas/7-basto.jpg", 12));
        mazo.add(new Carta(7, paloCopas, 11,"/pantallas/img/Cartas/7-copa.jpg", 12));
        
        mazo.add(new Carta(6, paloBastos, 12,"/pantallas/img/Cartas/6-basto.jpg", 8));
        mazo.add(new Carta(6, paloCopas, 12,"/pantallas/img/Cartas/6-copa.jpg", 8));
        mazo.add(new Carta(6, paloEspadas, 12,"/pantallas/img/Cartas/6-espada.jpg", 8));
        mazo.add(new Carta(6, paloOro, 12,"/pantallas/img/Cartas/6-oro.jpg", 8));
        
        mazo.add(new Carta(5, paloBastos, 13,"/pantallas/img/Cartas/5-basto.jpg", 4));
        mazo.add(new Carta(5, paloCopas, 13,"/pantallas/img/Cartas/5-copa.jpg", 4));
        mazo.add(new Carta(5, paloEspadas, 13,"/pantallas/img/Cartas/5-espada.jpg", 4));
        mazo.add(new Carta(5, paloOro, 13,"/pantallas/img/Cartas/5-oro.jpg", 4));
        
        mazo.add(new Carta(4, paloBastos, 14,"/pantallas/img/Cartas/4-basto.jpg", 0));
        mazo.add(new Carta(4, paloCopas, 14,"/pantallas/img/Cartas/4-copa.jpg", 0));
        mazo.add(new Carta(4, paloEspadas, 14,"/pantallas/img/Cartas/4-espada.jpg", 0));
        mazo.add(new Carta(4, paloOro, 14,"/pantallas/img/Cartas/4-oro.jpg", 0));
        
        
        //indicador de carta utilizada
    }

    public ArrayList<Carta> getMazo() {
        return mazo;
    }
    
    public void iniciarPartida(){
        //Paso 1 : Creación de jugadores, componente generador de números aleatorios y obtención de parámetros de juego.
        jugadorMaquina = JugadorMaquina.getInstancia(this);
        jugadorMaquina.setMazo(mazo);
        jugadorMaquina.setGestorTruco(this);

        jugadorHumano = new JugadorHumano();
        jugadorHumano.setGestorTruco(this);
        
        generadorNumerosAleatorios = new Random();

        parametrosJuego = Parametros.getInstancia();
        estadoActualMesa = new EstadoMesa();
        estadoActualMesa.setRondaActual(1);
        jugadorMaquina.setCantidadPuntos(0);
        jugadorHumano.setCantidadPuntos(0);
        
        //creacion de pantalla
        pantalla2 = new Partido(this);   
        
        int randomOrdenInicialManoYPie = generadorNumerosAleatorios.nextInt(2);

        if (randomOrdenInicialManoYPie == 0) {
            //Mano: Jugador máquina.
            //Pie: Jugador humano.
            jugadorMaquina.setEsMano(true);
            jugadorHumano.setEsMano(false);
            estadoActualMesa.setJugadorMano(jugadorMaquina);
            estadoActualMesa.setJugadorPie(jugadorHumano);
        } else {
            //Mano: Jugador humano.
            //Pie: Jugador máquina.
            jugadorHumano.setEsMano(true);
            jugadorMaquina.setEsMano(false);
            estadoActualMesa.setJugadorMano(jugadorHumano);
            estadoActualMesa.setJugadorPie(jugadorMaquina);
        } 
           
        iniciarMano("Primera mano");       
    }
    
    public void iniciarMano(){
        pantalla2.habilitarCartas(true);
        pantalla2.habilitarBotonSiguiente(false);
        pantalla2.habilitarBotonCanto(true);
        estadoActualMesa.setRondaActual(1);
        estadoActualMesa.setGanadorRonda1(null);
        estadoActualMesa.setGanadorRonda2(null);
        estadoActualMesa.setGanadorRonda3(null);
                                   
        estadoActualMesa.setQuienTieneElQuiero(null);
        estadoActualMesa.setCantoActual(null);
        
        if(estadoActualMesa.getJugadorMano()==jugadorHumano){
            //Mano: Jugador máquina.
            //Pie: Jugador humano.
            jugadorMaquina.setEsMano(true);
            jugadorHumano.setEsMano(false);
            estadoActualMesa.setJugadorMano(jugadorMaquina);
            estadoActualMesa.setJugadorPie(jugadorHumano);
        }else{
            //Mano: Jugador humano.
            //Pie: Jugador máquina.
            jugadorHumano.setEsMano(true);
            jugadorMaquina.setEsMano(false);
            estadoActualMesa.setJugadorMano(jugadorHumano);
            estadoActualMesa.setJugadorPie(jugadorMaquina);
        } 
        
        //reparto cartas
        this.barajar();
        pantalla2.actualizarCartas(jugadorHumano.getMano());
        
        //l jugador maquina juega la primera carta        
        if(jugadorMaquina.esMano()==true){
            Carta primeraCartaJugadaJugadorMano = jugadorMaquina.iniciarTurno();
            
            this.estadoActualMesa.getCartasJugadasMaquina().add(primeraCartaJugadaJugadorMano);
            
            estadoActualMesa.setCartaManoRonda1(primeraCartaJugadaJugadorMano);
            pantalla2.actualizarCartas(primeraCartaJugadaJugadorMano,1,false);
            System.out.println("Carta jugada por mano: \n" + primeraCartaJugadaJugadorMano.toString());
        }
    }
    
    public void iniciarMano(String s){
        pantalla2.habilitarBotonSiguiente(false);
        estadoActualMesa.setRondaActual(1);
        estadoActualMesa.setGanadorRonda1(null);
        estadoActualMesa.setGanadorRonda2(null);
        estadoActualMesa.setGanadorRonda3(null);
        
        //reparto cartas
        this.barajar();
        pantalla2.actualizarCartas(jugadorHumano.getMano());
        
        //el jugador maquina juega la primera carta        
        if(jugadorMaquina.esMano()==true){
            Carta primeraCartaJugadaJugadorMano = jugadorMaquina.iniciarTurno();
     
            this.estadoActualMesa.getCartasJugadasMaquina().add(primeraCartaJugadaJugadorMano);
            
            estadoActualMesa.setCartaManoRonda1(primeraCartaJugadaJugadorMano);
            pantalla2.actualizarCartas(primeraCartaJugadaJugadorMano,1,false);
            System.out.println("Carta jugada por mano: \n" + primeraCartaJugadaJugadorMano.toString());
        }
    }
    
    public void cartaJugadaHumano(Carta c){
        jugadorHumano.removerCartaDeMano(c);
        estadoActualMesa.getCartasJugadasHumano().add(c);
        if(estadoActualMesa.getRondaActual()==1){
            Carta primeraCartaJugadaJugadorHumano = c;
            if(jugadorHumano.esMano()==false){                
                estadoActualMesa.setCartaPieRonda1(primeraCartaJugadaJugadorHumano);
                System.out.println("Carta jugada por pie: \n" + primeraCartaJugadaJugadorHumano.toString());
            }else{            
                estadoActualMesa.setCartaManoRonda1(primeraCartaJugadaJugadorHumano);
                System.out.println("Carta jugada por mano: \n" + primeraCartaJugadaJugadorHumano.toString());
                
                //Jugador maquina juega su carta
                Carta primeraCartaJugadaJugadorPie = jugadorMaquina.iniciarTurno();
                
                this.estadoActualMesa.getCartasJugadasMaquina().add(primeraCartaJugadaJugadorPie);
                
                estadoActualMesa.setCartaPieRonda1(primeraCartaJugadaJugadorPie);
                pantalla2.actualizarCartas(primeraCartaJugadaJugadorPie,1,false);
                System.out.println("Carta jugada por pie: \n" + primeraCartaJugadaJugadorPie.toString());
            }
            
            int resultadoRonda;
            
            if (estadoActualMesa.getCartaManoRonda1().getJerarquia() < estadoActualMesa.getCartaPieRonda1().getJerarquia()) {
                resultadoRonda = GANADOR_RONDA_MANO;
            } else if (estadoActualMesa.getCartaManoRonda1().getJerarquia() > estadoActualMesa.getCartaPieRonda1().getJerarquia()) {
                resultadoRonda = GANADOR_RONDA_PIE;
            } else {
                resultadoRonda = RONDA_EMPARDADA;
            }

            if (resultadoRonda == GANADOR_RONDA_MANO) {
                estadoActualMesa.setGanadorRonda1(estadoActualMesa.getJugadorMano());
            }
            if (resultadoRonda == GANADOR_RONDA_PIE) {
                estadoActualMesa.setGanadorRonda1(estadoActualMesa.getJugadorPie());
            }
            if (resultadoRonda == RONDA_EMPARDADA) {
                estadoActualMesa.setGanadorRonda1(null);
            }           
                        
            if(estadoActualMesa.getGanadorRonda1()==jugadorMaquina){
                estadoActualMesa.setJugadorMano(jugadorMaquina);
                estadoActualMesa.setJugadorPie(jugadorHumano);
                jugadorHumano.setEsMano(false);
                jugadorMaquina.setEsMano(true);
            }else{
                estadoActualMesa.setJugadorMano(jugadorHumano);
                estadoActualMesa.setJugadorPie(jugadorMaquina);
                jugadorHumano.setEsMano(true);
                jugadorMaquina.setEsMano(false);
            }
            estadoActualMesa.setRondaActual(estadoActualMesa.getRondaActual()+1);
            if(estadoActualMesa.getJugadorMano()==jugadorMaquina){
                //jugador maquina juega su carta para la ronda2
                Carta segundaCartaJugadaJugadorMano = jugadorMaquina.iniciarTurno();
                
                this.estadoActualMesa.getCartasJugadasMaquina().add(segundaCartaJugadaJugadorMano);
                
                estadoActualMesa.setCartaManoRonda2(segundaCartaJugadaJugadorMano);
                pantalla2.actualizarCartas(segundaCartaJugadaJugadorMano,2,false);
                System.out.println("Carta jugada por mano: \n" + segundaCartaJugadaJugadorMano.toString());
            }
            return;
        }
        if(estadoActualMesa.getRondaActual()==2){
            Carta segundaCartaJugadaJugadorHumano = c;
            if(jugadorHumano.esMano()==false){                
                estadoActualMesa.setCartaPieRonda2(segundaCartaJugadaJugadorHumano);
                System.out.println("Carta jugada por pie: \n" + segundaCartaJugadaJugadorHumano.toString());
            }else{            
                estadoActualMesa.setCartaManoRonda2(segundaCartaJugadaJugadorHumano);
                System.out.println("Carta jugada por mano: \n" + segundaCartaJugadaJugadorHumano.toString());
                
                //Jugador maquina juega su carta
                Carta segundaCartaJugadaJugadorPie = jugadorMaquina.iniciarTurno();
                
                this.estadoActualMesa.getCartasJugadasMaquina().add(segundaCartaJugadaJugadorPie);
                
                estadoActualMesa.setCartaPieRonda2(segundaCartaJugadaJugadorPie);
                pantalla2.actualizarCartas(segundaCartaJugadaJugadorPie,2,false);
                System.out.println("Carta jugada por pie: \n" + segundaCartaJugadaJugadorPie.toString());
            }
            int resultadoRonda;
            if (estadoActualMesa.getCartaManoRonda2().getJerarquia() < estadoActualMesa.getCartaPieRonda2().getJerarquia()) {
                resultadoRonda = GANADOR_RONDA_MANO;
            } else if (estadoActualMesa.getCartaManoRonda2().getJerarquia() > estadoActualMesa.getCartaPieRonda2().getJerarquia()) {
                resultadoRonda = GANADOR_RONDA_PIE;
            } else {
                resultadoRonda = RONDA_EMPARDADA;
            }

            if (resultadoRonda == GANADOR_RONDA_MANO) {
                estadoActualMesa.setGanadorRonda2(estadoActualMesa.getJugadorMano());
            }
            if (resultadoRonda == GANADOR_RONDA_PIE) {
                estadoActualMesa.setGanadorRonda2(estadoActualMesa.getJugadorPie());
            }
            if (resultadoRonda == RONDA_EMPARDADA) {
                estadoActualMesa.setGanadorRonda2(null);
            }           
                        
            if(estadoActualMesa.getGanadorRonda2()==jugadorMaquina){
                estadoActualMesa.setJugadorMano(jugadorMaquina);
                estadoActualMesa.setJugadorPie(jugadorHumano);
                jugadorHumano.setEsMano(false);
                jugadorMaquina.setEsMano(true);
            }else{
                estadoActualMesa.setJugadorMano(jugadorHumano);
                estadoActualMesa.setJugadorPie(jugadorMaquina);
                jugadorHumano.setEsMano(true);
                jugadorMaquina.setEsMano(false);
            }
            
            //determino si ya hay un ganador
            if(determinarGanador()!=null){
                if(determinarGanador()==ganadorRonda.HUMANO){
                    sumarPuntos(ganadorRonda.HUMANO);                  
                    pantalla2.habilitarCartas(false);
                    JOptionPane.showMessageDialog(null,mensajeResultadoRonda+ganadorRonda.HUMANO);
                    determinarFinJuego();
                    pantalla2.habilitarBotonSiguiente(true);
                    pantalla2.habilitarBotonCanto(false);
                    return;
                }else{
                    sumarPuntos(ganadorRonda.MAQUINA); 
                    pantalla2.habilitarCartas(false);
                    JOptionPane.showMessageDialog(null,mensajeResultadoRonda+ganadorRonda.MAQUINA);
                    determinarFinJuego();
                    pantalla2.habilitarBotonSiguiente(true);
                    pantalla2.habilitarBotonCanto(false);
                    return;
                }
            }           
            estadoActualMesa.setRondaActual(estadoActualMesa.getRondaActual()+1);;
            if(estadoActualMesa.getJugadorMano()==jugadorMaquina){
                //jugador maquina juega su carta para la ronda3
                Carta terceraCartaJugadaJugadorMano = jugadorMaquina.iniciarTurno();
                
                this.estadoActualMesa.getCartasJugadasMaquina().add(terceraCartaJugadaJugadorMano);
                
                estadoActualMesa.setCartaManoRonda3(terceraCartaJugadaJugadorMano);
                pantalla2.actualizarCartas(terceraCartaJugadaJugadorMano,3,false);
                System.out.println("Carta jugada por mano: \n" + terceraCartaJugadaJugadorMano.toString());
            }
            return;
        }
        if(estadoActualMesa.getRondaActual()==3){
            Carta terceraCartaJugadaJugadorHumano = c;
            if(jugadorHumano.esMano()==false){                
                estadoActualMesa.setCartaPieRonda3(terceraCartaJugadaJugadorHumano);
                System.out.println("Carta jugada por pie: \n" + terceraCartaJugadaJugadorHumano.toString());
            }else{            
                estadoActualMesa.setCartaManoRonda3(terceraCartaJugadaJugadorHumano);
                System.out.println("Carta jugada por mano: \n" + terceraCartaJugadaJugadorHumano.toString());
                
                //Jugador maquina juega su carta
                Carta terceraCartaJugadaJugadorPie = jugadorMaquina.iniciarTurno();
                
                this.estadoActualMesa.getCartasJugadasMaquina().add(terceraCartaJugadaJugadorPie);
                
                estadoActualMesa.setCartaPieRonda3(terceraCartaJugadaJugadorPie);
                pantalla2.actualizarCartas(terceraCartaJugadaJugadorPie,3,false);
                System.out.println("Carta jugada por pie: \n" + terceraCartaJugadaJugadorPie.toString());
            }
            int resultadoRonda;
            if (estadoActualMesa.getCartaManoRonda3().getJerarquia() < estadoActualMesa.getCartaPieRonda3().getJerarquia()) {
                resultadoRonda = GANADOR_RONDA_MANO;
            } else if (estadoActualMesa.getCartaManoRonda3().getJerarquia() > estadoActualMesa.getCartaPieRonda3().getJerarquia()) {
                resultadoRonda = GANADOR_RONDA_PIE;
            } else {
                resultadoRonda = RONDA_EMPARDADA;
            }

            if (resultadoRonda == GANADOR_RONDA_MANO) {
                estadoActualMesa.setGanadorRonda3(estadoActualMesa.getJugadorMano());
            }
            if (resultadoRonda == GANADOR_RONDA_PIE) {
                estadoActualMesa.setGanadorRonda3(estadoActualMesa.getJugadorPie());
            }
            if (resultadoRonda == RONDA_EMPARDADA) {
                estadoActualMesa.setGanadorRonda3(null);
            }           
                        
            if(estadoActualMesa.getGanadorRonda3()==jugadorMaquina){
                estadoActualMesa.setJugadorMano(jugadorMaquina);
                estadoActualMesa.setJugadorPie(jugadorHumano);
                jugadorHumano.setEsMano(false);
                jugadorMaquina.setEsMano(true);
            }else{
                estadoActualMesa.setJugadorMano(jugadorHumano);
                estadoActualMesa.setJugadorPie(jugadorMaquina);
                jugadorHumano.setEsMano(true);
                jugadorMaquina.setEsMano(false);
            }
            //determino el ganador
            if(determinarGanador()!=null){
                if(determinarGanador()==ganadorRonda.HUMANO){
                    sumarPuntos(ganadorRonda.HUMANO);
                    pantalla2.habilitarCartas(false);
                    JOptionPane.showMessageDialog(null,mensajeResultadoRonda+ganadorRonda.HUMANO);
                    determinarFinJuego();
                    pantalla2.habilitarBotonSiguiente(true);
                    pantalla2.habilitarBotonCanto(false);
                    return;
                }else{
                    sumarPuntos(ganadorRonda.MAQUINA);
                    pantalla2.habilitarCartas(false);
                    JOptionPane.showMessageDialog(null,mensajeResultadoRonda+ganadorRonda.MAQUINA);
                    determinarFinJuego();
                    pantalla2.habilitarBotonSiguiente(true);
                    pantalla2.habilitarBotonCanto(false);
                    return;
                }
            }
            estadoActualMesa.setRondaActual(estadoActualMesa.getRondaActual()+1);;
            return;
        }
        
    }
    
    public void cantoRealizado(boolean esHumano){
        Canto cantoRealizado=null;        
        if(estadoActualMesa.getCantoActual()==null) cantoRealizado= CantoTruco.getInstancia();
        if(estadoActualMesa.getCantoActual()==CantoTruco.getInstancia()) cantoRealizado= CantoRetruco.getInstancia();
        if(estadoActualMesa.getCantoActual()==CantoRetruco.getInstancia()) cantoRealizado= CantoVale4.getInstancia();
        
        RespuestaCanto respuestaCanto;
        if(esHumano){
            
            estadoActualMesa.setQuienTieneElQuiero(jugadorMaquina);
            pantalla2.habilitarBotonCanto(false);
            respuestaCanto = jugadorMaquina.responderCanto(cantoRealizado);

            if(respuestaCanto.getRespuestaAceptacion() == RespuestaCanto.RESPUESTA_CANTO_QUERIDO){
                System.out.println("Respuesta de Maquina: QUERIDO");
                JOptionPane.showMessageDialog(null,"RESPUESTA: QUERIDO");
                estadoActualMesa.setCantoActual(cantoRealizado);
                try{
                pantalla2.getCantarTrucoButton().setText("Cantar "+estadoActualMesa.getProximoCanto());
                }catch(NullPointerException e){}
            }
            if(respuestaCanto.getRespuestaAceptacion() == RespuestaCanto.RESPUESTA_CANTO_NO_QUERIDO){
                System.out.println("Respuesta de Maquina: NO QUERIDO");
                JOptionPane.showMessageDialog(null,"RESPUESTA: NO QUERIDO");
                sumarPuntos(ganadorRonda.HUMANO);
                pantalla2.habilitarCartas(false);
                System.out.println("Ganador de la ronda: HUMANO !!!!");
                JOptionPane.showMessageDialog(null,mensajeResultadoRonda+ganadorRonda.HUMANO);
                pantalla2.habilitarBotonCanto(false);
                pantalla2.habilitarBotonSiguiente(true);
                return;               
            }           
        }else{
            
            estadoActualMesa.setQuienTieneElQuiero(jugadorHumano);
            String message = "MAQUINA le canto " + cantoRealizado.getNombre() + "!";
            String[] options= {"Quiero","No quiero"};
            
            int resp=JOptionPane.showOptionDialog(null, message, cantoRealizado.getNombre(), 2, PLAIN_MESSAGE , null, options, "Quiero");            
            if(resp==0){
                respuestaCanto = new RespuestaCanto(RespuestaCanto.RESPUESTA_CANTO_QUERIDO, null);
                
                estadoActualMesa.setCantoActual(cantoRealizado);
                pantalla2.getCantarTrucoButton().setText("Cantar " + estadoActualMesa.getProximoCanto());
                    
                
            }else{
                respuestaCanto = new RespuestaCanto(RespuestaCanto.RESPUESTA_CANTO_NO_QUERIDO, null);
                sumarPuntos(ganadorRonda.MAQUINA);
                System.out.println("Ganador de la ronda: MAQUINA !!!!");
                pantalla2.habilitarCartas(false);
                JOptionPane.showMessageDialog(null,mensajeResultadoRonda+ganadorRonda.MAQUINA);
                pantalla2.habilitarBotonCanto(false);
                pantalla2.habilitarBotonSiguiente(true);
                return;
            }
        }
    }
    
    private void sumarPuntos(ganadorRonda g){
        int puntaje;
        if(estadoActualMesa.getCantoActual()!=null){
            puntaje=estadoActualMesa.getCantoActual().getPuntosEnJuego();                    
        }else{
            puntaje=1;
        }
        switch(g){
            case HUMANO:                               
                jugadorHumano.sumarPuntos(puntaje);
                pantalla2.actualizarPuntaje(jugadorHumano.getCantidadPuntos(),true);
                break;
            case MAQUINA:
                jugadorMaquina.sumarPuntos(puntaje);    
                pantalla2.actualizarPuntaje(jugadorMaquina.getCantidadPuntos(),false);
                break;
        }                
    }
    
    private ganadorRonda determinarGanador(){
        if(estadoActualMesa.getGanadorRonda1()==jugadorHumano && estadoActualMesa.getGanadorRonda2()==jugadorHumano){
            return ganadorRonda.HUMANO;
        }        
        if(estadoActualMesa.getGanadorRonda1()==jugadorMaquina && estadoActualMesa.getGanadorRonda2()==jugadorMaquina){
            return ganadorRonda.MAQUINA;
        }
        if(estadoActualMesa.getGanadorRonda1()==null && estadoActualMesa.getGanadorRonda2()==jugadorHumano){
            return ganadorRonda.HUMANO;
        }
        if(estadoActualMesa.getGanadorRonda1()==null && estadoActualMesa.getGanadorRonda2()==jugadorMaquina){
            return ganadorRonda.MAQUINA;
        }
        if(estadoActualMesa.getGanadorRonda2()==jugadorHumano && estadoActualMesa.getGanadorRonda3()==jugadorHumano){
            return ganadorRonda.HUMANO;
        }
        if(estadoActualMesa.getGanadorRonda2()==jugadorMaquina && estadoActualMesa.getGanadorRonda3()==jugadorMaquina){
            return ganadorRonda.MAQUINA;
        }
        if(estadoActualMesa.getGanadorRonda1()==jugadorHumano && estadoActualMesa.getGanadorRonda3()==jugadorHumano){
            return ganadorRonda.HUMANO;
        }
        if(estadoActualMesa.getGanadorRonda1()==jugadorMaquina && estadoActualMesa.getGanadorRonda3()==jugadorMaquina){
            return ganadorRonda.MAQUINA;
        }
        if(estadoActualMesa.getGanadorRonda1()==jugadorHumano && estadoActualMesa.getGanadorRonda2()==null){
            return ganadorRonda.HUMANO;
        }
        if(estadoActualMesa.getGanadorRonda1()==jugadorMaquina && estadoActualMesa.getGanadorRonda2()==null){
            return ganadorRonda.MAQUINA;
        }
        if(estadoActualMesa.getGanadorRonda1()==null && estadoActualMesa.getGanadorRonda2()==null & estadoActualMesa.getGanadorRonda3()==jugadorHumano){
            return ganadorRonda.HUMANO;
        }
        if(estadoActualMesa.getGanadorRonda1()==null && estadoActualMesa.getGanadorRonda2()==null & estadoActualMesa.getGanadorRonda3()==jugadorMaquina){
            return ganadorRonda.MAQUINA;
        }
        if(estadoActualMesa.getRondaActual()==3){
            if(estadoActualMesa.getGanadorRonda1()==jugadorHumano && estadoActualMesa.getGanadorRonda3()==null){
                return ganadorRonda.HUMANO;
            }
            if(estadoActualMesa.getGanadorRonda1()==jugadorMaquina && estadoActualMesa.getGanadorRonda3()==null){
                return ganadorRonda.MAQUINA;
            }
        }
        return null;
    }
    
    private void barajar() {
        ArrayList<Carta> mazoDuplicado = new ArrayList<>();

        mazo.forEach((cartaActual) -> {
            mazoDuplicado.add(cartaActual);
        });

        int randomCarta1JugadorMaquina = generadorNumerosAleatorios.nextInt(mazoDuplicado.size());
        Carta carta1JugadorMaquina = mazoDuplicado.get(randomCarta1JugadorMaquina);
        mazoDuplicado.remove(carta1JugadorMaquina);

        int randomCarta1JugadorHumano = generadorNumerosAleatorios.nextInt(mazoDuplicado.size());
        Carta carta1JugadorHumano = mazoDuplicado.get(randomCarta1JugadorHumano);
        mazoDuplicado.remove(carta1JugadorHumano);

        int randomCarta2JugadorMaquina = generadorNumerosAleatorios.nextInt(mazoDuplicado.size());
        Carta carta2JugadorMaquina = mazoDuplicado.get(randomCarta2JugadorMaquina);
        mazoDuplicado.remove(carta2JugadorMaquina);

        int randomCarta2JugadorHumano = generadorNumerosAleatorios.nextInt(mazoDuplicado.size());
        Carta carta2JugadorHumano = mazoDuplicado.get(randomCarta2JugadorHumano);
        mazoDuplicado.remove(carta2JugadorHumano);

        int randomCarta3JugadorMaquina = generadorNumerosAleatorios.nextInt(mazoDuplicado.size());
        Carta carta3JugadorMaquina = mazoDuplicado.get(randomCarta3JugadorMaquina);
        mazoDuplicado.remove(carta3JugadorMaquina);

        int randomCarta3JugadorHumano = generadorNumerosAleatorios.nextInt(mazoDuplicado.size());
        Carta carta3JugadorHumano = mazoDuplicado.get(randomCarta3JugadorHumano);
        mazoDuplicado.remove(carta3JugadorHumano);

        jugadorMaquina.repartirMano(carta1JugadorMaquina, carta2JugadorMaquina, carta3JugadorMaquina);
        jugadorHumano.repartirMano(carta1JugadorHumano, carta2JugadorHumano, carta3JugadorHumano);

        ArrayList<Carta> manoRepartidaJugadorMaquina = jugadorMaquina.getMano();
        ArrayList<Carta> manoRepartidaJugadorHumano = jugadorHumano.getMano();

        System.out.println("Mano repartida a jugador máquina:");
        System.out.println("");

        manoRepartidaJugadorMaquina.forEach((cartaActual) -> {
            System.out.println(cartaActual.toString() + "\n");
        });

        System.out.println("Mano repartida a jugador humano:");
        System.out.println("");

        System.out.println("-----");

        manoRepartidaJugadorHumano.forEach((cartaActual) -> {
            System.out.println(cartaActual.toString() + "\n");
        });
    }
    
    public JugadorMaquina getJugadorMaquina() {
        return jugadorMaquina;
    }

    public JugadorHumano getJugadorHumano() {
        return jugadorHumano;
    }
    
    public EstadoMesa getEstadoActualMesa(){
        return this.estadoActualMesa;
    }
    
    //La cantidad de puntos para ganar la partida esta definido en 5
    public void determinarFinJuego(){        
        if(jugadorMaquina.getCantidadPuntos()>=parametrosJuego.getCantidadPuntosPartido()){
            String mensajeFinJuego = "                 Ha ganado el jugador ";
            JOptionPane.showMessageDialog(null,mensajeFinJuego+ganadorRonda.MAQUINA+"!\nPresione siguiente para comenzar un juego nuevo.","                                      Fin del juego",PLAIN_MESSAGE);
            pantalla2.habilitarBotonCanto(false);
            estadoActualMesa.setTerminoElJuego(true);
        }else if(jugadorHumano.getCantidadPuntos()>=parametrosJuego.getCantidadPuntosPartido()){
            String mensajeFinJuego = "                 Ha ganado el jugador ";
            JOptionPane.showMessageDialog(null,mensajeFinJuego+ganadorRonda.HUMANO+"!\nPresione siguiente para comenzar un juego nuevo.","                                      Fin del juego",PLAIN_MESSAGE);
            pantalla2.habilitarBotonCanto(false);
            estadoActualMesa.setTerminoElJuego(true);
        }        
    }
}
