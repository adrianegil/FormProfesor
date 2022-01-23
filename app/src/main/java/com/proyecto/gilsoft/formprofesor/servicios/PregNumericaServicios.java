package com.proyecto.gilsoft.formprofesor.servicios;

import android.content.Context;
import android.database.Cursor;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.app.PregNumActivity;
import com.proyecto.gilsoft.formprofesor.modelo.PregNumerica;
import java.util.ArrayList;

/**
 * Created by Adrian Gil on 24/6/2019.
 */

public class PregNumericaServicios {


    public ArrayList<PregNumerica> listPregNumericas(int codForm, Context context) {

        ArrayList<PregNumerica> list = new ArrayList<>();

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM pregNumerica WHERE codform = " + codForm, null);

        while (cursor.moveToNext()) {

            PregNumerica pregNumerica = new PregNumerica();
            pregNumerica.setCodForm(cursor.getInt(0));
            pregNumerica.setCodPreg(cursor.getInt(1));
            pregNumerica.setEnumPreg(cursor.getString(2));
            pregNumerica.setOrden(cursor.getInt(3));
            pregNumerica.setResult(cursor.getDouble(4));

            list.add(pregNumerica);

        }

        adaptadorBD.cerrarBD();

        return list;

    }

    public void eliminarPregNum(int codPreg, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        adaptadorBD.bd.execSQL("DELETE FROM pregNumerica WHERE codpreg =" + codPreg);

        adaptadorBD.cerrarBD();


    }

    public void insertarPregNumerica(PregNumerica pregunta, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);
        adaptadorBD.abrirBD();
        adaptadorBD.bd.execSQL("INSERT INTO pregNumerica(codform,enumPreg,orden,respuesta) VALUES(" + pregunta.getCodForm() + ",'" + pregunta.getEnumPreg() + "'," + pregunta.getOrden() + "," + pregunta.getResult() + ")");
        adaptadorBD.cerrarBD();

    }

}
