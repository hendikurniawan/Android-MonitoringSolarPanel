package com.example.monitoringsolars;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.monitoringsolars.data.DataSolars;
import com.example.monitoringsolars.server.Config_URL;
import android.support.v4.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonitorData extends AppCompatActivity {
    private  SwipeRefreshLayout swLayout;

    TextView suhus, kelembabans, tanggals, voltages, amperes, watts, hertzs;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_data);

        getSupportActionBar().hide();
        ButterKnife.bind(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

//        suhus = (TextView) findViewById(R.id.txtSuhu);
//        kelembabans = (TextView) findViewById(R.id.txtKelembaban);
        tanggals = (TextView) findViewById(R.id.txtTanggal);
        voltages = (TextView) findViewById(R.id.txtVoltage);
        amperes = (TextView) findViewById(R.id.txtAmpere);
        watts = (TextView) findViewById(R.id.txtWatt);
        hertzs = (TextView) findViewById(R.id.txtHertz);

        getData();

        swLayout = (SwipeRefreshLayout)findViewById(R.id.swlayout);
        swLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swLayout.setRefreshing(false);

                        Intent intent = new Intent(MonitorData.this, MonitorData.class);
                        startActivity(intent);

                        //llayout = startActivity(new Intent(this, DataRealTime.class));
                        //llayout = new ArrayAdapter<String>(DataRealTime.this, R.layout.activity_data_real_time);
                    }
                }, 2000);

            }
        });
    }

    private void getData() {

        pDialog.setMessage("Loading.....");
        showDialog();
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        final JSONObject jsonBody = new JSONObject();
        final String requestBody = jsonBody.toString();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config_URL.dataRealtime,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
                            JSONObject jObj = new JSONObject(response);
//                            Log.d("Response = ", response.toString());
                            boolean status = jObj.getBoolean("success");

                            if (status == true) {

                                String getObject = jObj.getString("results");
//                                String getObjects = jObj.getString("tanggal");
                                JSONObject jsonObject = new JSONObject(getObject);
//                                JSONObject jsonObjects = new JSONObject(getObjects);

//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    final JSONObject jsonObject = jsonArray.getJSONObject(i);

//                                    String dataSuhu = jsonObject.getString("suhu");
//                                    String dataKelem = jsonObject.getString("kelembaban");
//                                    String dataVolt = jsonObject.getString("volt");
//                                    String dataAmper = jsonObject.getString("amper");
//                                    String dataWatt = jsonObject.getString("watt");
//                                    String dataHertz = jsonObject.getString("hertz");
//                                String dataTgl = jsonObject.getString("tgl_update");

//                                    JSONObject suhu = new JSONObject(dataSuhu);
//                                    JSONObject kelem = new JSONObject(dataKelem);
//                                    JSONObject volt = new JSONObject(dataVolt);
//                                    JSONObject amper = new JSONObject(dataAmper);
//                                    JSONObject watt = new JSONObject(dataWatt);
//                                    JSONObject hertz = new JSONObject(dataHertz);
//                                JSONObject tgl = new JSONObject(dataTgl);

//                                    suhus.setText(jsonObject.getString("suhu"));
//                                    kelembabans.setText(jsonObject.getString("kelembaban"));
                                    voltages.setText(jsonObject.getString("volt"));
                                    amperes.setText(jsonObject.getString("amper"));
                                    watts.setText(jsonObject.getString("watt"));
                                    hertzs.setText(jsonObject.getString("hertz"));
                                    tanggals.setText(jObj.getString("terakhir update"));

//                                  tanggals.setText(jsonObjects.getString("date").replace(".000000", ""));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){
                Log.e(String.valueOf(getApplication()), "Error : " + error.getMessage());
                error.printStackTrace();
                hideDialog();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(MonitorData.this, Menu.class);
        startActivity(a);
        finish();
    }

//    @OnClick(R.id.btnRefresh)
//    void btnRefresh() {
//        Intent a = new Intent(MonitorData.this, MonitorData.class);
//        startActivity(a);
//        finish();
    }
