package com.proyecto.gilsoft.formprofesor.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.modelo.Formulario;
import com.proyecto.gilsoft.formprofesor.modelo.PregSeleccion;
import com.proyecto.gilsoft.formprofesor.modelo.RespSeleccion;
import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;

public class PregSeleccionActivity extends AppCompatActivity {

    EditText textEnum;
    EditText opc1;
    EditText opc2;
    EditText opc3;
    CheckBox cbOpc1, cbOpc2, cbOpc3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preg_seleccion);

        Bundle objetoForm = getIntent().getExtras();
        Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");


    }

    @Override
    public void onBackPressed() {

        Bundle objetoForm = getIntent().getExtras();
        Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");

        Bundle bundle = new Bundle();
        bundle.putSerializable("formulario", formulario);

        Intent intent = new Intent(PregSeleccionActivity.this, FormularioActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }


    public void insertaPregSelecc(View view) {

        Bundle bundleRes = getIntent().getExtras();
        Formulario formulario = (Formulario) bundleRes.getSerializable("formulario");
        int orden = bundleRes.getInt("ordenPreg");

        textEnum = (EditText) findViewById(R.id.editTextEnumPregSelecc);
        opc1 = (EditText) findViewById(R.id.editTextOpcSelecc1);
        opc2 = (EditText) findViewById(R.id.editTextOpcSelecc2);
        opc3 = (EditText) findViewById(R.id.editTextOpcSelecc3);

        String enunciado = textEnum.getText().toString();
        String resp1 = opc1.getText().toString();
        String resp2 = opc2.getText().toString();
        String resp3 = opc3.getText().toString();


        cbOpc1 = (CheckBox) findViewById(R.id.checkBoxOpc1);
        cbOpc2 = (CheckBox) findViewById(R.id.checkBoxOpc2);
        cbOpc3 = (CheckBox) findViewById(R.id.checkBoxOpc3);


        if (enunciado.isEmpty() || resp1.isEmpty() || resp2.isEmpty() || resp3.isEmpty()) {

            Toast.makeText(PregSeleccionActivity.this, "Todos los compos deben estar llenos", Toast.LENGTH_SHORT).show();

        } else {

            PregSeleccion pregSeleccion = new PregSeleccion();
            pregSeleccion.setCodForm(formulario.getCodForm());
            pregSeleccion.setEnumPreg(enunciado);
            pregSeleccion.setOrden(orden);

            LocalizacionServicios.getServicios().pregSeleccionServicios.insertarPregSelecc(pregSeleccion, PregSeleccionActivity.this);

            pregSeleccion = LocalizacionServicios.getServicios().pregSeleccionServicios.buscarPregSeleccion(enunciado, PregSeleccionActivity.this);

            RespSeleccion respSeleccion1 = new RespSeleccion();
            respSeleccion1.setCodPreg(pregSeleccion.getCodPreg());
            respSeleccion1.setDescrpResp(resp1);
            if (cbOpc1.isChecked())
                respSeleccion1.setOpcnCorrecta(1);
            else
                respSeleccion1.setOpcnCorrecta(0);
            LocalizacionServicios.getServicios().respSeleccionServicios.insertarRespSeleccion(respSeleccion1, PregSeleccionActivity.this);


            RespSeleccion respSeleccion2 = new RespSeleccion();
            respSeleccion2.setCodPreg(pregSeleccion.getCodPreg());
            respSeleccion2.setDescrpResp(resp2);
            if (cbOpc2.isChecked())
                respSeleccion2.setOpcnCorrecta(1);
            else
                respSeleccion2.setOpcnCorrecta(0);
            LocalizacionServicios.getServicios().respSeleccionServicios.insertarRespSeleccion(respSeleccion2, PregSeleccionActivity.this);


            RespSeleccion respSeleccion3 = new RespSeleccion();
            respSeleccion3.setCodPreg(pregSeleccion.getCodPreg());
            respSeleccion3.setDescrpResp(resp3);
            if (cbOpc3.isChecked())
                respSeleccion3.setOpcnCorrecta(1);
            else
                respSeleccion3.setOpcnCorrecta(0);
            LocalizacionServicios.getServicios().respSeleccionServicios.insertarRespSeleccion(respSeleccion3, PregSeleccionActivity.this);

            {
                Bundle bundle = new Bundle();
                bundle.putSerializable("formulario", formulario);
                Intent intent = new Intent(PregSeleccionActivity.this, FormularioActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        }


    }


}
