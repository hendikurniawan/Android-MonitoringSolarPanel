package com.example.monitoringsolars.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.monitoringsolars.R;
import com.example.monitoringsolars.data.DataSolars;

import java.util.List;

public class AdapterSolars extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DataSolars> item;

    public AdapterSolars(Activity activity, List<DataSolars> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_solar, null);


        TextView suhu         = (TextView) convertView.findViewById(R.id.txtSuhu);
        TextView kelembaban   = (TextView) convertView.findViewById(R.id.txtKelembaban);
        TextView tanggal      = (TextView) convertView.findViewById(R.id.txtTanggal);
        TextView volt         = (TextView) convertView.findViewById(R.id.txtVoltage);
        TextView amper        = (TextView) convertView.findViewById(R.id.txtAmpere);
        TextView kwh          = (TextView) convertView.findViewById(R.id.txtkwh);
        TextView hertz        = (TextView) convertView.findViewById(R.id.txtHertz);
        TextView watt         = (TextView) convertView.findViewById(R.id.txtWatt);

        suhu.setText(": "+item.get(position).getSuhu());
        kelembaban.setText(": "+item.get(position).getKelembaban());
        tanggal.setText(": "+item.get(position).getTanggal());
        volt.setText(": "+item.get(position).getVolt());
        amper.setText(": "+item.get(position).getAmpere());
        kwh.setText(": "+item.get(position).getKwh());
        hertz.setText(": "+item.get(position).getHertz());
        watt.setText(": "+item.get(position).getWatt());

        return convertView;
    }
}
