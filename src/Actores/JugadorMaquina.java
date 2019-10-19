package Actores;

import Entidades.Carta;
import java.util.ArrayList;
import Cantos.Canto;
import Cantos.CantoRetruco;
import Cantos.CantoTruco;
import Cantos.CantoVale4;
import Entidades.BondadCarta;
import java.util.HashMap;
import java.util.List;
import net.sourceforge.jFuzzyLogic.FIS;
import truco_iar.EstadoMesa;
import truco_iar.GestorTrucoL;
import truco_iar.Parametros;
import truco_iar.RespuestaCanto;

public class JugadorMaquina extends JugadorTruco {

    private ArrayList<Carta> mazo;
    private HashMap<Carta, Integer> hashMapCartasValoresDominioFuzzy;

    private ArrayList<ArrayList<Carta>> jerarquias;

    private static JugadorMaquina instancia;

    private int rondaActual;

    private final GestorTrucoL gestor;

    private JugadorMaquina(GestorTrucoL gestor) {
        super();
        super.gestorTruco = null;
        this.rondaActual = 0;
        this.gestor = gestor;
    }

    public static JugadorMaquina getInstancia(GestorTrucoL gestor) {
        if (instancia == null) {
            instancia = new JugadorMaquina(gestor);
        }
        return instancia;
    }

    public void setMazo(ArrayList<Carta> mazo) {
        this.mazo = mazo;

        jerarquias = new ArrayList<>();

        for (int i = 1; i < 15; i++) {
            jerarquias.add(new ArrayList<>());
        }

        mazo.forEach((cartaActual) -> {
            int jerarquiaCartaActual = cartaActual.getJerarquia();
            jerarquias.get(jerarquiaCartaActual - 1).add(cartaActual);
        });

        hashMapCartasValoresDominioFuzzy = new HashMap<>();

        int i = 0;
        for (Carta cartaActual : mazo) {
            hashMapCartasValoresDominioFuzzy.put(cartaActual, i);
            i++;
        }

    }

    public ArrayList<Carta> getMazo() {
        return this.mazo;
    }

    public ArrayList<Carta> getCartasEnJerarquia(int jerarquia) {
        return jerarquias.get(jerarquia - 1);
    }

    @Override
    public Carta iniciarTurno() {

        //Obtención de estado actual y determinación de jugada.
        EstadoMesa estadoActualMesa = gestorTruco.getEstadoActualMesa();

        Carta cartaJugadaPorManoRonda1 = estadoActualMesa.getCartaManoRonda1();
        Carta cartaJugadaPorPieRonda1 = estadoActualMesa.getCartaPieRonda1();
        JugadorTruco ganadorRonda1 = estadoActualMesa.getGanadorRonda1();

        Carta cartaJugadaPorManoRonda2 = estadoActualMesa.getCartaManoRonda2();
        Carta cartaJugadaPorPieRonda2 = estadoActualMesa.getCartaManoRonda2();
        JugadorTruco ganadorRonda2 = estadoActualMesa.getGanadorRonda2();

        Carta cartaJugadaPorManoRonda3 = estadoActualMesa.getCartaManoRonda3();
        Carta cartaJugadaPorPieRonda3 = estadoActualMesa.getCartaManoRonda3();
        JugadorTruco ganadorRonda3 = estadoActualMesa.getGanadorRonda3();

        //Ya están obtenidos los datos del estado actual de la mesa, estamos en condiciones de inferir la jugada.
        FIS fis = FIS.load("recursos\\jFuzzyLogic\\prueba_fuzzy.fcl", true);
        //JFuzzyChart.get().chart(fis);

        ArrayList<BondadCarta> bondadesCartasMano = new ArrayList<>();
        for (Carta cartaActual : mano) {
            BondadCarta bondadCarta = new BondadCarta(cartaActual, this.calcularBondad(cartaActual));
            bondadesCartasMano.add(bondadCarta);
//fis.setVariable("bondad_carta_" + i,hashMapCartasValoresDominioFuzzy.get(cartaActual));
        }

        double promedioBondad = 0;
        double totalBondad = 0;
        double cantidadBondades = bondadesCartasMano.size();

        for (BondadCarta bondadCartaActual : bondadesCartasMano) {
            totalBondad += bondadCartaActual.getBondad();
        }

        promedioBondad = totalBondad / cantidadBondades;

        fis.setVariable("valor_mano", promedioBondad);

        fis.evaluate();

        System.out.println(fis.getVariable("valor_mano"));
        System.out.println(fis.getVariable("decision_juego"));

        double evaluacionNinguno = fis.getVariable("decision_juego").getMembership("ninguno");
        double evaluacionTruco = fis.getVariable("decision_juego").getMembership("truco");
        double evaluacionRetruco = fis.getVariable("decision_juego").getMembership("retruco");
        double evaluacionValeCuatro = fis.getVariable("decision_juego").getMembership("vale_cuatro");

        Parametros parametrosJuego = Parametros.getInstancia();

        if (evaluacionValeCuatro >= parametrosJuego.getUmbralJugarValeCuatro()) {

            System.out.println("");
            System.out.println("ESTOY DISPUESTO A CANTAR VALE 4");
            System.out.println("");

            if (gestor.getEstadoActualMesa().getCantoActual() == null) {
                gestor.cantoRealizado(false);
                //cantar truco
            } else {
                if (gestor.getEstadoActualMesa().getCantoActual().equals(CantoVale4.getInstancia())) {
                    //jugar callado
                } else if (gestor.getEstadoActualMesa().getCantoActual().equals(CantoRetruco.getInstancia())) {
                    if (gestor.getEstadoActualMesa().getQuienTieneElQuiero().equals(this) || gestor.getEstadoActualMesa().getQuienTieneElQuiero() == null) {
                        //cantar retruco
                        gestor.cantoRealizado(false);
                    } else {
                        //jugar callado
                    }
                } else if (gestor.getEstadoActualMesa().getCantoActual().equals(CantoTruco.getInstancia())) {
                    if (gestor.getEstadoActualMesa().getQuienTieneElQuiero().equals(this) || gestor.getEstadoActualMesa().getQuienTieneElQuiero() == null) {
                        //cantar truco
                        gestor.cantoRealizado(false);
                    } else {
                        //jugar callado
                    }
                }
            }
        } /**
         * if(gestor.estaCantadoTruco Si hay cantado truco: Si hay cantado
         * retruco: Si hay cantado vale4: Jugar callado Si no: Yo tengo el vale
         * 4? Cantar vale 4 Si no: Jugar callado Si no: Yo tengo el retruco?
         * Cantar retruco Si no: Jugar callado Si no: Cantar truco
         */
        else if (evaluacionRetruco >= parametrosJuego.getUmbralJugarRetruco()) {

            //Estoy dispuesto a cantar retruco
            System.out.println("");
            System.out.println("ESTOY DISPUESTO A CANTAR RETRUCO");
            System.out.println("");
            if (gestor.getEstadoActualMesa().getCantoActual() != null) {
                if (gestor.getEstadoActualMesa().getCantoActual().equals(CantoTruco.getInstancia())) {
                    if (gestor.getEstadoActualMesa().getQuienTieneElQuiero().equals(this) || gestor.getEstadoActualMesa().getQuienTieneElQuiero() == null) {
                        //cantar retruco
                        gestor.cantoRealizado(false);
                    } else {
                        //jugar callado
                    }
                }
            } else {
                gestor.cantoRealizado(false);
            }
        } /**
         * Si hay cantado truco Si hay cantado retruco: -HECHO Jugar callado
         * -HECHO Si no: Yo tengo el retuco? Cantar retuco Si no: Jugar callado
         * Si no: Cantar truco
         */
        else if (evaluacionTruco
                >= parametrosJuego.getUmbralJugarTruco()) {

            System.out.println("");
            System.out.println("ESTOY DISPUESTO A CANTAR TRUCO");
            System.out.println("");

            if (gestor.getEstadoActualMesa().getCantoActual() != null) {
            } else {
                gestor.cantoRealizado(false);
                //cantar truco
            }
        }

        /**
         * Si hay cantado truco: Juego callado Si no: Canto truco
         *
         * Jugar callado
         */
        //fis.setVariable("bondad_carta_1",0);
        //fis.setVariable("bondad_carta_2",1);
        //fis.setVariable("bondad_carta_3",2);
        //fis.getVariable("bondad_mano").evaluate();
        //fis.evaluate();
        //System.out.println();
//        System.out.println(fis.getVariable("bondad_carta_1"));
//        System.out.println(fis.getVariable("bondad_carta_2"));
//        System.out.println(fis.getVariable("bondad_carta_3"));
//        
//        System.out.println(fis.getVariable("bondad_mano"));
//        
        //Devolucion de carta de manera pensada
        Carta cartaAJugar = null;

        if (gestor.getEstadoActualMesa()
                .getJugadorMano().equals(this)) {
            //VOY PRIMERO

            cartaAJugar = this.getCartaMasAltaEnMano();
        } else {
            //VOY SEGUNDO

            ArrayList<Carta> cartasJugadasPorHumano = gestor.getEstadoActualMesa().getCartasJugadasHumano();

            Carta cartaJugadaPorHumanoEnEstaRonda = cartasJugadasPorHumano.get(cartasJugadasPorHumano.size() - 1);

            List<Carta> cartasMejoresALaDeHumano = this.getCartasEnManoQueLeGananA(cartaJugadaPorHumanoEnEstaRonda);

            if (cartasMejoresALaDeHumano.isEmpty()) {
                
                List<Carta> cartasQueEmpardanALaDeHumano = this.getCartasEnManoQueEmpardanCon(cartaJugadaPorHumanoEnEstaRonda);
                if(!cartasQueEmpardanALaDeHumano.isEmpty())
                    cartaAJugar = cartasQueEmpardanALaDeHumano.get(0);
                else
                    cartaAJugar = this.getCartaMasBajaEnMano();
            } else {
                cartaAJugar = this.getCartaMenorEnConjunto(cartasMejoresALaDeHumano);
            }
        }

        mano.remove(cartaAJugar);
        rondaActual++;
        return cartaAJugar;

        //Devolucion de carta de manera aleatoria
//        Random generadorNumerosAleatorios = new Random();
//        int randomCartaAJugar = generadorNumerosAleatorios.nextInt(mano.size());
//        Carta cartaAJugar = mano.get(randomCartaAJugar);
//
//        mano.remove(cartaAJugar);
//
//        rondaActual++;
//
//        return cartaAJugar;
    }

    private Carta getCartaMenorEnConjunto(List<Carta> cartasMejoresALaDeHumano) {

        Carta resultado = null;

        for (Carta cartaActual : cartasMejoresALaDeHumano) {
            if (resultado == null) {
                resultado = cartaActual;
            } else {
                if (cartaActual.getJerarquia() > resultado.getJerarquia()) {
                    resultado = cartaActual;
                }
            }
        }
        return resultado;
    }

    private Carta getCartaMasBajaEnMano() {

        int maximaJerarquia = 1;
        Carta resultado = null;

        for (Carta cartaActual : mano) {
            if (cartaActual.getJerarquia() > maximaJerarquia) {
                resultado = cartaActual;
                maximaJerarquia = cartaActual.getJerarquia();
            }
        }

        return resultado;
    }

    private List<Carta> getCartasEnManoQueLeGananA(Carta c) {

        List<Carta> resultado = new ArrayList<>();

        for (Carta cartaActual : mano) {
            if (cartaActual.getJerarquia() < c.getJerarquia()) {
                resultado.add(cartaActual);
            }
        }

        return resultado;
    }

        private List<Carta> getCartasEnManoQueEmpardanCon(Carta c) {

        List<Carta> resultado = new ArrayList<>();

        for (Carta cartaActual : mano) {
            if (cartaActual.getJerarquia() == c.getJerarquia()) {
                resultado.add(cartaActual);
            }
        }

        return resultado;
    }
    
    private Carta getCartaMasAltaEnMano() {

        int menorJerarquia = 14;
        Carta resultado = null;

        for (Carta cartaActual : mano) {
            if (cartaActual.getJerarquia() < menorJerarquia) {
                resultado = cartaActual;
                menorJerarquia = cartaActual.getJerarquia();
            }
        }

        return resultado;
    }

    private double calcularBondad(Carta c) {
        //cantidad de cartas contra las que estoy jugando
        System.out.println("Bondad carta " + c + ":");
        System.out.println("---");

        double bondad;
        double c1;
        double c2;
        double c3;
        double c4;
        
        c1 = 34 + (3 - gestor.getEstadoActualMesa().getCartasJugadasHumano().size());
        c2 = c.getCantCartasAQueLeGanaDe40();

        c3 = cantidadDeCartasPeoresALaActualEnMiMano(c);

        c4 = cantidadDeCartasPeoresALaActualEnMesa(c);

        bondad = (c2 - c3 - c4) / c1;

        
        System.out.println("C1: " + c1);
        System.out.println("C2: " + c2);
        System.out.println("C3: " + c3);
        System.out.println("C4: " + c4);
        System.out.println("Bondad: " + bondad);

        //esto es dudoso, y es a causa del else de arriba.
//        if(bondad > 1) bondad = 1;
//        if(bondad < 0) bondad = 0;
//        
        return bondad;
    }

    private int cantidadDeCartasPeoresALaActualEnMiMano(Carta c) {
        int cantidad = 0;
        for (Carta card : this.getMano()) {
            if (card.getJerarquia() > c.getJerarquia()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    private int cantidadDeCartasEnMesa() {
        return gestor.getEstadoActualMesa().getCartasJugadasHumano().size() + gestor.getEstadoActualMesa().getCartasJugadasHumano().size();
    }

    private int cantidadDeCartasPeoresALaActualEnMesa(Carta c) {
        int cantidad = 0;

        ArrayList<Carta> cartasJugadasHumano = gestor.getEstadoActualMesa().getCartasJugadasHumano();
        ArrayList<Carta> cartasJugadasMaquina = gestor.getEstadoActualMesa().getCartasJugadasMaquina();

        ArrayList<Carta> cartasJugadas = new ArrayList<>();

        for (Carta cartaActual : cartasJugadasHumano) {
            cartasJugadas.add(cartaActual);
        }

        for (Carta cartaActual : cartasJugadasMaquina) {
            cartasJugadas.add(cartaActual);
        }

        for (Carta card : cartasJugadas) {
            if (card.getJerarquia() > c.getJerarquia()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    @Override
    public RespuestaCanto responderCanto(Canto cantoRealizado) {

        RespuestaCanto respuestaCanto = new RespuestaCanto();

        respuestaCanto.setCantoContraoferta(null);
        respuestaCanto.setRespuestaAceptacion(RespuestaCanto.RESPUESTA_CANTO_NO_QUERIDO);

        FIS fis = FIS.load("recursos\\jFuzzyLogic\\prueba_fuzzy.fcl", true);
        //JFuzzyChart.get().chart(fis);

        ArrayList<BondadCarta> bondadesCartasMano = new ArrayList<>();
        for (Carta cartaActual : mano) {
            BondadCarta bondadCarta = new BondadCarta(cartaActual, this.calcularBondad(cartaActual));
            bondadesCartasMano.add(bondadCarta);
//fis.setVariable("bondad_carta_" + i,hashMapCartasValoresDominioFuzzy.get(cartaActual));
        }

        double promedioBondad = 0;
        double totalBondad = 0;
        double cantidadBondades = bondadesCartasMano.size();

        for (BondadCarta bondadCartaActual : bondadesCartasMano) {
            totalBondad += bondadCartaActual.getBondad();
        }

        promedioBondad = totalBondad / cantidadBondades;

        fis.setVariable("valor_mano", promedioBondad);

        fis.evaluate();

        System.out.println(fis.getVariable("valor_mano"));
        System.out.println(fis.getVariable("decision_juego"));

        double evaluacionNinguno = fis.getVariable("decision_juego").getMembership("ninguno");
        double evaluacionTruco = fis.getVariable("decision_juego").getMembership("truco");
        double evaluacionRetruco = fis.getVariable("decision_juego").getMembership("retruco");
        double evaluacionValeCuatro = fis.getVariable("decision_juego").getMembership("vale_cuatro");

        Parametros parametrosJuego = Parametros.getInstancia();

        if (evaluacionValeCuatro >= parametrosJuego.getUmbralJugarValeCuatro()) {

            System.out.println("");
            System.out.println("TENGO INTENCION DE CANTAR VALE 4");
            System.out.println("");
            
            respuestaCanto.setRespuestaAceptacion(RespuestaCanto.RESPUESTA_CANTO_QUERIDO);
            if (cantoRealizado.equals(CantoTruco.getInstancia())) {
                respuestaCanto.setCantoContraoferta(CantoRetruco.getInstancia());
            }
            else if (cantoRealizado.equals(CantoRetruco.getInstancia())) {
                respuestaCanto.setCantoContraoferta(CantoVale4.getInstancia());
            }
        } else if (evaluacionRetruco >= parametrosJuego.getUmbralJugarRetruco()) {

            System.out.println("");
            System.out.println("TENGO INTENCION DE CANTAR RETRUCO");
            System.out.println("");
            
            if (cantoRealizado.equals(CantoTruco.getInstancia())) {
                respuestaCanto.setRespuestaAceptacion(RespuestaCanto.RESPUESTA_CANTO_QUERIDO);
                respuestaCanto.setCantoContraoferta(CantoRetruco.getInstancia());
                
            } else if (cantoRealizado.equals(CantoRetruco.getInstancia())) {
                respuestaCanto.setRespuestaAceptacion(RespuestaCanto.RESPUESTA_CANTO_QUERIDO);
            }
        } else if (evaluacionTruco >= parametrosJuego.getUmbralJugarTruco()) {

            System.out.println("");
            System.out.println("TENGO INTENCION DE CANTAR TRUCO");
            System.out.println("");
            
            if (cantoRealizado.equals(CantoTruco.getInstancia())) {
                respuestaCanto.setRespuestaAceptacion(RespuestaCanto.RESPUESTA_CANTO_QUERIDO);
            }
        }

        return respuestaCanto;
    }

    @Override
    public void repartirMano(Carta carta1, Carta carta2, Carta carta3) {

        super.repartirMano(carta1, carta2, carta3);

        rondaActual = 1;
    }
}
