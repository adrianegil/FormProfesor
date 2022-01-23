package com.proyecto.gilsoft.formprofesor.adaptadores;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by Adrian Gil on 21/6/2019.
 */

public class AdaptadorBD {

    private static final String NOMBRE_BASE_DATOS = "formulariosBD";
    private static final int VERSION_BASE_DATOS = 1;

    private final Context context;
    private DatabaseHelper DBHelper;
    public SQLiteDatabase bd;

    public AdaptadorBD(Context context){

        this.context = context;
        DBHelper = new DatabaseHelper(context);

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private String crearTablaForm = "CREATE TABLE formulario(codform INTEGER PRIMARY KEY AUTOINCREMENT, nombform TEXT NOT NULL)";

        private String crearTablaPregNumerica = "CREATE TABLE pregNumerica(codform INTEGER, codpreg INTEGER PRIMARY KEY AUTOINCREMENT, enumPreg TEXT," +
                "orden INTEGER, respuesta DOUBLE PRESICION, FOREIGN KEY(codform) REFERENCES formulario(codform) ON DELETE CASCADE)";

        private String crearTablaPregSeleccion = "CREATE TABLE pregSeleccion(codform INTEGER, codpreg INTEGER PRIMARY KEY AUTOINCREMENT, enumPreg TEXT," +
                "orden INTEGER, FOREIGN KEY(codform) REFERENCES formulario(codform) ON DELETE CASCADE)";

        private String crearTablaPregVerdFalso = "CREATE TABLE pregVerdFalso(codform INTEGER, codpreg INTEGER PRIMARY KEY AUTOINCREMENT, enumPreg TEXT," +
                "orden INTEGER, FOREIGN KEY(codform) REFERENCES formulario(codform) ON DELETE CASCADE)";

        private String crearTablaRespSelecc = "CREATE TABLE respSelecc(codpreg INTEGER, codresp INTEGER PRIMARY KEY AUTOINCREMENT, descrpResp TEXT," +
                "opcnCorrecta INTEGER, FOREIGN KEY(codpreg) REFERENCES pregSeleccion(codpreg) ON DELETE CASCADE)";

        private String crearTablaOpcnPregVerdFalso = "CREATE TABLE opcnPregVerdFalso(codpreg INTEGER, codOpcnPreg INTEGER PRIMARY KEY AUTOINCREMENT, enumOpcnPreg TEXT," +
                "opcnVerdadera INTEGER,FOREIGN KEY(codpreg) REFERENCES pregVerdFalso(codpreg) ON DELETE CASCADE)";



        public DatabaseHelper(Context context) {

            super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);

        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(crearTablaForm);
            db.execSQL(crearTablaPregNumerica);
            db.execSQL(crearTablaPregSeleccion);
            db.execSQL(crearTablaPregVerdFalso);
            db.execSQL(crearTablaRespSelecc);
            db.execSQL(crearTablaOpcnPregVerdFalso);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS formulario");
            db.execSQL("DROP TABLE IF EXISTS pregNumerica");
            db.execSQL("DROP TABLE IF EXISTS pregSeleccion");
            db.execSQL("DROP TABLE IF EXISTS pregVerdFalso");
            db.execSQL("DROP TABLE IF EXISTS respSelecc");
            db.execSQL("DROP TABLE IF EXISTS opcnPregVerdFalso");

            onCreate(db);

        }
    }

    public void abrirBD() throws SQLException {

        bd = DBHelper.getWritableDatabase();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            bd.setForeignKeyConstraintsEnabled(true);

        } else {

            bd.execSQL("PRAGMA foreign_keys = ON");

        }

        return;
    }

    public void cerrarBD(){

        DBHelper.close();
        bd.close();

    }
}
