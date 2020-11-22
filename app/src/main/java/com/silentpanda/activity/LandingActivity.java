package com.silentpanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.silentpanda.R;


public class LandingActivity extends Activity {
    VideoView videoView;
    ImageView skipBtn,scanTagBtn;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        videoView=(VideoView) findViewById(R.id.videoView);
        skipBtn=(ImageView)findViewById(R.id.skipBtn);
        scanTagBtn=(ImageView)findViewById(R.id.scanTagBtn);
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.silent_panda);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LandingActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
        scanTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LandingActivity.this,ScanningActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.setVideoURI(uri);
        videoView.start();
    }
}
