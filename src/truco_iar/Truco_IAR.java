package truco_iar;

import pantallas.Inicial;

/**
 *
 * @author Nicolás Bosi
 */
public class Truco_IAR {

    public static void main(String[] args) {

        GestorTrucoL gestorTrucoL = new GestorTrucoL();
        //gestorTruco.iniciarPartida();
        Inicial pantalla1 = new Inicial(gestorTrucoL);
        pantalla1.setVisible(true);
    }
    
}
