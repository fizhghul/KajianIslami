package com.hafiizh.kajianislami.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hafiizh.kajianislami.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Semua extends Fragment {


    public Semua() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_semua, container, false);
        return v;
    }
}
