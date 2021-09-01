package com.example.flotest;
// mysql = kjm, woals12
// dbname = flotest
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.flotest.databinding.ActivityMainBinding;
import java.util.ArrayList;

/*
Array
(
    [0] => Array
        (
            [title] => 바람기억
            [artist] => 나얼
            [imgurl] => http://drive.google.com/uc?export=view&id=1YcjIzbpNEVIGi4svwR94-XXzXnsbzqzx
        )
 */



public class MainActivity extends AppCompatActivity {

    private String ip = "14.44.6.129";
    ActivityMainBinding binding;
    int song_state = 0; //0 = 일시정지 상태, 1= 재생상태
    MediaPlayer mediaPlayer;
    int pausePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSelfPermission();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        GetData task = new GetData();
        task.execute();




        //추후 MusicList 클래스에서 서버, DB통신 간 데이터 얻어오는걸로
        String title[] = {"바람기억", "고백", "좋니", "있잖아", "바라만본다", "슬픔활용법", "너랑나", "잔소리"};
        String artist[] = {"나얼", "장범준", "윤종신", "IU", "MSG워너비", "김범수", "IU", "임슬옹(feet.IU)"};

        ArrayList<MusicList> arrayList = new ArrayList<MusicList>();

        for (int i = 0; i < title.length; i++) {
            MusicList musicList = new MusicList(R.drawable.heart, title[i], artist[i]);
            arrayList.add(musicList);
        }

        ListAdapter listAdapter = new ListAdapter(MainActivity.this, arrayList);

        binding.playListView.setAdapter(listAdapter);
        binding.playListView.setClickable(true);


        ImageButton forward_btn = (ImageButton) findViewById(R.id.foward_btn);
        ImageButton play_pause_btn = (ImageButton) findViewById(R.id.play_btn);
        ImageButton rewind_btn = (ImageButton) findViewById(R.id.back_btn);

        play_pause_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (song_state == 0) {
                    play_pause_btn.setImageResource(R.drawable.pause_btn);
                    //재생 서술
                    if (mediaPlayer == null) {
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.goback);
                        mediaPlayer.start();
                    } else {
                        mediaPlayer.seekTo(pausePosition);
                        mediaPlayer.start();
                    }

                    song_state = 1;

                } else {
                    play_pause_btn.setImageResource(R.drawable.play_btn);
                    //일시정지 서술
                    song_state = 0;
                    mediaPlayer.pause();
                    pausePosition = mediaPlayer.getCurrentPosition();
                }

            }


        });


    }

    private void checkSelfPermission() { //INTERNER은 위험권한 아니라서 런타임 요청이 필요 없을텐데...
        String tmp ="";
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            tmp = tmp + Manifest.permission.INTERNET + " ";
        }
        if(TextUtils.isEmpty(tmp) == false){ // 권한 없을 시 요청
            ActivityCompat.requestPermissions(this, tmp.trim().split(" "), 1000);
        }
        else
            Toast.makeText(this, "권한 허용 완료", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            int length  = permissions.length;
            for(int i = 0 ; i<length ; i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    //권한 허용
                    Log.d("MainActivity", "권한허용 : " + permissions[i]);
                }
            }
        }
    }
}