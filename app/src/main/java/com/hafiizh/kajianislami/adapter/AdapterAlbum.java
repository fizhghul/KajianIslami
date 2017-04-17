package com.hafiizh.kajianislami.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hafiizh.kajianislami.Album;
import com.hafiizh.kajianislami.R;
import com.hafiizh.kajianislami.fragment.Video;
import com.hafiizh.kajianislami.network.Url;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HAFIIZH on 4/16/2017.
 */

public class AdapterAlbum extends RecyclerView.Adapter<AdapterAlbum.ViewHolder> {
    private List<HashMap<String, String>> listItems;
    private AppCompatActivity activity;
    LinearLayout video, audio;
    Bundle bundle = new Bundle();
    ProgressDialog pd;
    MediaPlayer mp;

    public AdapterAlbum(List<HashMap<String, String>> listItems, AppCompatActivity activity) {
        this.listItems = listItems;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_album, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final HashMap<String, String> listItem = listItems.get(position);
        holder.nama.setText(activity.getIntent().getStringExtra(Url.NAMA_KATEGORI));
        holder.judul.setText(listItem.get(Url.JUDUL_PERKAT));
        Glide.with(activity).load(Url.FOLDER_GAMBAR + listItem.get(Url.GAMBAR_PERKAT)).placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_report_image).into(holder.gambar);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                LayoutInflater inflater = activity.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.choose_play, null);
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setTitle("Choose play with !");
                dialog.setView(dialogView);
                video = (LinearLayout) dialogView.findViewById(R.id.choose_video);
                audio = (LinearLayout) dialogView.findViewById(R.id.choose_audio);
                video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bundle.putString(Url.VIDEO, listItem.get(Url.VIDEO_PERKAT));
                        Video video = new Video();
                        video.setArguments(bundle);
                        Album.draggablePanel.maximize();
                    }
                });
                audio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogView.forceLayout();
                        try {
                            pd = new ProgressDialog(activity);
                            pd.setMessage("Buffering.....");
                            pd.show();
                            mp = new MediaPlayer();
                            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    Log.i("Stream Audio Stream", "Prepared Finished");
                                    pd.dismiss();
                                    mp.start();
                                }
                            });
                            mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                                @Override
                                public boolean onError(MediaPlayer mp, int what, int extra) {
                                    pd.dismiss();
                                    return false;
                                }
                            });
                            mp.setDataSource(Url.FOLDER_AUDIO + listItem.get(Url.AUDIO_PERKAT));
                            mp.prepareAsync();
                            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    Toast.makeText(activity, "Completed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (Exception e) {
                            Log.e("Stream Audio Stream", e.getMessage());
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView judul, nama;
        public ImageView gambar;
        public CardView card;

        public ViewHolder(View v) {
            super(v);
            judul = (TextView) v.findViewById(R.id.judul_album);
            nama = (TextView) v.findViewById(R.id.nama_album);
            gambar = (ImageView) v.findViewById(R.id.gambar_album);
            card = (CardView) v.findViewById(R.id.cardAlbum);
        }
    }
}
