package com.hafiizh.kajianislami;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
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
import com.github.pedrovgs.DraggablePanel;
import com.hafiizh.kajianislami.adapter.AdapterAlbum;
import com.hafiizh.kajianislami.adapter.AdapterSpinner;
import com.hafiizh.kajianislami.adapter.AdapterUtama;
import com.hafiizh.kajianislami.fragment.ListVideo;
import com.hafiizh.kajianislami.fragment.Video;
import com.hafiizh.kajianislami.network.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album extends AppCompatActivity {
    SwipeRefreshLayout swipe;
    RecyclerView rvAlbum;
    List<HashMap<String, String>> list = new ArrayList<>();
    Spinner spinner;
    AdapterSpinner adapterSpinner;
    String[] tampilkan = {
            "Tampilkan berdasarkan Nama",
            "Tampilkan berdasarkan Terbaru"
    };
    String url_php = Url.PHP_PERKAT;
    RelativeLayout layout, data;
    Button try_again;
    AdapterAlbum adapter;
    ProgressBar loading;
    String getId;
    public static DraggablePanel draggablePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        Fade enterTransition = new Fade();
        enterTransition.setDuration(2000);
        getWindow().setEnterTransition(enterTransition);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        draggablePanel = (DraggablePanel) findViewById(R.id.draggable_panel);
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(new Video());
        draggablePanel.setBottomFragment(new ListVideo());
        draggablePanel.setTopViewHeight(500);
        draggablePanel.initializeView();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                draggablePanel.closeToLeft();
            }
        }, 100);
        rvAlbum = (RecyclerView) findViewById(R.id.rvAlbum);
        rvAlbum.setHasFixedSize(true);
        rvAlbum.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        layout = (RelativeLayout) findViewById(R.id.no_internet_album);
        data = (RelativeLayout) findViewById(R.id.data_album);
        data.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
        loading = (ProgressBar) findViewById(R.id.loading_album);
        loading.setVisibility(View.GONE);
        try_again = (Button) findViewById(R.id.try_again_album);
        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                loadDataAlbum();
            }
        });
        spinner = (Spinner) findViewById(R.id.tampilkan_album);
        adapterSpinner = new AdapterSpinner(this, tampilkan);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        url_php = Url.PHP_PERKAT;
                        loadDataAlbum();
                        break;
                    case 1:
                        url_php = Url.PHP_PERKAT_OB_ID;
                        loadDataAlbum();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        swipe = (SwipeRefreshLayout) findViewById(R.id.refreshAlbum);
        swipe.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                loadDataAlbum();
            }
        });
        loadDataAlbum();
    }

    private void loadDataAlbum() {
        list.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_php, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray(Url.ALBUM);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(Url.ID_PERKAT, o.getString(Url.ID_PERKAT));
                        map.put(Url.JUDUL_PERKAT, o.getString(Url.JUDUL_PERKAT));
                        map.put(Url.GAMBAR_PERKAT, o.getString(Url.GAMBAR_PERKAT));
                        map.put(Url.VIDEO_PERKAT, o.getString(Url.VIDEO_PERKAT));
                        map.put(Url.AUDIO_PERKAT, o.getString(Url.AUDIO_PERKAT));
                        list.add(map);
                        swipe.setRefreshing(false);
                        loading.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                    }
                    adapter = new AdapterAlbum(list, Album.this);
                    rvAlbum.setAdapter(adapter);
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
                getId = getIntent().getStringExtra(Url.ID_KATEGORI);
                Map<String, String> map = new HashMap<>();
                map.put(Url.ID_KATEGORI, getId);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finishAfterTransition();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        super.onBackPressed();
    }
}
