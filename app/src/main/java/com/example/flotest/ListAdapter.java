package com.example.flotest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<MusicList> {
    List<MusicList> lists;

    public ListAdapter(Context context, ArrayList<MusicList> musiclist){
        super(context, R.layout.list_card, musiclist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MusicList musicList = getItem(position);

        if(convertView == null){ //list_itmes = list_card
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_card,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.imgURL);
        TextView tv_Title = convertView.findViewById(R.id.tv_title);
        TextView tv_Artist = convertView.findViewById(R.id.tv_artist);

        //imageView.setImageResource(musicList.get_imgURL());
        tv_Title.setText(musicList.get_title());
        tv_Artist.setText(musicList.get_artist());




        return convertView;
    }
}
