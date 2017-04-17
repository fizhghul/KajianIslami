package com.hafiizh.kajianislami.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hafiizh.kajianislami.R;
import com.hafiizh.kajianislami.adapter.AdapterSpinner;
import com.hafiizh.kajianislami.adapter.AdapterUtama;
import com.hafiizh.kajianislami.network.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Kategori extends Fragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipe;
    AdapterUtama adapter;
    List<HashMap<String, String>> list = new ArrayList<>();
    Spinner spinner;
    AdapterSpinner adapterSpinner;
    String[] tampilkan = {
            "Tampilkan berdasarkan Nama",
            "Tampilkan berdasarkan Terbaru"
    };
    String url_php = Url.PHP_KATEGORI;
    RelativeLayout layout, data;
    Button try_again;
    ProgressBar loading;

    public Kategori() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kategori, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rvKategori);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        layout = (RelativeLayout) v.findViewById(R.id.no_internet);
        data = (RelativeLayout) v.findViewById(R.id.data);
        data.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
        loading = (ProgressBar) v.findViewById(R.id.loading);
        loading.setVisibility(View.GONE);
        try_again = (Button) v.findViewById(R.id.try_again);
        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                loadDataKategori();
            }
        });
        spinner = (Spinner) v.findViewById(R.id.tampilkan);
        adapterSpinner = new AdapterSpinner(getActivity(), tampilkan);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        url_php = Url.PHP_KATEGORI;
                        loadDataKategori();
                        break;
                    case 1:
                        url_php = Url.PHP_KATEGORI_OB_ID;
                        loadDataKategori();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        swipe = (SwipeRefreshLayout) v.findViewById(R.id.refreshKategori);
        swipe.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                loadDataKategori();
            }
        });
        loadDataKategori();
        return v;
    }

    private void loadDataKategori() {
        list.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_php, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray(Url.KATEGORI);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(Url.ID_KATEGORI, o.getString(Url.ID_KATEGORI));
                        map.put(Url.NAMA_KATEGORI, o.getString(Url.NAMA_KATEGORI));
                        map.put(Url.GAMBAR_KATEGORI, o.getString(Url.GAMBAR_KATEGORI));
                        list.add(map);
                        swipe.setRefreshing(false);
                        loading.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                    }
                    adapter = new AdapterUtama(list, getActivity());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
