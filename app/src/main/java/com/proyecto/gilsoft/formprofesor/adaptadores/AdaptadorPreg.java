package com.proyecto.gilsoft.formprofesor.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.app.FormularioActivity;
import com.proyecto.gilsoft.formprofesor.app.MainActivity;
import com.proyecto.gilsoft.formprofesor.app.VisualizarPregNumActivity;
import com.proyecto.gilsoft.formprofesor.app.VisualizarPregSeleccActivity;
import com.proyecto.gilsoft.formprofesor.app.VisualizarPregVerdFalsoActivity;
import com.proyecto.gilsoft.formprofesor.modelo.Formulario;
import com.proyecto.gilsoft.formprofesor.modelo.PregNumerica;
import com.proyecto.gilsoft.formprofesor.modelo.PregSeleccion;
import com.proyecto.gilsoft.formprofesor.modelo.PregVerdFalso;
import com.proyecto.gilsoft.formprofesor.modelo.Pregunta;
import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;
import java.util.ArrayList;

/**
 * Created by Adrian Gil on 21/6/2019.
 */

public class AdaptadorPreg extends BaseAdapter {

    private Context context;
    private ArrayList<Pregunta> listPreg;

    public AdaptadorPreg(Context context, ArrayList<Pregunta> listPreg) {

        this.context = context;
        this.listPreg = listPreg;

    }

    @Override
    public int getCount() {

        return listPreg.size();

    }

    @Override
    public Object getItem(int position) {

        return listPreg.get(position);

    }

    @Override
    public long getItemId(int position) {

        return 0;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_itemlist, null);

        TextView tipoPreg = (TextView) view.findViewById(R.id.textViewTipoPreg);
        TextView descrpPreg = (TextView) view.findViewById(R.id.textViewDescrPreg);
        ImageView img = (ImageView) view.findViewById(R.id.imageViewDeletePreg);
        ImageView imgVisual = (ImageView) view.findViewById(R.id.imageViewVistaPreg);

        final Pregunta pregunta = listPreg.get(position);

        if (pregunta instanceof PregSeleccion)
            tipoPreg.setText("Tipo de Pregunta: " + "Selección");
        else if (pregunta instanceof PregNumerica)
            tipoPreg.setText("Tipo de Pregunta: " + "Numérica");
        else if (pregunta instanceof PregVerdFalso)
            tipoPreg.setText("Tipo de Pregunta: " + "Verdadero o Falso");

        descrpPreg.setText("Descripcion: " + pregunta.getEnumPreg());

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle(R.string.ConfirmEliminar);
                dialog.setMessage(R.string.DesElimPreg);

                dialog.setPositiveButton(R.string.btnSi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (pregunta instanceof PregSeleccion)
                            LocalizacionServicios.getServicios().pregSeleccionServicios.eliminarPregSelecc(pregunta.getCodPreg(), context);
                        else if (pregunta instanceof PregNumerica)
                            LocalizacionServicios.getServicios().pregNumericaServicios.eliminarPregNum(pregunta.getCodPreg(), context);
                        else if (pregunta instanceof PregVerdFalso)
                            LocalizacionServicios.getServicios().pregVerdFalsoServicios.eliminarPregVerdFalso(pregunta.getCodPreg(), context);

                        listPreg.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(parent.getContext(), R.string.ElimadoPreg, Toast.LENGTH_SHORT).show();

                        Formulario formulario = LocalizacionServicios.getServicios().formularioServicios.buscarFormulario(pregunta.getCodForm(),context);

                        Intent intent = new Intent(context, FormularioActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("formulario", formulario);
                        intent.putExtras(bundle);
                        Activity activity = (Activity) context;
                        activity.startActivity(intent);
                        activity.finish();



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
        });

        imgVisual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("pregunta", pregunta);

                if (pregunta instanceof PregSeleccion) {

                    Intent intent = new Intent(context, VisualizarPregSeleccActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);


                } else if (pregunta instanceof PregNumerica) {

                    Intent intent = new Intent(context, VisualizarPregNumActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);


                } else if (pregunta instanceof PregVerdFalso) {

                    Intent intent = new Intent(context, VisualizarPregVerdFalsoActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }

            }
        });


        return view;
    }

}