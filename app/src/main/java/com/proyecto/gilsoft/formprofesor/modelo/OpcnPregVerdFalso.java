package com.proyecto.gilsoft.formprofesor.modelo;

/**
 * Created by Adrian Gil on 25/6/2019.
 */

public class OpcnPregVerdFalso {

    private int codPreg;
    private int codOpcnPreg;
    private String enumOpcnPreg;
    private int opcnVerdadera;

    public OpcnPregVerdFalso(int codPreg, int codOpcnPreg, String enumOpcnPreg, int opcnVerdadera) {

        this.codPreg = codPreg;
        this.codOpcnPreg = codOpcnPreg;
        this.enumOpcnPreg = enumOpcnPreg;
        this.opcnVerdadera = opcnVerdadera;

    }

    public OpcnPregVerdFalso() {
    }

    public int getCodPreg() {
        return codPreg;
    }

    public void setCodPreg(int codPreg) {
        this.codPreg = codPreg;
    }

    public int getCodOpcnPreg() {
        return codOpcnPreg;
    }

    public void setCodOpcnPreg(int codOpcnPreg) {
        this.codOpcnPreg = codOpcnPreg;
    }

    public String getEnumOpcnPreg() {
        return enumOpcnPreg;
    }

    public void setEnumOpcnPreg(String enumOpcnPreg) {
        this.enumOpcnPreg = enumOpcnPreg;
    }

    public int getOpcnVerdadera() {
        return opcnVerdadera;
    }

    public void setOpcnVerdadera(int opcnVerdadera) {
        this.opcnVerdadera = opcnVerdadera;
    }
}
