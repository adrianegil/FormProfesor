package com.proyecto.gilsoft.formprofesor.servicios;

import android.content.Context;
import android.database.Cursor;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.modelo.Formulario;
import java.util.ArrayList;

/**
 * Created by Adrian Gil on 21/6/2019.
 */

public class FormularioServicios {


    public Formulario buscarFormulario(String nombForm, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM formulario WHERE nombform ='" + nombForm + "'", null);

        Formulario formulario = new Formulario();

        if (cursor.moveToFirst()) {

            formulario.setCodForm(cursor.getInt(0));
            formulario.setNombForm(cursor.getString(1));

        }

        adaptadorBD.cerrarBD();

        return formulario;

    }

    public Formulario buscarFormulario(int codForm, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM formulario WHERE codform =" + codForm, null);

        Formulario formulario = new Formulario();

        if (cursor.moveToFirst()) {

            formulario.setCodForm(cursor.getInt(0));
            formulario.setNombForm(cursor.getString(1));

        }

        adaptadorBD.cerrarBD();

        return formulario;

    }

    public void insertarFormulario(String nombForm, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        adaptadorBD.bd.execSQL("INSERT INTO formulario(nombform) VALUES('" + nombForm + "')");

        adaptadorBD.cerrarBD();

    }

    public void modificarFormulario(Formulario formulario, String nombForm, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        adaptadorBD.bd.execSQL("UPDATE formulario SET nombform ='" + nombForm + "' WHERE codform = " + formulario.getCodForm());

        adaptadorBD.cerrarBD();

    }

    public void eliminarFormulario(Formulario form, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        // adaptadorBD.bd.delete("formulario","codform ="+form.getCodForm(),null);

        adaptadorBD.bd.execSQL("DELETE FROM formulario WHERE codform =" + form.getCodForm());

        adaptadorBD.cerrarBD();

    }

    public ArrayList<Formulario> listFormulario(Context context) {

        ArrayList<Formulario> list = new ArrayList<Formulario>();

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM formulario", null);

        while (cursor.moveToNext()) {

            Formulario formulario = new Formulario();
            formulario.setCodForm(cursor.getInt(0));
            formulario.setNombForm(cursor.getString(1));

            list.add(formulario);

        }

        adaptadorBD.cerrarBD();

        return list;
    }


}
