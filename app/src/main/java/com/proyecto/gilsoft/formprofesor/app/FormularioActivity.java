package com.proyecto.gilsoft.formprofesor.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorPreg;
import com.proyecto.gilsoft.formprofesor.modelo.Formulario;
import com.proyecto.gilsoft.formprofesor.modelo.PregSeleccion;
import com.proyecto.gilsoft.formprofesor.modelo.Pregunta;
import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;
import java.util.ArrayList;


public class FormularioActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private ListView listView;
    private AdaptadorPreg adaptadorPreg;
    private ArrayList<Pregunta> listPreg;
    AdaptadorBD adaptadorBD = new AdaptadorBD(this);
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        /*

        this.btn = (Button) findViewById(R.id.btnAñadirPreg);

        Animation desp = AnimationUtils.loadAnimation(this,R.anim.desaparecer);
        desp.setFillAfter(true);
        desp.setRepeatMode(Animation.REVERSE);
        desp.setRepeatCount(Animation.INFINITE);

        this.btn.startAnimation(desp);

         */

        Bundle objetoForm = getIntent().getExtras();
        Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");

        listPreg = LocalizacionServicios.getServicios().preguntaServicios.listPreguntas(formulario.getCodForm(), FormularioActivity.this);

        toolbar = (Toolbar) findViewById(R.id.ToolbNuevForm);
        setSupportActionBar(toolbar);
        toolbar.setTitle(formulario.getNombForm());
        //toolbar.setSubtitle("Cantd. de Preguntas: " + listPreg.size());

        listView = (ListView) findViewById(R.id.listPreg);
        adaptadorPreg = new AdaptadorPreg(this, listPreg);
        listView.setAdapter(adaptadorPreg);


    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(FormularioActivity.this, MainActivity.class));
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuEliminar: {

                Bundle objetoForm = getIntent().getExtras();
                final Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");

                AlertDialog.Builder dialog = new AlertDialog.Builder(FormularioActivity.this);
                dialog.setTitle(R.string.ConfirmEliminar);
                dialog.setMessage(getString(R.string.DesRealElimForm) + formulario.getNombForm());
                dialog.setPositiveButton(R.string.btnSi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LocalizacionServicios.getServicios().formularioServicios.eliminarFormulario(formulario, FormularioActivity.this);

                        Toast.makeText(FormularioActivity.this,getString(R.string.FormElim)+ formulario.getNombForm(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(FormularioActivity.this, MainActivity.class));
                        finish();


                    }
                });
                dialog.setNegativeButton(R.string.btnNo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
                dialog.setNeutralButton(R.string.btnCancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
            break;

         /*   case R.id.menuGuardar: {

                Toast.makeText(FormularioActivity.this, "Se ha tocado el botón Guardar", Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.menuVisualizar: {

                Toast.makeText(FormularioActivity.this, "Se ha tocado el botón Visual", Toast.LENGTH_SHORT).show();


            }
            break;*/
            case R.id.menuCambiarNombre: {

                cambiarNombreForm();

            }
            break;

        }

        return super.onOptionsItemSelected(item);
    }



    public void añadirPregunta(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view1 = getLayoutInflater().inflate(R.layout.opciones, null);

        builder.setView(view1);
        builder.setTitle(R.string.SeleccTipoPreg);

        Button btnSeleccion = (Button) view1.findViewById(R.id.btnSleccion);
        Button btnNumerica = (Button) view1.findViewById(R.id.btnNumerica);
        Button btnTrueFalse = (Button) view1.findViewById(R.id.btnVerdFals);

        btnSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FormularioActivity.this, R.string.TipoSelecc, Toast.LENGTH_SHORT).show();

                Bundle objetoForm = getIntent().getExtras();
                Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");

                listPreg = LocalizacionServicios.getServicios().preguntaServicios.listPreguntas(formulario.getCodForm(), FormularioActivity.this);

                int orden = 0;
                for (int i = 0; i < listPreg.size(); i++) {

                    if (listPreg.get(i).getOrden() > orden)
                        orden = listPreg.get(i).getOrden();

                }
                orden++;

                Intent intent = new Intent(FormularioActivity.this, PregSeleccionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ordenPreg", orden);
                bundle.putSerializable("formulario", formulario);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });
        btnNumerica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FormularioActivity.this, R.string.TipoNumeric, Toast.LENGTH_SHORT).show();

                Bundle objetoForm = getIntent().getExtras();
                Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");

                listPreg = LocalizacionServicios.getServicios().preguntaServicios.listPreguntas(formulario.getCodForm(), FormularioActivity.this);

                int orden = 0;

                for (int i = 0; i < listPreg.size(); i++) {

                    if (listPreg.get(i).getOrden() > orden)
                        orden = listPreg.get(i).getOrden();

                }

                orden++;

                Intent intent = new Intent(FormularioActivity.this, PregNumActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ordenPreg", orden);
                bundle.putSerializable("formulario", formulario);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });
        btnTrueFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FormularioActivity.this, R.string.TipoVerdFals, Toast.LENGTH_SHORT).show();
                Bundle objetoForm = getIntent().getExtras();
                Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");

                listPreg = LocalizacionServicios.getServicios().preguntaServicios.listPreguntas(formulario.getCodForm(), FormularioActivity.this);

                int orden = 0;

                for (int i = 0; i < listPreg.size(); i++) {

                    if (listPreg.get(i).getOrden() > orden)
                        orden = listPreg.get(i).getOrden();

                }

                orden++;

                Intent intent = new Intent(FormularioActivity.this, PregVerdFalsoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("formulario", formulario);
                bundle.putInt("ordenPreg", orden);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        builder.show();

    }

    public void cambiarNombreForm() {

        final EditText text = new EditText(this);
        text.setText(toolbar.getTitle());

        final AlertDialog dialog = new AlertDialog.Builder(FormularioActivity.this)
                .setTitle(R.string.CbrNombForm)
                .setMessage(R.string.EscrNonbNuevForm)
                .setPositiveButton(R.string.btnAceptar, null)
                .setNegativeButton(R.string.btnCancelar, null)
                .setView(text)
                .show();

        Button buttonPos = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        buttonPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = text.getText().toString().trim();

                if (nombre.isEmpty()) {

                    text.setError("Error");
                    Toast.makeText(FormularioActivity.this, R.string.ValidCmpoNoVacio, Toast.LENGTH_SHORT).show();

                } else {

                    Bundle objetoForm = getIntent().getExtras();
                    Formulario formulario = (Formulario) objetoForm.getSerializable("formulario");

                    LocalizacionServicios.getServicios().formularioServicios.modificarFormulario(formulario, nombre, FormularioActivity.this);

                    Formulario formulariob = LocalizacionServicios.getServicios().formularioServicios.buscarFormulario(nombre, FormularioActivity.this);

                    Intent intent = new Intent(FormularioActivity.this, FormularioActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("formulario", formulariob);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    finish();

                }
            }
        });

        Button buttonNeg = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

    }

}


