package com.proyecto.gilsoft.formprofesor.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.proyecto.gilsoft.formprofesor.R;
import com.proyecto.gilsoft.formprofesor.app.FormularioActivity;
import com.proyecto.gilsoft.formprofesor.app.MainActivity;
import com.proyecto.gilsoft.formprofesor.modelo.Formulario;
import com.proyecto.gilsoft.formprofesor.modelo.Pregunta;
import com.proyecto.gilsoft.formprofesor.servicios.LocalizacionServicios;
import java.util.ArrayList;

/**
 * Created by Adrian Gil on 21/6/2019.
 */

public class AdaptadorForm extends RecyclerView.Adapter<AdaptadorForm.ViewHolder> implements View.OnClickListener {

    private ArrayList<Formulario> listForm;
    private Context context;
    private View.OnClickListener listener;

    public AdaptadorForm(ArrayList<Formulario> listForm, Context context) {

        this.listForm = listForm;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_itemform, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Formulario form = listForm.get(position);
        ArrayList<Pregunta> list = LocalizacionServicios.getServicios().preguntaServicios.listPreguntas(form.getCodForm(), context);

        holder.textNombForm.setText(form.getNombForm());
        holder.texCantPreg.setText("Total de Preguntas: " + list.size());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle(R.string.ConfirmEliminar);
                dialog.setMessage(context.getString(R.string.DesRealElimForm) + form.getNombForm());
                dialog.setPositiveButton(R.string.btnSi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LocalizacionServicios.getServicios().formularioServicios.eliminarFormulario(form, context);
                        listForm.remove(form);
                        notifyDataSetChanged();

                        Toast.makeText(context, context.getString(R.string.FormElim) + form.getNombForm(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, MainActivity.class);

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
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, FormularioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("formulario", form);
                intent.putExtras(bundle);
                Activity activity = (Activity) context;
                activity.startActivity(intent);
                activity.finish();

            }
        });


    }

    @Override
    public int getItemCount() {

        return listForm.size();

    }

    @Override
    public void onClick(View v) {

        if (listener != null) {
            listener.onClick(v);
        }
    }



    public void setOnClickListener(View.OnClickListener listener) {

        this.listener = listener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textNombForm;
        public TextView texCantPreg;
        public ImageView imgDelete;
        public ImageView imgEdit;

        public ViewHolder(View itemView) {

            super(itemView);

            this.textNombForm = (TextView) itemView.findViewById(R.id.NombForm);
            this.texCantPreg = (TextView) itemView.findViewById(R.id.textViewCantPreg);
            this.imgDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
            this.imgEdit = (ImageView) itemView.findViewById(R.id.imageViewEdit);

        }


    }

}
