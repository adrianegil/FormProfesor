package com.proyecto.gilsoft.formprofesor.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.modelo.Formulario;
import com.proyecto.gilsoft.formprofesor.modelo.PregNumerica;
import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;

public class PregNumActivity extends AppCompatActivity {

    EditText textEnum;
    EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preg_num);

    }

    @Override
    public void onBackPressed() {

        Bundle objetoForm = getIntent().getExtras();
        Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");

        Bundle bundle = new Bundle();
        bundle.putSerializable("formulario", formulario);
        Intent intent = new Intent(PregNumActivity.this, FormularioActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }



    public void inserPregNum(View view) {

        textEnum = (EditText) findViewById(R.id.editTextEnumPregNum);
        result = (EditText) findViewById(R.id.editTextResultNum);

        String enumciado = textEnum.getText().toString();
        String res = result.getText().toString();

        Bundle bundleRes = getIntent().getExtras();
        Formulario formulario = (Formulario) bundleRes.getSerializable("formulario");
        int orden = bundleRes.getInt("ordenPreg");

        if(enumciado.isEmpty()||res.isEmpty())
        {
            Toast.makeText(PregNumActivity.this, R.string.ValidTodCampVacios,Toast.LENGTH_SHORT).show();

        }
        else {

            Double resultado = Double.parseDouble(res);

            PregNumerica pregNumerica = new PregNumerica();
            pregNumerica.setCodForm(formulario.getCodForm());
            pregNumerica.setEnumPreg(enumciado);
            pregNumerica.setOrden(orden);
            pregNumerica.setResult(resultado);

            LocalizacionServicios.getServicios().pregNumericaServicios.insertarPregNumerica(pregNumerica, PregNumActivity.this);

            Bundle bundle = new Bundle();
            bundle.putSerializable("formulario", formulario);
            Intent intent = new Intent(PregNumActivity.this, FormularioActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

    }

}
