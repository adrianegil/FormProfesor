package com.proyecto.gilsoft.formprofesor.servicios;

import android.content.Context;
import android.database.Cursor;

import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.app.PregSeleccionActivity;
import com.proyecto.gilsoft.formprofesor.modelo.PregSeleccion;
import com.proyecto.gilsoft.formprofesor.modelo.Pregunta;

import java.util.ArrayList;

/**
 * Created by Adrian Gil on 24/6/2019.
 */

public class PregSeleccionServicios {


    public ArrayList<PregSeleccion> listPregSeleccion(int codForm, Context context) {

        ArrayList<PregSeleccion> list = new ArrayList<>();

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM pregSeleccion WHERE codform = " + codForm, null);

        while (cursor.moveToNext()) {

            PregSeleccion pregSeleccion = new PregSeleccion();
            pregSeleccion.setCodForm(cursor.getInt(0));
            pregSeleccion.setCodPreg(cursor.getInt(1));
            pregSeleccion.setEnumPreg(cursor.getString(2));
            pregSeleccion.setOrden(cursor.getInt(3));

            list.add(pregSeleccion);

        }

        adaptadorBD.cerrarBD();

        return list;

    }

    public PregSeleccion buscarPregSeleccion(String enumPreg, Context context) {

        PregSeleccion pregSeleccion = new PregSeleccion();

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM pregSeleccion WHERE enumPreg ='" + enumPreg + "'", null);

        if (cursor.moveToFirst()) {

            pregSeleccion.setCodForm(cursor.getInt(0));
            pregSeleccion.setCodPreg(cursor.getInt(1));
            pregSeleccion.setEnumPreg(cursor.getString(2));
            pregSeleccion.setOrden(cursor.getInt(3));

        }

        adaptadorBD.cerrarBD();

        return pregSeleccion;

    }

    public void eliminarPregSelecc(int codPreg, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        adaptadorBD.bd.execSQL("DELETE FROM pregSeleccion WHERE codpreg =" + codPreg);

        adaptadorBD.cerrarBD();

    }

    public void insertarPregSelecc(PregSeleccion pregunta, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);
        adaptadorBD.abrirBD();
        adaptadorBD.bd.execSQL("INSERT INTO pregSeleccion(codform,enumPreg,orden) VALUES(" + pregunta.getCodForm() + ",'" + pregunta.getEnumPreg() + "'," + pregunta.getOrden() + ")");
        adaptadorBD.cerrarBD();

    }

}
