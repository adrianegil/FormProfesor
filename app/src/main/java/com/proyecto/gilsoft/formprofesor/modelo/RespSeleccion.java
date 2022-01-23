package com.proyecto.gilsoft.formprofesor.modelo;

import java.io.Serializable;

/**
 * Created by Adrian Gil on 25/6/2019.
 */

public class RespSeleccion {

    private int codPreg;
    private int codResp;
    private String descrpResp;
    private int opcnCorrecta;

    public RespSeleccion(int codPreg, int codResp, String descrpResp, int opcnCorrecta) {

        this.codPreg = codPreg;
        this.codResp = codResp;
        this.descrpResp = descrpResp;
        this.opcnCorrecta = opcnCorrecta;

    }

    public RespSeleccion() {
    }

    public int getCodPreg() {
        return codPreg;
    }

    public void setCodPreg(int codPreg) {
        this.codPreg = codPreg;
    }

    public int getCodResp() {
        return codResp;
    }

    public void setCodResp(int codResp) {
        this.codResp = codResp;
    }

    public String getDescrpResp() {
        return descrpResp;
    }

    public void setDescrpResp(String descrpResp) {
        this.descrpResp = descrpResp;
    }

    public int getOpcnCorrecta() {
        return opcnCorrecta;
    }

    public void setOpcnCorrecta(int opcnCorrecta) {
        this.opcnCorrecta = opcnCorrecta;
    }
}
