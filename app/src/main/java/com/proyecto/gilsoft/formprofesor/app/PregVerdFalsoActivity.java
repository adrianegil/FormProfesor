package com.proyecto.gilsoft.formprofesor.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.modelo.Formulario;
import com.proyecto.gilsoft.formprofesor.modelo.OpcnPregVerdFalso;
import com.proyecto.gilsoft.formprofesor.modelo.PregVerdFalso;
import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;

public class PregVerdFalsoActivity extends AppCompatActivity {

    EditText textEnum, texOpcPreg1, texOpcPreg2, texOpcPreg3;
    Spinner spOpc1, spOpc2, spOpc3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preg_verd_falso);

        spOpc1 = (Spinner) findViewById(R.id.spinnerOcp1);
        spOpc2 = (Spinner) findViewById(R.id.spinnerOcp2);
        spOpc3 = (Spinner) findViewById(R.id.spinnerOcp3);

        String[] opciones = {getString(R.string.spinnVerd), getString(R.string.spinnFals)};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);

        spOpc1.setAdapter(arrayAdapter);
        spOpc2.setAdapter(arrayAdapter);
        spOpc3.setAdapter(arrayAdapter);

    }

    @Override
    public void onBackPressed() {

        Bundle objetoForm = getIntent().getExtras();
        Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");

        Bundle bundle = new Bundle();
        bundle.putSerializable("formulario", formulario);

        Intent intent = new Intent(PregVerdFalsoActivity.this, FormularioActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }


    public void insertaPregVerdFalso(View view) {

        Bundle bundleRes = getIntent().getExtras();
        Formulario formulario = (Formulario) bundleRes.getSerializable("formulario");
        int orden = bundleRes.getInt("ordenPreg");

        textEnum = (EditText) findViewById(R.id.editTextEnumPregVerdFalso);
        texOpcPreg1 = (EditText) findViewById(R.id.opcPreg1);
        texOpcPreg2 = (EditText) findViewById(R.id.opcPreg2);
        texOpcPreg3 = (EditText) findViewById(R.id.opcPreg3);

        spOpc1 = (Spinner) findViewById(R.id.spinnerOcp1);
        spOpc2 = (Spinner) findViewById(R.id.spinnerOcp2);
        spOpc3 = (Spinner) findViewById(R.id.spinnerOcp3);

        String enunciado = textEnum.getText().toString();
        String opc1 = texOpcPreg1.getText().toString();
        String opc2 = texOpcPreg2.getText().toString();
        String opc3 = texOpcPreg3.getText().toString();


        if (enunciado.isEmpty() || opc1.isEmpty() || opc2.isEmpty() || opc3.isEmpty()) {

            Toast.makeText(this, R.string.ValidTodCampVacios, Toast.LENGTH_SHORT).show();

        } else {

            PregVerdFalso pregVerdFalso = new PregVerdFalso();
            pregVerdFalso.setCodForm(formulario.getCodForm());
            pregVerdFalso.setEnumPreg(enunciado);
            pregVerdFalso.setOrden(orden);

            LocalizacionServicios.getServicios().pregVerdFalsoServicios.insertarPregVerdFalso(pregVerdFalso, PregVerdFalsoActivity.this);

            pregVerdFalso = LocalizacionServicios.getServicios().pregVerdFalsoServicios.buscarPregVerdFalso(enunciado, PregVerdFalsoActivity.this);

            OpcnPregVerdFalso opcnPregVerdFalso1 = new OpcnPregVerdFalso();
            opcnPregVerdFalso1.setCodPreg(pregVerdFalso.getCodPreg());
            opcnPregVerdFalso1.setEnumOpcnPreg(opc1);
            if (spOpc1.getSelectedItem().equals("Verdad"))
                opcnPregVerdFalso1.setOpcnVerdadera(1);
            else
                opcnPregVerdFalso1.setOpcnVerdadera(0);
            LocalizacionServicios.getServicios().opcPregVerdFalsoServicios.insertarOpcnPregVerdFalso(opcnPregVerdFalso1, this);


            OpcnPregVerdFalso opcnPregVerdFalso2 = new OpcnPregVerdFalso();
            opcnPregVerdFalso2.setCodPreg(pregVerdFalso.getCodPreg());
            opcnPregVerdFalso2.setEnumOpcnPreg(opc2);
            if (spOpc2.getSelectedItem().equals("Verdad"))
                opcnPregVerdFalso2.setOpcnVerdadera(1);
            else
                opcnPregVerdFalso2.setOpcnVerdadera(0);
            LocalizacionServicios.getServicios().opcPregVerdFalsoServicios.insertarOpcnPregVerdFalso(opcnPregVerdFalso2, this);


            OpcnPregVerdFalso opcnPregVerdFalso3 = new OpcnPregVerdFalso();
            opcnPregVerdFalso3.setCodPreg(pregVerdFalso.getCodPreg());
            opcnPregVerdFalso3.setEnumOpcnPreg(opc3);
            if (spOpc3.getSelectedItem().equals("Verdad"))
                opcnPregVerdFalso3.setOpcnVerdadera(1);
            else
                opcnPregVerdFalso3.setOpcnVerdadera(0);
            LocalizacionServicios.getServicios().opcPregVerdFalsoServicios.insertarOpcnPregVerdFalso(opcnPregVerdFalso3, this);


            Bundle bundle = new Bundle();
            bundle.putSerializable("formulario", formulario);
            Intent intent = new Intent(PregVerdFalsoActivity.this, FormularioActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

        }

    }

}
