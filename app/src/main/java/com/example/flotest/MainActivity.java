package com.example.flotest;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.flotest.databinding.ActivityMainBinding;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int song_state = 0; //0 = 일시정지 상태, 1= 재생상태
    MediaPlayer mediaPlayer;
    int pausePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        
        //추후 MusicList 클래스에서 서버, DB통신 간 데이터 얻어오는걸로
         String title[] = {"바람기억", "좋니", "있잖아", "바라만본다", "슬픔활용법", "너랑나", "잔소리"}; 
         String artist[] = {"나얼", "윤종신", "IU", "MSG워너비", "김범수", "IU", "임슬옹(feet.IU)"};

         ArrayList<MusicList> arrayList= new ArrayList<MusicList>();

         for(int i = 0 ; i<title.length ; i++){
             MusicList musicList = new MusicList(R.drawable.heart, title[i], artist[i]);
             arrayList.add(musicList);
         }

         ListAdapter listAdapter = new ListAdapter(MainActivity.this, arrayList);

         binding.playListView.setAdapter(listAdapter);
         binding.playListView.setClickable(true);


        ImageButton forward_btn = (ImageButton) findViewById(R.id.foward_btn);
        ImageButton play_pause_btn = (ImageButton) findViewById(R.id.play_btn);
        ImageButton rewind_btn = (ImageButton) findViewById(R.id.back_btn);

        play_pause_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(song_state == 0) {
                    play_pause_btn.setImageResource(R.drawable.pause_btn);
                    //재생 서술
                    if(mediaPlayer == null){
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.goback);
                        mediaPlayer.start();
                    }
                    else{
                        mediaPlayer.seekTo(pausePosition);
                        mediaPlayer.start();
                    }

                    song_state = 1;

                }
                else{
                    play_pause_btn.setImageResource(R.drawable.play_btn);
                    //일시정지 서술
                    song_state = 0;
                    mediaPlayer.pause();
                    pausePosition = mediaPlayer.getCurrentPosition();
                }

            }
        });













    }


}