package com.example.storyplayer2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView storyViewRV;
    String[] usernameList = {"Göksu","Simay","Halil","Berkay","Loki"};
    String[] ppUrlList = {"https://source.unsplash.com/user/c_v_r/80x80",
            "https://source.unsplash.com/user/c_v_r/90x90",
            "https://source.unsplash.com/user/c_v_r/100x100",
            "https://source.unsplash.com/user/c_v_r/128x128",
            "https://source.unsplash.com/user/c_v_r/256x256"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storyViewRV = findViewById(R.id.storyViewRV);
        storyViewRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        storyViewRV.setAdapter(new StoryViewAdapter(usernameList,ppUrlList,storyViewRV));
    }
}