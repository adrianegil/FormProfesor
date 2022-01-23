package com.proyecto.gilsoft.formprofesor.modelo;


import java.io.Serializable;

/**
 * Created by Adrian Gil on 21/6/2019.
 */

public class Pregunta implements Serializable {


    protected int codForm;
    protected int codPreg;
    protected String enumPreg;
    protected int orden;

    public Pregunta() {

    }

    public Pregunta(int codForm, int codPreg, String enumPreg, int orden) {

        this.codForm = codForm;
        this.codPreg = codPreg;
        this.enumPreg = enumPreg;
        this.orden = orden;

    }

    public int getCodForm() {
        return codForm;
    }

    public void setCodForm(int codForm) {
        this.codForm = codForm;
    }

    public int getCodPreg() {
        return codPreg;
    }

    public void setCodPreg(int codPreg) {
        this.codPreg = codPreg;
    }

    public String getEnumPreg() {
        return enumPreg;
    }

    public void setEnumPreg(String enumPreg) {
        this.enumPreg = enumPreg;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
