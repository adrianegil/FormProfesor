package com.proyecto.gilsoft.formprofesor.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.proyecto.gilsoft.formprofesor.R;

public class SplashActivity extends AppCompatActivity {

    Animation frombottom, fromtop, desap;
    TextView textView, textView4;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView4 = (TextView) findViewById(R.id.textView4);
        textView = (TextView) findViewById(R.id.textView6);
        imageView = (ImageView) findViewById(R.id.imageView6);

        desap = AnimationUtils.loadAnimation(this, R.anim.desap);
        desap.setFillAfter(true);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        textView4.setAnimation(desap);
        imageView.setAnimation(fromtop);
        textView.setAnimation(frombottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 4000);
    }
}
