package com.example.appmussic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmussic.Adapter.Viewpageplaylistnhac;
import com.example.appmussic.Fragment.Fragment_Playlist;
import com.example.appmussic.Fragment.Fragment_dianhac;
import com.example.appmussic.Fragment.Fragment_playdanhsachbaihat;
import com.example.appmussic.Model.BaiHat;
import com.example.appmussic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class PlaynhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imageplay, imgrepeat, imgnext, imggrandom, imgpre;
    ViewPager viewPagerplaynhac;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static Viewpageplaylistnhac adapternhac;
    Fragment_dianhac fragment_dianhac;
    Fragment_playdanhsachbaihat fragmentPlaydanhsachbaihat;
    MediaPlayer mediaPlayer;
     int position = 0;
     boolean repeat= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playnhac);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetdatafromInten();
        init();
        eventClick();


    }

    private void eventClick() {
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                 if (adapternhac.getItem(1)!=null){
                     if (mangbaihat.size()>0){
                         fragment_dianhac.Playnhac(mangbaihat.get(0).getHinhbaihat());
                         handler.removeCallbacks(this);
                     }else {
                         handler.postDelayed(this,300);
                     }

                 }

            }
        },500);
        imageplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imageplay.setImageResource(R.drawable.iconplay);
                }else {
                    mediaPlayer.start();
                    imageplay.setImageResource(R.drawable.iconpause);
                }
            }
        });
    }

    private void GetdatafromInten() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baiHatArrayList;
            }
        }
    }


    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesog);
        sktime = findViewById(R.id.seekbarsong);
        imageplay = findViewById(R.id.imagebuttonplay);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        imgnext = findViewById(R.id.imagebuttonnext);
        imggrandom = findViewById(R.id.imagebuttonsuffe);
        imgpre = findViewById(R.id.imagebuttonpreview);
        viewPagerplaynhac = findViewById(R.id.Viewpageplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dianhac = new Fragment_dianhac();
        fragmentPlaydanhsachbaihat = new Fragment_playdanhsachbaihat();
        adapternhac = new Viewpageplaylistnhac(getSupportFragmentManager());
        adapternhac.Addfragment(fragmentPlaydanhsachbaihat);
        adapternhac.Addfragment(fragment_dianhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_dianhac = (Fragment_dianhac) adapternhac.getItem(1);
        if (mangbaihat.size()>0){
          getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
          new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
          imageplay.setImageResource(R.drawable.iconpause);

        }
    }
       class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
}
