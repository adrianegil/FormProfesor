package com.proyecto.gilsoft.formprofesor.servicios;

import android.content.Context;
import android.database.Cursor;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.modelo.OpcnPregVerdFalso;
import java.util.ArrayList;

/**
 * Created by Adrian Gil on 1/7/2019.
 */

public class OpcPregVerdFalsoServicios {

    public ArrayList<OpcnPregVerdFalso> listOpcPregVerdFalso(int codPreg, Context context){

        ArrayList<OpcnPregVerdFalso> list = new ArrayList<>();

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        Cursor cursor = adaptadorBD.bd.rawQuery("SELECT * FROM opcnPregVerdFalso WHERE codpreg = "+codPreg, null);

        while (cursor.moveToNext()){

            OpcnPregVerdFalso opcnPregVerdFalso = new OpcnPregVerdFalso();
            opcnPregVerdFalso.setCodPreg(cursor.getInt(0));
            opcnPregVerdFalso.setCodOpcnPreg(cursor.getInt(1));
            opcnPregVerdFalso.setEnumOpcnPreg(cursor.getString(2));
            opcnPregVerdFalso.setOpcnVerdadera(cursor.getInt(3));

            list.add(opcnPregVerdFalso);

        }

        adaptadorBD.cerrarBD();

        return list;

    }

    public void insertarOpcnPregVerdFalso(OpcnPregVerdFalso opcnPregVerdFalso, Context context){

        AdaptadorBD adaptadorBD = new AdaptadorBD(context);

        adaptadorBD.abrirBD();

        adaptadorBD.bd.execSQL("INSERT INTO opcnPregVerdFalso(codpreg,enumOpcnPreg,opcnVerdadera) VALUES("+ opcnPregVerdFalso.getCodPreg()
                +",'"+opcnPregVerdFalso.getEnumOpcnPreg()+"',"+opcnPregVerdFalso.getOpcnVerdadera()+")");

        adaptadorBD.cerrarBD();

    }

}
