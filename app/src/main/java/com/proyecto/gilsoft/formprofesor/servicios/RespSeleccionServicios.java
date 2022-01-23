package com.proyecto.gilsoft.formprofesor.servicios;

import android.content.Context;
import android.database.Cursor;

import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.modelo.RespSeleccion;

import java.util.ArrayList;

/**
 * Created by Adrian Gil on 29/6/2019.
 */

public class RespSeleccionServicios {

    public ArrayList<RespSeleccion> listRespSeleccion(int codPreg, Context context) {

        ArrayList<RespSeleccion> list = new ArrayList<>();

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM respSelecc WHERE codpreg = " + codPreg, null);

        while (cursor.moveToNext()) {

            RespSeleccion respSeleccion = new RespSeleccion();
            respSeleccion.setCodPreg(cursor.getInt(0));
            respSeleccion.setCodResp(cursor.getInt(1));
            respSeleccion.setDescrpResp(cursor.getString(2));
            respSeleccion.setOpcnCorrecta(cursor.getInt(3));

            list.add(respSeleccion);

        }

        adaptadorBD.cerrarBD();

        return list;

    }

    public void insertarRespSeleccion(RespSeleccion respSeleccion, Context context) {

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        adaptadorBD.bd.execSQL("INSERT INTO respSelecc(codpreg,descrpResp,opcnCorrecta) VALUES(" + respSeleccion.getCodPreg()
                + ",'" + respSeleccion.getDescrpResp() + "'," + respSeleccion.getOpcnCorrecta() + ")");

        adaptadorBD.cerrarBD();

    }

}
