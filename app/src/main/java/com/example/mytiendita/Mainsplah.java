package com.example.mytiendita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class Mainsplah extends Activity {
ImageView fondi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsplah);

        fondi = findViewById(R.id.imageView);
        Glide.with(getApplicationContext()).load(R.drawable.fondito).into(fondi);
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent pasar = new Intent(Mainsplah.this,MainActivity.class);
                startActivity(pasar);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea,5000);
    }

}