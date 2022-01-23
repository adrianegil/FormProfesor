package com.proyecto.gilsoft.formprofesor.app;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.modelo.PregSeleccion;
import com.proyecto.gilsoft.formprofesor.modelo.Pregunta;
import com.proyecto.gilsoft.formprofesor.modelo.RespSeleccion;
import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;
import java.util.ArrayList;

public class VisualizarPregSeleccActivity extends AppCompatActivity {

    TextView enumPreg, opc1, opc2, opc3;
    CheckBox chOpc1, chOpc2, chOpc3;
    Button btnEvaluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_preg_selecc);

        Bundle objetoPreg = getIntent().getExtras();
        PregSeleccion pregunta = (PregSeleccion) objetoPreg.getSerializable("pregunta");

        final ArrayList<RespSeleccion> list = LocalizacionServicios.getServicios().respSeleccionServicios.listRespSeleccion(pregunta.getCodPreg(), VisualizarPregSeleccActivity.this);

        enumPreg = (TextView) findViewById(R.id.TextViewEnumPregSelecc);
        opc1 = (TextView) findViewById(R.id.TextViewOpcSelecc1);
        opc2 = (TextView) findViewById(R.id.TextViewOpcSelecc2);
        opc3 = (TextView) findViewById(R.id.TextViewOpcSelecc3);

        chOpc1 = (CheckBox) findViewById(R.id.checkBoxRespOpc1);
        chOpc2 = (CheckBox) findViewById(R.id.checkBoxRespOpc2);
        chOpc3 = (CheckBox) findViewById(R.id.checkBoxRespOpc3);

        btnEvaluar = (Button) findViewById(R.id.btnEvaluarPregSelecc);

        enumPreg.setText(pregunta.getEnumPreg());
        opc1.setText(list.get(0).getDescrpResp());
        opc2.setText(list.get(1).getDescrpResp());
        opc3.setText(list.get(2).getDescrpResp());

        btnEvaluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean correcto = true;

                if (list.get(0).getOpcnCorrecta() == 1) {

                    if (!chOpc1.isChecked()) {

                        correcto = false;
                        opc1.setTextColor(Color.GREEN);

                    } else {

                        opc1.setTextColor(Color.GREEN);

                    }

                }

                if (list.get(1).getOpcnCorrecta() == 1) {

                    if (!chOpc2.isChecked()) {

                        correcto = false;
                        opc2.setTextColor(Color.GREEN);

                    } else {

                        opc2.setTextColor(Color.GREEN);

                    }

                }

                if (list.get(2).getOpcnCorrecta() == 1) {

                    if (!chOpc3.isChecked()) {

                        correcto = false;
                        opc3.setTextColor(Color.GREEN);

                    } else {

                        opc3.setTextColor(Color.GREEN);

                    }

                }

                if (list.get(0).getOpcnCorrecta() == 0) {

                    if (chOpc1.isChecked()) {

                        correcto = false;
                        opc1.setTextColor(Color.RED);

                    }

                }

                if (list.get(1).getOpcnCorrecta() == 0) {

                    if (chOpc2.isChecked()) {

                        correcto = false;
                        opc2.setTextColor(Color.RED);

                    }

                }

                if (list.get(2).getOpcnCorrecta() == 0) {

                    if (chOpc3.isChecked()) {

                        correcto = false;
                        opc3.setTextColor(Color.RED);

                    }

                }


                if (correcto) {

                    Toast.makeText(VisualizarPregSeleccActivity.this, R.string.contesCorrectPreg, Toast.LENGTH_SHORT).show();
                    btnEvaluar.setTextColor(Color.GREEN);

                } else {

                    Toast.makeText(VisualizarPregSeleccActivity.this, R.string.contestIncorrectPreg, Toast.LENGTH_SHORT).show();
                    btnEvaluar.setTextColor(Color.RED);

                }

                btnEvaluar.setEnabled(false);
                chOpc1.setEnabled(false);
                chOpc2.setEnabled(false);
                chOpc3.setEnabled(false);

            }
        });

    }

}
