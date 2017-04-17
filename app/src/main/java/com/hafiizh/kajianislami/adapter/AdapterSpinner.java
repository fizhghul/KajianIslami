package com.hafiizh.kajianislami.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hafiizh.kajianislami.R;

/**
 * Created by HAFIIZH on 4/16/2017.
 */

public class AdapterSpinner extends ArrayAdapter<String> {
    private Activity activity;
    private String[] nama;
    TextView list_nama;

    public AdapterSpinner(Activity activity, String[] nama) {
        super(activity, R.layout.list_spinner, nama);
        this.activity = activity;
        this.nama = nama;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_spinner, null, true);
        list_nama = (TextView) rowView.findViewById(R.id.list_nama_spinner);

        list_nama.setText(nama[position]);

        return rowView;
    }
}
