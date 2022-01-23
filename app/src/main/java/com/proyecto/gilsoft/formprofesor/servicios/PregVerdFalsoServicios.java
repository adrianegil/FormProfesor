package com.proyecto.gilsoft.formprofesor.servicios;

import android.content.Context;
import android.database.Cursor;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.app.PregVerdFalsoActivity;
import com.proyecto.gilsoft.formprofesor.modelo.PregVerdFalso;
import java.util.ArrayList;

/**
 * Created by Adrian Gil on 24/6/2019.
 */

public class PregVerdFalsoServicios {

    public ArrayList<PregVerdFalso> listPregVerdFalso(int codForm, Context context) {

        ArrayList<PregVerdFalso> list = new ArrayList<>();

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM pregVerdFalso WHERE codform = " + codForm, null);

        while (cursor.moveToNext()) {

            PregVerdFalso pregVerdFalso = new PregVerdFalso();
            pregVerdFalso.setCodForm(cursor.getInt(0));
            pregVerdFalso.setCodPreg(cursor.getInt(1));
            pregVerdFalso.setEnumPreg(cursor.getString(2));
            pregVerdFalso.setOrden(cursor.getInt(3));

            list.add(pregVerdFalso);

        }

        adaptadorBD.cerrarBD();

        return list;

    }

    public PregVerdFalso buscarPregVerdFalso(String enumPreg, Context context) {

        PregVerdFalso pregVerdFalso = new PregVerdFalso();

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM pregVerdFalso WHERE enumPreg ='" + enumPreg + "'", null);

        if (cursor.moveToFirst()) {

            pregVerdFalso.setCodForm(cursor.getInt(0));
            pregVerdFalso.setCodPreg(cursor.getInt(1));
            pregVerdFalso.setEnumPreg(cursor.getString(2));
            pregVerdFalso.setOrden(cursor.getInt(3));

        }

        adaptadorBD.cerrarBD();

        return pregVerdFalso;

    }

    public void eliminarPregVerdFalso(int codPreg, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        adaptadorBD.bd.execSQL("DELETE FROM pregVerdFalso WHERE codpreg =" + codPreg);

        adaptadorBD.cerrarBD();

    }

    public void insertarPregVerdFalso(PregVerdFalso pregunta, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);
        adaptadorBD.abrirBD();
        adaptadorBD.bd.execSQL("INSERT INTO pregVerdFalso(codform,enumPreg,orden) VALUES(" + pregunta.getCodForm() + ",'" + pregunta.getEnumPreg() + "'," + pregunta.getOrden() + ")");
        adaptadorBD.cerrarBD();

    }
}
