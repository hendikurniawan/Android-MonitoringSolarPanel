package com.example.monitoringsolars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.example.monitoringsolars.server.Config_URL;

public class Graph extends AppCompatActivity {

    private SwipeRefreshLayout swLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        swLayout = (SwipeRefreshLayout)findViewById(R.id.swlayout);
        swLayout.setColorSchemeResources(R.color.blueset3);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swLayout.setRefreshing(false);

                        Intent intent = new Intent(Graph.this, Graph.class);
                        startActivity(intent);

                        //llayout = startActivity(new Intent(this, DataRealTime.class));
                        //llayout = new ArrayAdapter<String>(DataRealTime.this, R.layout.activity_data_real_time);
                    }
                }, 1000);

            }
        });


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        final WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        // Tiga baris di bawah ini agar laman yang dimuat dapat
        // melakukan zoom.
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.solarmeter.id/chart/index.php");

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Graph.this, Menu.class);
        startActivity(a);
        finish();
    }
}