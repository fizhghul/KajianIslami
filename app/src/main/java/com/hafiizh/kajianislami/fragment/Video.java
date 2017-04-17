package com.hafiizh.kajianislami.fragment;


import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hafiizh.kajianislami.Album;
import com.hafiizh.kajianislami.R;
import com.hafiizh.kajianislami.network.Url;

/**
 * A simple {@link Fragment} subclass.
 */
public class Video extends Fragment {
    VideoView videoView;
    String data, get;
    ProgressDialog progressDialog;


    public Video() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        videoView = (VideoView) v.findViewById(R.id.video_play);
        try {
            get = getArguments().getString(Url.VIDEO);
            data = Url.FOLDER_VIDEO + get;
        } catch (Exception e) {
            Log.d("data", "data error");
            Album.draggablePanel.closeToLeft();
        }
        try {
            if (get != null) {
                data = Url.FOLDER_VIDEO + get;
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("Loading ...");
                progressDialog.setMessage("Buffering...");
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(true);
                progressDialog.show();
                try {
                    MediaController mediaController = new MediaController(getActivity());
                    mediaController.setAnchorView(videoView);
                    Uri video = Uri.parse(data);
                    videoView.setMediaController(mediaController);
                    videoView.setVideoURI(video);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                videoView.requestFocus();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        progressDialog.dismiss();
                        videoView.start();
                    }
                });
            } else {
                Log.d("data", "play video error");
                Album.draggablePanel.closeToLeft();
            }
        } catch (Exception e) {
            Log.d("data", "error catch");
            Album.draggablePanel.closeToLeft();
        }
        return v;
    }
}
