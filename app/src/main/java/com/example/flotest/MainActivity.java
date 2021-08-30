package com.example.flotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.flotest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

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












    }


}