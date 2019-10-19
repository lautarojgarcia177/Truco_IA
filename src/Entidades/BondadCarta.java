/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

public class BondadCarta {
    Carta carta;
    Double bondad;
    
    public BondadCarta(Carta carta,Double bondad ){
        this.carta = carta;
        this.bondad = bondad;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Double getBondad() {
        return bondad;
    }

    public void setBondad(Double bondad) {
        this.bondad = bondad;
    }
}
