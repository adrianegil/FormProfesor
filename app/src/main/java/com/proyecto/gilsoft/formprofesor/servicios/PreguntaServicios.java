package com.proyecto.gilsoft.formprofesor.servicios;

import android.content.Context;

import com.proyecto.gilsoft.formprofesor.modelo.PregNumerica;
import com.proyecto.gilsoft.formprofesor.modelo.PregSeleccion;
import com.proyecto.gilsoft.formprofesor.modelo.PregVerdFalso;
import com.proyecto.gilsoft.formprofesor.modelo.Pregunta;

import java.util.ArrayList;

/**
 * Created by Adrian Gil on 24/6/2019.
 */

public class PreguntaServicios {


    public ArrayList<Pregunta> listPreguntas(int codForm, Context context) {

        ArrayList<Pregunta> list = new ArrayList<>();
        ArrayList<PregNumerica> listPregNum = new ArrayList<>();
        ArrayList<PregSeleccion> listPregSelecc = new ArrayList<>();
        ArrayList<PregVerdFalso> listPregVerdFal = new ArrayList<>();

        listPregNum = LocalizacionServicios.getServicios().pregNumericaServicios.listPregNumericas(codForm, context);
        listPregSelecc = LocalizacionServicios.getServicios().pregSeleccionServicios.listPregSeleccion(codForm, context);
        listPregVerdFal = LocalizacionServicios.getServicios().pregVerdFalsoServicios.listPregVerdFalso(codForm, context);

        for (int i = 0; i < listPregNum.size(); i++) {

            PregNumerica pregNumerica = new PregNumerica();

            pregNumerica = listPregNum.get(i);

            list.add(pregNumerica);

        }

        for (int i = 0; i < listPregSelecc.size(); i++) {

            PregSeleccion pregSeleccion = new PregSeleccion();

            pregSeleccion = listPregSelecc.get(i);

            list.add(pregSeleccion);

        }

        for (int i = 0; i < listPregVerdFal.size(); i++) {

            PregVerdFalso pregVerdFalso = new PregVerdFalso();

            pregVerdFalso = listPregVerdFal.get(i);

            list.add(pregVerdFalso);

        }


        ArrayList<Pregunta> listResult = Burbuja(list);

        return listResult;

    }



    //METODO DE ORDENAMIENTO
    public ArrayList<Pregunta> Burbuja(ArrayList<Pregunta> listDesordenada) {

        ArrayList<Pregunta> list = listDesordenada;

        for (int i = 0; i < list.size(); i++) {

            for (int j = i + 1; j < list.size(); j++) {

                if (list.get(i).getOrden() > list.get(j).getOrden()) {

                    Pregunta auxiliar = list.get(i);

                    list.set(i, list.get(j));

                    list.set(j, auxiliar);

                }
            }
        }

        return list;
    }

}

