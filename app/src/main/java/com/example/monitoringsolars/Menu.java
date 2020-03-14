package com.example.monitoringsolars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import butterknife.ButterKnife;
import butterknife.OnClick;

public class Menu extends AppCompatActivity {
    ImageView GambarGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();
        ButterKnife.bind(this);

        // TAMBAHKAN INI
        GambarGif = (ImageView)findViewById(R.id.logo);

        Glide.with(Menu.this)
                // LOAD URL DARI LOKAL DRAWABLE
                .load(R.drawable.giff)
                .asGif()
                //PENGATURAN CACHE
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(GambarGif);

    }

    @OnClick(R.id.btnHistory)
    void dataHistori() {
        Intent a = new Intent(Menu.this, ListDataSolar.class);
        startActivity(a);
        finish();
    }


    @OnClick(R.id.btnTemHum)
    void btnTemHum() {
        Intent b = new Intent(Menu.this, MonitorData.class);
        startActivity(b);
        finish();
    }

    @OnClick(R.id.btnChart)
    void btnChart() {
        Intent b = new Intent(Menu.this, Graph.class);
        startActivity(b);
        finish();
    }

    @OnClick(R.id.btnControl)
    void btnControl() {
        Intent b = new Intent(Menu.this, Control.class);
        startActivity(b);
        finish();
    }

}
