package truco_iar;

import Cantos.Canto;
import Actores.JugadorHumano;
import Actores.JugadorMaquina;
import Actores.JugadorTruco;
import Entidades.PaloCopas;
import Entidades.Carta;
import Entidades.Palo;
import Entidades.PaloOro;
import Entidades.PaloEspadas;
import Entidades.PaloBastos;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import pantallas.Inicial;
import pantallas.Partido;


public class GestorTruco{
    
    private final ArrayList<Carta> mazo;
    private final ArrayList<Palo> palos;

    private JugadorMaquina jugadorMaquina;
    private JugadorHumano jugadorHumano;

    private Random generadorNumerosAleatorios;

    Parametros parametrosJuego;

    private static final int GANADOR_RONDA_MANO = 1;
    private static final int GANADOR_RONDA_PIE = -1;
    private static final int RONDA_EMPARDADA = 0;

    private EstadoMesa estadoActualMesa;
    
    //Pantalla de partido AGREGADO POR LAUTA
    public Partido pantalla2;
    public boolean respuestaChupino;
    int ronda = 0;
    JugadorTruco jugadorMano;
    JugadorTruco jugadorPie;

    public GestorTruco() {

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

//        mazo.add(new Carta(1, paloEspadas, 1, "/pantallas/img/Cartas/1-espada.jpg"));
//        mazo.add(new Carta(2, paloEspadas, 6, "/pantallas/img/Cartas/2-espada.jpg"));
//        mazo.add(new Carta(3, paloEspadas, 5, "/pantallas/img/Cartas/3-espada.jpg"));
//        mazo.add(new Carta(4, paloEspadas, 14,"/pantallas/img/Cartas/4-espada.jpg"));
//        mazo.add(new Carta(5, paloEspadas, 13,"/pantallas/img/Cartas/5-espada.jpg"));
//        mazo.add(new Carta(6, paloEspadas, 12,"/pantallas/img/Cartas/6-espada.jpg"));
//        mazo.add(new Carta(7, paloEspadas, 3,"/pantallas/img/Cartas/7-espada.jpg"));
//        mazo.add(new Carta(10, paloEspadas, 10,"/pantallas/img/Cartas/10-espada.jpg"));
//        mazo.add(new Carta(11, paloEspadas, 9,"/pantallas/img/Cartas/11-espada.jpg"));
//        mazo.add(new Carta(12, paloEspadas, 8,"/pantallas/img/Cartas/12-espada.jpg"));
//
//        mazo.add(new Carta(1, paloBastos, 2,"/pantallas/img/Cartas/1-basto.jpg"));
//        mazo.add(new Carta(2, paloBastos, 6,"/pantallas/img/Cartas/2-basto.jpg"));
//        mazo.add(new Carta(3, paloBastos, 5,"/pantallas/img/Cartas/3-basto.jpg"));
//        mazo.add(new Carta(4, paloBastos, 14,"/pantallas/img/Cartas/4-basto.jpg"));
//        mazo.add(new Carta(5, paloBastos, 13,"/pantallas/img/Cartas/5-basto.jpg"));
//        mazo.add(new Carta(6, paloBastos, 12,"/pantallas/img/Cartas/6-basto.jpg"));
//        mazo.add(new Carta(7, paloBastos, 11,"/pantallas/img/Cartas/7-basto.jpg"));
//        mazo.add(new Carta(10, paloBastos, 10,"/pantallas/img/Cartas/10-basto.jpg"));
//        mazo.add(new Carta(11, paloBastos, 9,"/pantallas/img/Cartas/11-basto.jpg"));
//        mazo.add(new Carta(12, paloBastos, 8,"/pantallas/img/Cartas/12-basto.jpg"));
//
//        mazo.add(new Carta(1, paloOro, 7,"/pantallas/img/Cartas/1-oro.jpg"));
//        mazo.add(new Carta(2, paloOro, 6,"/pantallas/img/Cartas/2-oro.jpg"));
//        mazo.add(new Carta(3, paloOro, 5,"/pantallas/img/Cartas/3-oro.jpg"));
//        mazo.add(new Carta(4, paloOro, 14,"/pantallas/img/Cartas/4-oro.jpg"));
//        mazo.add(new Carta(5, paloOro, 13,"/pantallas/img/Cartas/5-oro.jpg"));
//        mazo.add(new Carta(6, paloOro, 12,"/pantallas/img/Cartas/6-oro.jpg"));
//        mazo.add(new Carta(7, paloOro, 4,"/pantallas/img/Cartas/7-oro.jpg"));
//        mazo.add(new Carta(10, paloOro, 10,"/pantallas/img/Cartas/10-oro.jpg"));
//        mazo.add(new Carta(11, paloOro, 9,"/pantallas/img/Cartas/11-oro.jpg"));
//        mazo.add(new Carta(12, paloOro, 8,"/pantallas/img/Cartas/12-oro.jpg"));
//
//        mazo.add(new Carta(1, paloCopas, 7,"/pantallas/img/Cartas/1-copa.jpg"));
//        mazo.add(new Carta(2, paloCopas, 6,"/pantallas/img/Cartas/2-copa.jpg"));
//        mazo.add(new Carta(3, paloCopas, 5,"/pantallas/img/Cartas/3-copa.jpg"));
//        mazo.add(new Carta(4, paloCopas, 14,"/pantallas/img/Cartas/4-copa.jpg"));
//        mazo.add(new Carta(5, paloCopas, 13,"/pantallas/img/Cartas/5-copa.jpg"));
//        mazo.add(new Carta(6, paloCopas, 12,"/pantallas/img/Cartas/6-copa.jpg"));
//        mazo.add(new Carta(7, paloCopas, 11,"/pantallas/img/Cartas/7-copa.jpg"));
//        mazo.add(new Carta(10, paloCopas, 10,"/pantallas/img/Cartas/10-copa.jpg"));
//        mazo.add(new Carta(11, paloCopas, 9,"/pantallas/img/Cartas/11-copa.jpg"));
//        mazo.add(new Carta(12, paloCopas, 8,"/pantallas/img/Cartas/12-copa.jpg"));

        estadoActualMesa = null;
    }

    public void mostrarMazo() {

        System.out.println("Cartas en mazo:\n-----------------------------------------\n");

        mazo.forEach((cartaActual) -> {
            System.out.println(cartaActual.toString() + "\n");
        });
    }

    public void iniciarPartida() {

        //Paso 1 : Creación de jugadores, componente generador de números aleatorios y obtención de parámetros de juego.
        //jugadorMaquina = JugadorMaquina.getInstancia();
        jugadorMaquina.setMazo(mazo);
        //jugadorMaquina.setGestorTruco(this);

        jugadorHumano = new JugadorHumano();
        //jugadorHumano.setGestorTruco(this);
        
        generadorNumerosAleatorios = new Random();

        parametrosJuego = Parametros.getInstancia();
        
        //creacion de pantalla
        //pantalla2 = new Partido(this);
        
           
        //Paso 2: Determinación de quién comienza siendo "Mano" y "Pie".

        int randomOrdenInicialManoYPie = generadorNumerosAleatorios.nextInt(2);

        if (randomOrdenInicialManoYPie == 0) {
            //Mano: Jugador máquina.
            //Pie: Jugador humano.

            jugadorMaquina.setEsMano(true);
            jugadorHumano.setEsMano(false);
            System.out.println("Mano: Jugador máquina");
        } else {
            //Mano: Jugador humano.
            //Pie: Jugador máquina.

            jugadorHumano.setEsMano(true);
            jugadorMaquina.setEsMano(false);
            System.out.println("Mano: Jugador humano");
        }

        //Paso 3: Jugar manos hasta que se determine un ganador de la partida.
        while (jugadorHumano.getCantidadPuntos() < parametrosJuego.getCantidadPuntosPartido() && jugadorMaquina.getCantidadPuntos() < parametrosJuego.getCantidadPuntosPartido()) {
            JugadorTruco ganadorMano = this.jugarMano();

            int cantidadDePuntosGanador = 1;
            if(estadoActualMesa.getCantoActual() != null) cantidadDePuntosGanador = estadoActualMesa.getCantoActual().getPuntosEnJuego();
            ganadorMano.sumarPuntos(cantidadDePuntosGanador);
            
            jugadorHumano.alternarManoPie();
            jugadorMaquina.alternarManoPie();


        }

        System.out.println("---------------");
        System.out.println("FIN DE LA PARTIDA.\n\n");
        System.out.println("Cantidad de puntos jugador máquina: " + jugadorMaquina.getCantidadPuntos() + "\n");
        System.out.println("Cantidad de puntos jugador humano: " + jugadorHumano.getCantidadPuntos() + "\n");
        //Paso 4: Determinar resultado de juego.
        if (jugadorHumano.getCantidadPuntos() >= parametrosJuego.getCantidadPuntosPartido() && jugadorMaquina.getCantidadPuntos() < parametrosJuego.getCantidadPuntosPartido()) {
            System.out.println("RESULTADO: GANADOR JUGADOR HUMANO");
            return;
        }
        if (jugadorMaquina.getCantidadPuntos() >= parametrosJuego.getCantidadPuntosPartido() && jugadorHumano.getCantidadPuntos() < parametrosJuego.getCantidadPuntosPartido()) {
            System.out.println("RESULTADO: GANADOR JUGADOR MÁQUINA");
            return;
        }
        System.out.println("RESULTADO: EMPATE");
    }

   
    
    private JugadorTruco jugarMano() {

        //Paso 1: Barajar y dar cartas a jugadores.
        this.barajar();
        
        //Actualiza la pantalla con las cartas
        pantalla2.actualizarCartas(jugadorHumano.getMano());
        
        
        JugadorTruco ganadorDeMano = null;        

        JugadorTruco jugadorGanadorRonda1 = null;
        JugadorTruco jugadorPerdedorRonda1 = null;

        JugadorTruco jugadorGanadorRonda2 = null;
        JugadorTruco jugadorPerdedorRonda2 = null;


        if (jugadorHumano.esMano()) {
            jugadorMano = jugadorHumano;
            jugadorPie = jugadorMaquina;
        } else {
            jugadorMano = jugadorMaquina;
            jugadorPie = jugadorHumano;
        }
        
        estadoActualMesa = new EstadoMesa();
        
        System.out.println("-----------");
        System.out.println("RONDA 1");
        System.out.println("-----------");
        //Paso 2 - Jugar ronda 1.
            //Paso 2.1: Conceder turno a jugador mano.
        Carta primeraCartaJugadaJugadorMano = jugadorMano.iniciarTurno();
        estadoActualMesa.setCartaManoRonda1(primeraCartaJugadaJugadorMano);
        System.out.println("Carta jugada por mano: \n" + primeraCartaJugadaJugadorMano.toString());
        
        if(jugadorHumano.esMano()){
            pantalla2.actualizarCartas(primeraCartaJugadaJugadorMano, 1,true);
        }else{
            pantalla2.actualizarCartas(primeraCartaJugadaJugadorMano, 1,false);
        }
        
            //Paso 2.2: Conceder turno a jugador pie.
        Carta primeraCartaJugadaJugadorPie = jugadorPie.iniciarTurno();
        estadoActualMesa.setCartaPieRonda1(primeraCartaJugadaJugadorPie);
        System.out.println("Carta jugada por pie: \n" + primeraCartaJugadaJugadorPie.toString());
        
        if(! jugadorHumano.esMano() ){
            pantalla2.actualizarCartas(primeraCartaJugadaJugadorPie, 1,true);
        }else{
            pantalla2.actualizarCartas(primeraCartaJugadaJugadorPie, 1,false);
        }
        
        
        int resultadoRonda;
        if (primeraCartaJugadaJugadorMano.getJerarquia() < primeraCartaJugadaJugadorPie.getJerarquia()) {
            resultadoRonda = GestorTruco.GANADOR_RONDA_MANO;
        } else if (primeraCartaJugadaJugadorMano.getJerarquia() > primeraCartaJugadaJugadorPie.getJerarquia()) {
            resultadoRonda = GestorTruco.GANADOR_RONDA_PIE;
        } else {
            resultadoRonda = GestorTruco.RONDA_EMPARDADA;
        }

        if (resultadoRonda == GestorTruco.GANADOR_RONDA_MANO) {
            jugadorGanadorRonda1 = jugadorMano;
            jugadorPerdedorRonda1 = jugadorPie;
            
        }
        if (resultadoRonda == GestorTruco.GANADOR_RONDA_PIE) {
            jugadorGanadorRonda1 = jugadorPie;
            jugadorPerdedorRonda1 = jugadorMano;
        }
        if (resultadoRonda == GestorTruco.RONDA_EMPARDADA) {
            jugadorGanadorRonda1 = null;
            jugadorPerdedorRonda1 = null;
        }
        
        estadoActualMesa.setGanadorRonda1(jugadorGanadorRonda1);

        /**
         * Paso 3 - Jugar ronda 2.
         *
         * 1 - Comprobar que en la ronda anterior hubo un ganador. 2- Si hubo un
         * ganador: 2.1 - conceder turno a jugador ganador de ronda anterior.
         * 2.2 - conceder turno a jugador perdedor de ronda anterior. 3-
         * Determinar resultado de esta ronda y definir nuevos ganador y
         * perdedor
         *
         */
        
        System.out.println("-----------");
        System.out.println("RONDA 2");
        System.out.println("-----------");

        if (jugadorGanadorRonda1 != null) {

            Carta segundaCartaJugadaGanadorRonda1 = jugadorGanadorRonda1.iniciarTurno();
            estadoActualMesa.setCartaManoRonda2(segundaCartaJugadaGanadorRonda1);
            System.out.println("Carta jugada por mano: \n" + segundaCartaJugadaGanadorRonda1.toString());
            
            if(jugadorHumano == jugadorGanadorRonda1){
            pantalla2.actualizarCartas(segundaCartaJugadaGanadorRonda1, 2,true);
            }else{
            pantalla2.actualizarCartas(segundaCartaJugadaGanadorRonda1, 2,false);
            }
            
            Carta segundaCartaJugadaPerdedorRonda1 = jugadorPerdedorRonda1.iniciarTurno();
            estadoActualMesa.setCartaPieRonda2(segundaCartaJugadaPerdedorRonda1);
            System.out.println("Carta jugada por pie: \n" + segundaCartaJugadaPerdedorRonda1.toString());
            
            if(!(jugadorHumano == jugadorGanadorRonda1)){
            pantalla2.actualizarCartas(segundaCartaJugadaPerdedorRonda1, 2,true);
            }else{
            pantalla2.actualizarCartas(segundaCartaJugadaPerdedorRonda1, 2,false);
            }
                        
            if (segundaCartaJugadaGanadorRonda1.getJerarquia() < segundaCartaJugadaPerdedorRonda1.getJerarquia()) {
                resultadoRonda = GestorTruco.GANADOR_RONDA_MANO;
            } else if (segundaCartaJugadaGanadorRonda1.getJerarquia() > segundaCartaJugadaPerdedorRonda1.getJerarquia()) {
                resultadoRonda = GestorTruco.GANADOR_RONDA_PIE;
            } else {
                resultadoRonda = GestorTruco.RONDA_EMPARDADA;
            }

            if (resultadoRonda == GestorTruco.GANADOR_RONDA_MANO) {
                //DAR POR GANADOR AL JUGADOR GANADOR RONDA 1.

                return jugadorGanadorRonda1;
            }
            if (resultadoRonda == GestorTruco.GANADOR_RONDA_PIE) {
                //SEGUIR JUGANDO, ESTAN 1 A 1.

                jugadorGanadorRonda2 = jugadorPerdedorRonda1;
                jugadorPerdedorRonda2 = jugadorGanadorRonda1;
            }
            if (resultadoRonda == GestorTruco.RONDA_EMPARDADA) {
                //DAR POR GANADOR AL JUGADOR GANADOR RONDA 1L
                return jugadorGanadorRonda1;
            }
        } else {
            
            //Empardaron en 1ra
            
            Carta segundaCartaJugadaMano = jugadorMano.iniciarTurno();
            estadoActualMesa.setCartaManoRonda2(segundaCartaJugadaMano);
            System.out.println("Carta jugada por mano: \n" + segundaCartaJugadaMano.toString());
            
            if(jugadorHumano == jugadorMano){
            pantalla2.actualizarCartas(segundaCartaJugadaMano, 2,true);
            }else{
            pantalla2.actualizarCartas(segundaCartaJugadaMano, 2,false);
            }
            
            Carta segundaCartaJugadaPie = jugadorPie.iniciarTurno();
            estadoActualMesa.setCartaPieRonda2(segundaCartaJugadaPie);
            System.out.println("Carta jugada por pie: \n" + segundaCartaJugadaPie.toString());
            
            if(!(jugadorHumano == jugadorMano)){
            pantalla2.actualizarCartas(segundaCartaJugadaPie, 2,true);
            }else{
            pantalla2.actualizarCartas(segundaCartaJugadaPie, 2,false);
            }
                        
            if (segundaCartaJugadaMano.getJerarquia() < segundaCartaJugadaPie.getJerarquia()) {
                resultadoRonda = GestorTruco.GANADOR_RONDA_MANO;
            } else if (segundaCartaJugadaMano.getJerarquia() > segundaCartaJugadaPie.getJerarquia()) {
                resultadoRonda = GestorTruco.GANADOR_RONDA_PIE;
            } else {
                resultadoRonda = GestorTruco.RONDA_EMPARDADA;
            }

            
            
            if (resultadoRonda == GestorTruco.GANADOR_RONDA_MANO) {
                //Ganador de 2da: Mano
                return jugadorMano;    
            }
            if (resultadoRonda == GestorTruco.GANADOR_RONDA_PIE) {
                //Ganador de 2da: Pie
                return jugadorPie;
            }
            if (resultadoRonda == GestorTruco.RONDA_EMPARDADA) {
                //Empardaron en 2da.
                
                jugadorGanadorRonda2 = null;
                jugadorPerdedorRonda2 = null;
            }
        }
        
        estadoActualMesa.setGanadorRonda2(jugadorGanadorRonda2);

        System.out.println("-----------");
        System.out.println("RONDA 3");
        System.out.println("-----------");
        
        if(jugadorGanadorRonda2 != null){
            
            //un jugador ganó 1ra y el otro ganó 2da.
            
            Carta terceraCartaJugadaGanadorRonda2 = jugadorGanadorRonda2.iniciarTurno();
            estadoActualMesa.setCartaManoRonda3(terceraCartaJugadaGanadorRonda2);
            System.out.println("Carta jugada por mano: \n" + terceraCartaJugadaGanadorRonda2.toString());
            
            if(jugadorHumano == jugadorGanadorRonda2){
            pantalla2.actualizarCartas(terceraCartaJugadaGanadorRonda2, 3, true);
            }else{
            pantalla2.actualizarCartas(terceraCartaJugadaGanadorRonda2, 3,false);
            }
            
            Carta terceraCartaJugadaPerdedorRonda2 = jugadorPerdedorRonda2.iniciarTurno();
            estadoActualMesa.setCartaPieRonda3(terceraCartaJugadaPerdedorRonda2);
            System.out.println("Carta jugada por pie: \n" + terceraCartaJugadaPerdedorRonda2.toString());
            
            if(jugadorHumano == jugadorPerdedorRonda2){
            pantalla2.actualizarCartas(terceraCartaJugadaPerdedorRonda2, 3, true);
            }else{
            pantalla2.actualizarCartas(terceraCartaJugadaPerdedorRonda2, 3, false);
            }
                        
            
            if (terceraCartaJugadaGanadorRonda2.getJerarquia() < terceraCartaJugadaPerdedorRonda2.getJerarquia()) {
                resultadoRonda = GestorTruco.GANADOR_RONDA_MANO;
            } else if (terceraCartaJugadaGanadorRonda2.getJerarquia() > terceraCartaJugadaPerdedorRonda2.getJerarquia()) {
                resultadoRonda = GestorTruco.GANADOR_RONDA_PIE;
            } else {
                resultadoRonda = GestorTruco.RONDA_EMPARDADA;
            }

            if (resultadoRonda == GestorTruco.GANADOR_RONDA_MANO) {
                //Ganador de 3ra: Mano
                return jugadorGanadorRonda2;
            }
            if (resultadoRonda == GestorTruco.GANADOR_RONDA_PIE) {
                //Ganador de 3ra: Pie
                return jugadorPerdedorRonda2;
            }
            
            if (resultadoRonda == GestorTruco.RONDA_EMPARDADA) {
                //Empardaron en 3ra.
                return jugadorGanadorRonda1;
            }
        }
        else{
            //ambos jugadores empardaron 1ra y 2da.
            
            Carta terceraCartaJugadaMano = jugadorMano.iniciarTurno();
            estadoActualMesa.setCartaManoRonda3(terceraCartaJugadaMano);
            System.out.println("Carta jugada por mano: \n" + terceraCartaJugadaMano.toString());
            
            if((jugadorHumano == jugadorMano)){
            pantalla2.actualizarCartas(terceraCartaJugadaMano, 3, true);
            }else{
            pantalla2.actualizarCartas(terceraCartaJugadaMano, 3, false);
            }
            
            Carta terceraCartaJugadaPie = jugadorPie.iniciarTurno();
            estadoActualMesa.setCartaPieRonda3(terceraCartaJugadaPie);
            System.out.println("Carta jugada por pie: \n" + terceraCartaJugadaPie.toString());
            
            if(!(jugadorHumano == jugadorMano)){
            pantalla2.actualizarCartas(terceraCartaJugadaPie, 3, false);
            }else{
            pantalla2.actualizarCartas(terceraCartaJugadaPie, 3, true);
            }
            
            if (terceraCartaJugadaMano.getJerarquia() < terceraCartaJugadaPie.getJerarquia()) {
                resultadoRonda = GestorTruco.GANADOR_RONDA_MANO;
            } else if (terceraCartaJugadaMano.getJerarquia() > terceraCartaJugadaPie.getJerarquia()) {
                resultadoRonda = GestorTruco.GANADOR_RONDA_PIE;
            } else {
                resultadoRonda = GestorTruco.RONDA_EMPARDADA;
            }


            
            if (resultadoRonda == GestorTruco.GANADOR_RONDA_MANO) {
                return jugadorMano;
            }
            if (resultadoRonda == GestorTruco.GANADOR_RONDA_PIE) {
                return jugadorPie;
            }
            if (resultadoRonda == GestorTruco.RONDA_EMPARDADA) {
                return jugadorMano;
            }
        }
        return null;
    }
    
    private void jugarRondaMaquina(int ronda){
        switch(ronda){
            case 1:   
                System.out.println("-----------");
                System.out.println("RONDA 1");
                System.out.println("-----------");
                if(jugadorMano == jugadorMaquina){
                    Carta primeraCartaJugadaJugadorMano = jugadorMano.iniciarTurno();
                    estadoActualMesa.setCartaManoRonda1(primeraCartaJugadaJugadorMano);
                    System.out.println("Carta jugada por mano: \n" + primeraCartaJugadaJugadorMano.toString());
                    pantalla2.actualizarCartas(primeraCartaJugadaJugadorMano, 1,false);
                }
                if(jugadorMano == jugadorHumano){
                    
                }               
                Carta primeraCartaJugadaJugadorPie = jugadorPie.iniciarTurno();
                estadoActualMesa.setCartaPieRonda1(primeraCartaJugadaJugadorPie);
                System.out.println("Carta jugada por pie: \n" + primeraCartaJugadaJugadorPie.toString());

                if(! jugadorHumano.esMano() ){
                    pantalla2.actualizarCartas(primeraCartaJugadaJugadorPie, 1,true);
                }else{
                    pantalla2.actualizarCartas(primeraCartaJugadaJugadorPie, 1,false);
                }


                int resultadoRonda;
//                if (primeraCartaJugadaJugadorMano.getJerarquia() < primeraCartaJugadaJugadorPie.getJerarquia()) {
//                    resultadoRonda = GestorTruco.GANADOR_RONDA_MANO;
//                } else if (primeraCartaJugadaJugadorMano.getJerarquia() > primeraCartaJugadaJugadorPie.getJerarquia()) {
//                    resultadoRonda = GestorTruco.GANADOR_RONDA_PIE;
//                } else {
//                    resultadoRonda = GestorTruco.RONDA_EMPARDADA;
//                }
//
//                if (resultadoRonda == GestorTruco.GANADOR_RONDA_MANO) {
//                    jugadorGanadorRonda1 = jugadorMano;
//                    jugadorPerdedorRonda1 = jugadorPie;
//
//                }
//                if (resultadoRonda == GestorTruco.GANADOR_RONDA_PIE) {
//                    jugadorGanadorRonda1 = jugadorPie;
//                    jugadorPerdedorRonda1 = jugadorMano;
//                }
//                if (resultadoRonda == GestorTruco.RONDA_EMPARDADA) {
//                    jugadorGanadorRonda1 = null;
//                    jugadorPerdedorRonda1 = null;
//                }
//
//                estadoActualMesa.setGanadorRonda1(jugadorGanadorRonda1);
                break;
            case 2:
                break;
            case 3:
                break;
        }
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

    public EstadoMesa getEstadoActualMesa(){
        return this.estadoActualMesa;
    }
    
    public void cantoRealizado(JugadorTruco realizadorCanto, Canto cantoRealizado){
        
        System.out.println("CANTO REALIZADO: " + cantoRealizado.getNombre() + "\n\n");
        
        RespuestaCanto respuestaCanto;
        JugadorTruco respondedor;
        
        if(realizadorCanto.equals(jugadorMaquina)){
            respondedor = jugadorHumano;
            String message = "IA le canto " + cantoRealizado.getNombre() + "!";
            String[] options= {"Quiero","No quiero"};
            //int resp = JOptionPane.showConfirmDialog(null, message, cantoRealizado.getNombre(), JOptionPane.YES_NO_OPTION);
            int resp=JOptionPane.showOptionDialog(null, message, "TRUCO", 2, PLAIN_MESSAGE , null,options ,"Quiero" );
            if(resp==0){
                respuestaCanto = new RespuestaCanto(RespuestaCanto.RESPUESTA_CANTO_QUERIDO, null);
            }else{
                respuestaCanto = new RespuestaCanto(RespuestaCanto.RESPUESTA_CANTO_NO_QUERIDO, null);
            }
        }
        else{
            respondedor = jugadorMaquina;
            respuestaCanto = respondedor.responderCanto(cantoRealizado);
            
            if(respuestaCanto.getRespuestaAceptacion() == RespuestaCanto.RESPUESTA_CANTO_QUERIDO){
            System.out.println("RESPUESTA: QUERIDO");
            estadoActualMesa.setCantoActual(cantoRealizado);
                if(respuestaCanto.getCantoContraoferta() != null){
                    this.cantoRealizado(respondedor, respuestaCanto.getCantoContraoferta());
                }
            }
                if(respuestaCanto.getRespuestaAceptacion() == RespuestaCanto.RESPUESTA_CANTO_NO_QUERIDO){
            System.out.println("RESPUESTA: NO QUERIDO");
            }
        }             
    }
    
    public boolean isRespuestaChupino() {
        return respuestaChupino;
    }

    public void setRespuestaChupino(boolean respuestaChupino) {
        this.respuestaChupino = respuestaChupino;
    }
    
    public JugadorMaquina getJugadorMaquina() {
        return jugadorMaquina;
    }

    public JugadorHumano getJugadorHumano() {
        return jugadorHumano;
    }
    
    public int getRonda(){
        return this.ronda;
    }
}
