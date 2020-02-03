package com.example.monitoringsolars;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonitorData extends AppCompatActivity {

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

        suhus = (TextView) findViewById(R.id.txtSuhu);
        kelembabans = (TextView) findViewById(R.id.txtKelembaban);
        tanggals = (TextView) findViewById(R.id.txtTanggal);
        voltages = (TextView) findViewById(R.id.txtVoltage);
        amperes = (TextView) findViewById(R.id.txtAmpere);
        watts = (TextView) findViewById(R.id.txtWatt);
        hertzs = (TextView) findViewById(R.id.txtHertz);

        getData();
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
                            boolean status = jObj.getBoolean("success");

                            if (status == true) {

                                String getObject = jObj.getString("message");
                                JSONArray jsonArray = new JSONArray(getObject);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    final JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String dataSuhu = jsonObject.getString("suhu");
                                    String dataKelem = jsonObject.getString("kelembaban");
                                    String dataVolt = jsonObject.getString("volt");
                                    String dataAmper = jsonObject.getString("amper");
                                    String dataWatt = jsonObject.getString("watt");
                                    String dataHertz = jsonObject.getString("hertz");
//                                String dataTgl = jsonObject.getString("tanggal");

                                    JSONObject suhu = new JSONObject(dataSuhu);
                                    JSONObject kelem = new JSONObject(dataKelem);
                                    JSONObject volt = new JSONObject(dataVolt);
                                    JSONObject amper = new JSONObject(dataAmper);
                                    JSONObject watt = new JSONObject(dataWatt);
                                    JSONObject hertz = new JSONObject(dataHertz);
//                                JSONObject tgl = new JSONObject(dataTgl);

                                    suhus.setText(suhu.getString("data"));
                                    kelembabans.setText(kelem.getString("data"));
                                    voltages.setText(volt.getString("data"));
                                    amperes.setText(amper.getString("data"));
                                    watts.setText(watt.getString("data"));
                                    hertzs.setText(hertz.getString("data"));
                                    tanggals.setText(jsonObject.getString("tanggal"));
                                }
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

    @OnClick(R.id.btnRefresh)
    void btnRefresh() {
        Intent a = new Intent(MonitorData.this, MonitorData.class);
        startActivity(a);
        finish();
    }
}