package com.hafiizh.kajianislami.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
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
import com.hafiizh.kajianislami.FullScreenManager;
import com.hafiizh.kajianislami.R;
import com.hafiizh.kajianislami.fragment.Semua;
import com.hafiizh.kajianislami.network.DownloadManagerController;
import com.hafiizh.kajianislami.network.Url;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HAFIIZH on 4/23/2017.
 */

public class AdapterSemua extends RecyclerView.Adapter<AdapterSemua.ViewHolder> {
    private List<HashMap<String, String>> listItems;
    private Activity activity;
    private LinearLayout video, audio;
    private ProgressDialog pd;
    public static MediaPlayer mp = new MediaPlayer();
    private AlertDialog.Builder dialog_show, dialog_choose, dialog_download, dialog_video;
    private DownloadManagerController download;
    private long file_download;
    private Uri uri_audio, uri_video;
    public static String url_play_audio;
    FullScreenManager fullScreenManager;

    public AdapterSemua(List<HashMap<String, String>> listItems, Activity activity) {
        this.listItems = listItems;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_album, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        fullScreenManager = new FullScreenManager(activity);

        final HashMap<String, String> listItem = listItems.get(position);

        dialog_show = new AlertDialog.Builder(activity);
        dialog_choose = new AlertDialog.Builder(activity);
        dialog_download = new AlertDialog.Builder(activity);
        dialog_video = new AlertDialog.Builder(activity);

        holder.judul.setText(listItem.get(Url.JUDUL_PERKAT));
        Glide.with(activity).load(listItem.get(Url.GAMBAR_PERKAT))
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(holder.gambar);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(position);
            }
        });
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                showDialogChoose(position, listItems.get(position).get(Url.JUDUL_PERKAT));
                return false;
            }
        });
    }

    private void showDialogDownload(final int position, String judul) {
        download = new DownloadManagerController(judul, activity, file_download);

        uri_audio = Uri.parse(listItems.get(position).get(Url.AUDIO_PERKAT));
        uri_video = Uri.parse(listItems.get(position).get(Url.VIDEO_PERKAT));
        String unduh = "Download";
        String[] dialog_item = {unduh + " video " + listItems.get(position).get(Url.JUDUL_PERKAT), unduh + " audio " + listItems.get(position).get(Url.JUDUL_PERKAT)};
        dialog_download.setCancelable(true).setItems(dialog_item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        try {
                            file_download = download.DownloadData(uri_video);
                        } catch (Exception e) {
                            Toast.makeText(activity, "Maaf video ini belum bisa di download !", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        try {
                            file_download = download.DownloadData(uri_audio);
                        } catch (Exception e) {
                            Toast.makeText(activity, "Maaf audio ini tidak bisa di download !", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        }).show();
    }

    private void showDialogChoose(final int position, final String judul) {
        String[] dialog_item = {"Choose Play With", "Download"};
        dialog_choose.setCancelable(true).setItems(dialog_item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        showDialog(position);
                        break;
                    case 1:
                        showDialogDownload(position, judul);
                        break;
                }
            }
        }).show();
    }

    public void showDialog(final int position) {
        final HashMap<String, String> listItem = listItems.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.choose_play, null);
        dialog_show.setIcon(R.drawable.kajian).setTitle("Choose play with !").setView(dialogView);
        final AlertDialog alertDialog = dialog_show.create();
        alertDialog.show();
        video = (LinearLayout) dialogView.findViewById(R.id.choose_video);
        audio = (LinearLayout) dialogView.findViewById(R.id.choose_audio);

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                showDialogVideo(position);
            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                url_play_audio = listItem.get(Url.AUDIO_PERKAT);
                if (mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                    play_audio();
                } else {
                    play_audio();
                }
            }
        });
    }

    private void showDialogVideo(final int position) {
        dialog_video.setTitle("Info !")
                .setMessage("Membutuhkan koneksi cepat untuk memutar video")
                .setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Semua.youTubePlayerView.loadVideo(listItems.get(position).get(Url.VIDEO_PERKAT), 0);
                    }
                })
                .setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void play_audio() {
        if (mp.isPlaying()) {
            mp.stop();
        } else {
            try {
                Semua.fab.setImageResource(android.R.drawable.ic_media_pause);
                pd = new ProgressDialog(activity);
                pd.setMessage("Buffering.....");
                pd.show();
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer media) {
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
                mp.setDataSource(url_play_audio);
                mp.prepareAsync();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer media) {
                        Toast.makeText(activity, "Completed", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Log.e("Stream Audio Stream", "Error");
            }
        }
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
