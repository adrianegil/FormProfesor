package com.proyecto.gilsoft.formprofesor.app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.modelo.OpcnPregVerdFalso;
import com.proyecto.gilsoft.formprofesor.modelo.PregVerdFalso;
import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;

import java.util.ArrayList;

public class VisualizarPregVerdFalsoActivity extends AppCompatActivity {

    TextView enumPreg, opcPreg1, opcPreg2, opcPreg3;
    Spinner sp1, sp2, sp3;
    Button btnEvaluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_preg_verd_falso);

        Bundle objetoPreg = getIntent().getExtras();
        PregVerdFalso pregunta = (PregVerdFalso) objetoPreg.getSerializable("pregunta");

        btnEvaluar = (Button) findViewById(R.id.btnEvaluarPregVerdFalso);

        enumPreg = (TextView) findViewById(R.id.TextViewEnumPregVerdFalso);
        opcPreg1 = (TextView) findViewById(R.id.TextViewOpcVerdFalso1);
        opcPreg2 = (TextView) findViewById(R.id.TextViewOpcVerdFalso2);
        opcPreg3 = (TextView) findViewById(R.id.TextViewOpcVerdFalso3);

        sp1 = (Spinner) findViewById(R.id.spinnerResp1);
        sp2 = (Spinner) findViewById(R.id.spinnerResp2);
        sp3 = (Spinner) findViewById(R.id.spinnerResp3);

        String[] opciones = {getString(R.string.spinnTrue), "F"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);

        sp1.setAdapter(arrayAdapter);
        sp2.setAdapter(arrayAdapter);
        sp3.setAdapter(arrayAdapter);

        enumPreg.setText(pregunta.getEnumPreg());

        final ArrayList<OpcnPregVerdFalso> list = LocalizacionServicios.getServicios().opcPregVerdFalsoServicios.listOpcPregVerdFalso(pregunta.getCodPreg(), this);

        opcPreg1.setText(list.get(0).getEnumOpcnPreg());
        opcPreg2.setText(list.get(1).getEnumOpcnPreg());
        opcPreg3.setText(list.get(2).getEnumOpcnPreg());

        btnEvaluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean correcto = true;

                if (list.get(0).getOpcnVerdadera() == 1) {

                    if (!sp1.getSelectedItem().equals("V")) {

                        correcto = false;
                        opcPreg1.setTextColor(Color.RED);

                    } else {

                        opcPreg1.setTextColor(Color.GREEN);

                    }

                }

                if (list.get(1).getOpcnVerdadera() == 1) {

                    if (!sp2.getSelectedItem().equals("V")) {

                        correcto = false;
                        opcPreg2.setTextColor(Color.RED);

                    } else {

                        opcPreg2.setTextColor(Color.GREEN);

                    }

                }

                if (list.get(2).getOpcnVerdadera() == 1) {

                    if (!sp3.getSelectedItem().equals("V")) {

                        correcto = false;
                        opcPreg3.setTextColor(Color.RED);

                    } else {

                        opcPreg3.setTextColor(Color.GREEN);

                    }

                }

                if (list.get(0).getOpcnVerdadera() == 0) {

                    if (sp1.getSelectedItem().equals("V")) {

                        correcto = false;
                        opcPreg1.setTextColor(Color.RED);

                    } else {

                        opcPreg1.setTextColor(Color.GREEN);

                    }

                }

                if (list.get(1).getOpcnVerdadera() == 0) {

                    if (sp2.getSelectedItem().equals("V")) {

                        correcto = false;
                        opcPreg2.setTextColor(Color.RED);

                    } else {

                        opcPreg2.setTextColor(Color.GREEN);

                    }

                }
                if (list.get(2).getOpcnVerdadera() == 0) {

                    if (sp3.getSelectedItem().equals("V")) {

                        correcto = false;
                        opcPreg3.setTextColor(Color.RED);


                    } else {

                        opcPreg3.setTextColor(Color.GREEN);

                    }

                }


                if (correcto) {

                    Toast.makeText(VisualizarPregVerdFalsoActivity.this, R.string.contesCorrectPreg, Toast.LENGTH_SHORT).show();
                    btnEvaluar.setTextColor(Color.GREEN);

                } else {

                    Toast.makeText(VisualizarPregVerdFalsoActivity.this, R.string.contestIncorrectPreg, Toast.LENGTH_SHORT).show();
                    btnEvaluar.setTextColor(Color.RED);

                }

                btnEvaluar.setEnabled(false);
                sp1.setEnabled(false);
                sp2.setEnabled(false);
                sp3.setEnabled(false);

            }
        });


    }


}
