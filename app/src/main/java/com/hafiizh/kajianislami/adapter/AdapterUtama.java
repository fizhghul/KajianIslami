package com.hafiizh.kajianislami.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hafiizh.kajianislami.Album;
import com.hafiizh.kajianislami.R;
import com.hafiizh.kajianislami.network.Url;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HAFIIZH on 4/16/2017.
 */

public class AdapterUtama extends RecyclerView.Adapter<AdapterUtama.ViewHolder> {
    private List<HashMap<String, String>> listItems;
    private Activity activity;

    public AdapterUtama(List<HashMap<String, String>> listItems, Activity activity) {
        this.listItems = listItems;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_utama, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final HashMap<String, String> listItem = listItems.get(position);
        holder.nama.setText(listItem.get(Url.NAMA_KATEGORI));
        Glide.with(activity).load(Url.FOLDER_GAMBAR + listItem.get(Url.GAMBAR_KATEGORI)).placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_report_image).into(holder.gambar);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity);
                Intent a = new Intent(activity, Album.class);
                a.putExtra(Url.ID_KATEGORI, listItem.get(Url.ID_KATEGORI));
                a.putExtra(Url.NAMA_KATEGORI, listItem.get(Url.NAMA_KATEGORI));
                view.getContext().startActivity(a, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama;
        public ImageView gambar;
        public CardView card;

        public ViewHolder(View v) {
            super(v);
            nama = (TextView) v.findViewById(R.id.namaUtama);
            gambar = (ImageView) v.findViewById(R.id.imageUtama);
            card = (CardView) v.findViewById(R.id.cardUtama);
        }
    }
}
