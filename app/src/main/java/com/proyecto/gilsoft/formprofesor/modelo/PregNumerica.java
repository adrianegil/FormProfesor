package com.proyecto.gilsoft.formprofesor.modelo;

/**
 * Created by Adrian Gil on 21/6/2019.
 */

public class PregNumerica extends Pregunta {

    private Double result;


    public PregNumerica(int codForm, int codPreg, String descrpPreg, int orden, Double result) {

        super(codForm, codPreg, descrpPreg, orden);
        this.result = result;

    }

    public PregNumerica() {


    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
