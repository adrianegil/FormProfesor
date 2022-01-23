package com.proyecto.gilsoft.formprofesor.modelo;

import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Adrian Gil on 21/6/2019.
 */

public class Formulario implements Serializable {

    private int codForm;
    private String nombForm;
    private ArrayList<Pregunta> listPreg;

    public Formulario() {

        this.listPreg = new ArrayList<Pregunta>();

    }

    public Formulario(int codForm, String nombForm) {

        this.codForm = codForm;
        this.nombForm = nombForm;
        this.listPreg = new ArrayList<Pregunta>();

    }

    public Formulario(int codForm, String nombForm, ArrayList<Pregunta> listPreg) {

        this.codForm = codForm;
        this.nombForm = nombForm;
        this.listPreg = listPreg;

    }

    public int getCodForm() {
        return codForm;
    }

    public void setCodForm(int codForm) {
        this.codForm = codForm;
    }

    public String getNombForm() {
        return nombForm;
    }

    public void setNombForm(String nombForm) {
        this.nombForm = nombForm;
    }

    public ArrayList<Pregunta> getListPreg() {
        return listPreg;
    }

    public void setListPreg(ArrayList<Pregunta> listPreg) {
        this.listPreg = listPreg;
    }
}
