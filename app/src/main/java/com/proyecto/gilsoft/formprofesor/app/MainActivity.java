package com.proyecto.gilsoft.formprofesor.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorBD;
import com.proyecto.gilsoft.formprofesor.adaptadores.AdaptadorForm;
import com.proyecto.gilsoft.formprofesor.modelo.Formulario;
import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int cont = 0;
    public AdaptadorForm adaptador;
    private RecyclerView recyclerView;
    private ArrayList<Formulario> listForm;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        this.btn = (Button) findViewById(R.id.btnAñadirForm);

        Animation desp = AnimationUtils.loadAnimation(this,R.anim.desaparecer);
        desp.setFillAfter(true);
        desp.setRepeatMode(Animation.REVERSE);
        desp.setRepeatCount(Animation.INFINITE);

        this.btn.startAnimation(desp);

        */

        listForm = LocalizacionServicios.getServicios().formularioServicios.listFormulario(MainActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolb1);
        // toolbar.setSubtitle("Cantd. de Formularios: "+ listForm.size());
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.ReciclerViewForm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adaptador = new AdaptadorForm(listForm, this);
        recyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Formulario formulario = listForm.get(recyclerView.getChildAdapterPosition(v));

                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("formulario", formulario);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {

        if (cont == 0) {

            Toast.makeText(MainActivity.this, R.string.ToastAtras, Toast.LENGTH_SHORT).show();
            cont++;

        } else {

            super.onBackPressed();
        }


        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                cont = 0;

            }
        }.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_inicial, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.btnInfo: {

                startActivity(new Intent(MainActivity.this, InformacionActivity.class));
                Toast.makeText(this, R.string.ToastInfo, Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.btnNuevoForm: {

                nuevoFormulario(new View(this));

            }
            break;

            case R.id.btnAyuda: {

                Toast.makeText(this, R.string.ToastAyuda, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, AyudaActivity.class));


            }
            break;

            case R.id.btnSalir: {

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle(R.string.DialogTitlleCerrar);
                dialog.setMessage(R.string.DialogMessgeCerrar);
                dialog.setPositiveButton(R.string.btnSi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                        System.exit(0);

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

        }

        return super.onOptionsItemSelected(item);
    }


    public void nuevoFormulario(View view) {

        final EditText text = new EditText(this);

        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.menuNuevoForm)
                .setMessage(R.string.DialogNuevForm)
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
                    Toast.makeText(MainActivity.this, R.string.ValidCmpoNoVacio, Toast.LENGTH_SHORT).show();
                } else {

                    LocalizacionServicios.getServicios().formularioServicios.insertarFormulario(nombre, MainActivity.this);
                    Formulario formulariob = LocalizacionServicios.getServicios().formularioServicios.buscarFormulario(nombre, MainActivity.this);
                    dialog.dismiss();

                    Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("formulario", formulariob);
                    intent.putExtras(bundle);
                    Toast.makeText(MainActivity.this, getString(R.string.ToastCreado) + " " + nombre, Toast.LENGTH_SHORT).show();
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
