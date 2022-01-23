package com.proyecto.gilsoft.formprofesor.app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.modelo.PregNumerica;

public class VisualizarPregNumActivity extends AppCompatActivity {

    TextView enumPreg, respCorrecta;
    EditText result;
    Button btnEvaluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_preg_num);

        Bundle objetoPreg = getIntent().getExtras();
        final PregNumerica pregunta = (PregNumerica) objetoPreg.getSerializable("pregunta");

        enumPreg = (TextView) findViewById(R.id.TextViewEnumPregNum);
        respCorrecta = (TextView) findViewById(R.id.textViewRespCorrecta);
        result = (EditText) findViewById(R.id.editTextResultNumVisual);
        btnEvaluar = (Button) findViewById(R.id.btnEvaluarPregNum);

        enumPreg.setText(pregunta.getEnumPreg());

        btnEvaluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String res = result.getText().toString();

                if (res.isEmpty()) {

                    Toast.makeText(VisualizarPregNumActivity.this, R.string.dbLLenrEstPreg, Toast.LENGTH_SHORT).show();
                    result.setError("Error");

                } else {

                    Double resultado = Double.parseDouble(res);

                    if (resultado.equals(pregunta.getResult())) {

                        Toast.makeText(VisualizarPregNumActivity.this, R.string.contesCorrectPreg, Toast.LENGTH_SHORT).show();

                        btnEvaluar.setTextColor(Color.GREEN);
                        result.setTextColor(Color.GREEN);

                    } else {

                        Toast.makeText(VisualizarPregNumActivity.this, R.string.contestIncorrectPreg, Toast.LENGTH_SHORT).show();

                        respCorrecta.setVisibility(View.VISIBLE);
                        respCorrecta.setText("Respuesta Correcta: " + pregunta.getResult());
                        respCorrecta.setTextColor(Color.GREEN);
                        btnEvaluar.setTextColor(Color.RED);
                        result.setTextColor(Color.RED);

                    }

                    result.setEnabled(false);
                    btnEvaluar.setEnabled(false);

                }

            }
        });

    }

}
