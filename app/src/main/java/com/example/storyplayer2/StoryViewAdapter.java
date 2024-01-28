package com.example.storyplayer2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class StoryViewAdapter extends RecyclerView.Adapter<StoryViewAdapter.StoryViewHolder> {

    String[] usernameList;
    String[] ppUrlList;
    RecyclerView storyViewRV;


    public StoryViewAdapter(String[] usernameList, String[] ppUrlList, RecyclerView storyViewRV) {
        this.usernameList = usernameList;
        this.ppUrlList = ppUrlList;
        this.storyViewRV = storyViewRV;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.story_icon_item,parent,false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        holder.username.setText(usernameList[position]);
        Glide.with(storyViewRV)
                .load(ppUrlList[position])
                .into(holder.imageView);
        holder.frameLayout.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), StoryPlayerActivity.class);
//                intent.putExtra("usernameList",usernameList);
//                intent.putExtra("ppUrlList",ppUrlList);
//                intent.putExtra("position",holder.getAdapterPosition());

                view.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return usernameList.length;
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder
    {
        TextView username;
        FrameLayout frameLayout;
        ImageView imageView;
        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            frameLayout = itemView.findViewById(R.id.frameLayout);
            imageView = itemView.findViewById(R.id.profile_image);
        }
    }

}