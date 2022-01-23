package com.proyecto.gilsoft.formprofesor.app;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.proyecto.gilsoft.formprofesor.R;

public class InformacionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        toolbar = (Toolbar) findViewById(R.id.ToolbInfo);
        setSupportActionBar(toolbar);

        /*
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        */

        imageView = (ImageView) findViewById(R.id.imgBackInform);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

    }

}
