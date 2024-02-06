package com.example.storyplayer2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.storyplayer2.databinding.StoryBinding;
import com.hisham.jazzyviewpagerlib.JazzyViewPager;
import com.hisham.jazzyviewpagerlib.JazzyViewPager.TransitionEffect;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.shts.android.storiesprogressview.StoriesProgressView;

public class StoryPlayerActivity extends AppCompatActivity {
    private JazzyViewPager vpage;
    MainAdapter vpageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_story_player);

        vpage = findViewById(R.id.jazzy_pager);
        vpage.setTransitionEffect(TransitionEffect.CubeOut);
        Intent intent = getIntent();
        int[] counter = intent.getIntArrayExtra("counter");
        vpageAdapter = new MainAdapter(this,
                intent.getIntExtra("position",0),
                Objects.requireNonNull(intent.getStringArrayExtra("usernameList")),
                Objects.requireNonNull(intent.getStringArrayExtra("ppUrlList")),
                counter
        );
        vpage.setAdapter(vpageAdapter);
        vpage.setCurrentItem(intent.getIntExtra("position",0));
        vpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int oldPosition = -1;
            int tempPosition = -1;
            boolean scrolling = false;

            public void onPageScrollStateChanged(int state) {
                if(state == 0) {
                    if(tempPosition>=0) {
                        vpageAdapter.position=tempPosition;
                        tempPosition = -1;
                        vpageAdapter.startStories();
                    }else{
                        vpageAdapter.storiesProgressView.resume();
                    }
                }
            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(positionOffset>0){
                    scrolling = true;
                }else{
                    if (scrolling){
                        if (oldPosition != position){
                            tempPosition = position;
                        }else {
                            tempPosition = -1;
                        }
                    }
                    oldPosition = position;
                    scrolling = false;
                }
            }
            public void onPageSelected(int position) {}
        });
    }
    @Override
    public void finish() {
        super.finish();
        System.out.println("finish");
        StoryViewAdapter.counter = vpageAdapter.counter;
    }
    private class MainAdapter extends PagerAdapter implements StoriesProgressView.StoriesListener {
        private final StoryPlayerActivity activity;
        StoriesProgressView storiesProgressView;
        StoryBinding binding;
        View view;
        private ImageView image;
        private final String[][] ImageURls = {
                {"https://source.unsplash.com/user/c_v_r/100x100"},
                {"https://source.unsplash.com/user/c_v_r/125x125", "https://source.unsplash.com/user/c_v_r/100x150"},
                {"https://source.unsplash.com/user/c_v_r/175x175", "https://source.unsplash.com/user/c_v_r/200x200", "https://source.unsplash.com/user/c_v_r/225x225"},
                {"https://source.unsplash.com/user/c_v_r/250x250", "https://source.unsplash.com/user/c_v_r/275x275","https://source.unsplash.com/user/c_v_r/300x300", "https://source.unsplash.com/user/c_v_r/325x325"},
                {"https://source.unsplash.com/user/c_v_r/350x350", "https://source.unsplash.com/user/c_v_r/375x375","https://source.unsplash.com/user/c_v_r/400x400", "https://source.unsplash.com/user/c_v_r/425x425","https://source.unsplash.com/user/c_v_r/450x450"}
        };
        private int position;
        private final String[] usernameList;
        private final String[] ppUrlList;
        private final int[] counter;
        long pressTime = 0L;
        long limit = 500L;

        public MainAdapter(StoryPlayerActivity storyPlayerActivity, int position, String[] usernameList, String[] ppUrlList, int[] counter){
            this.activity=storyPlayerActivity;
            this.position=position;
            this.usernameList=usernameList;
            this.ppUrlList = ppUrlList;
            this.counter=counter;
        }
        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            System.out.println("this.position: "+this.position +" // position: "+ position);

            LayoutInflater inflater = (LayoutInflater) container.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = StoryBinding.inflate(inflater);
            view = binding.getRoot();

            CircleImageView profileImage = binding.profileImage;
            TextView usernameTV = binding.usernameTV;

            usernameTV.setText(usernameList[position]);
            Glide.with(view)
                    .load(ppUrlList[position])
                    .into(profileImage);
            if(this.position==position) {
                startStories();
            }
            vpage.setObjectForPosition(view, position);
            container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object obj) {
            container.removeView((View) obj);
        }
        @Override
        public int getCount() {
            return usernameList.length;
        }
        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void onNext() {
            if(ImageURls[position].length>counter[position]) {
                counter[position]++;
                glideImage(ImageURls[position][counter[position]]);
            }
        }
        @Override
        public void onPrev() {
            if ((counter[position] - 1) < 0) {
                if(position>0) {
                    position--;
                    vpage.setAdapter(vpageAdapter);
                    vpage.setCurrentItem(position);
                }
                return;
            }
            --counter[position];
            if(ImageURls[position].length>counter[position]) {
                glideImage(ImageURls[position][counter[position]]);
            }
        }
        @Override
        public void onComplete() {
            counter[position] = ImageURls[position].length-1;
            if(position<ImageURls.length-1){
                position++;
                vpage.setAdapter(vpageAdapter);
                vpage.setCurrentItem(position);
            }else{
                activity.finish();
            }

        }
        private void glideImage(String URL)
        {
            Glide.with(view)
                    .load(URL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Toast.makeText(StoryPlayerActivity.this, "Failed to load image.", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(image);
        }
        private void startStories(){
            System.out.println("start stories");
            storiesProgressView = binding.stories;
            storiesProgressView.setStoriesCount(ImageURls[position].length);
            storiesProgressView.setStoryDuration(5000);
            storiesProgressView.setStoriesListener(this);
            if(counter[position]<ImageURls[position].length) storiesProgressView.startStories(counter[position]);
            image = binding.image;

            glideImage(ImageURls[position][counter[position]]);

            View reverse = binding.reverse;
            reverse.setOnClickListener(v -> storiesProgressView.reverse());
            reverse.setOnTouchListener(onTouchListener);

            View skip = binding.skip;
            skip.setOnClickListener(v -> storiesProgressView.skip());
            skip.setOnTouchListener(onTouchListener);

        }
        private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        pressTime = System.currentTimeMillis();
                        storiesProgressView.pause();
                        return false;

                    case MotionEvent.ACTION_UP:
                        long now = System.currentTimeMillis();
                        storiesProgressView.resume();
                        return limit < now - pressTime;
                }
                return false;
            }
        };
    }

}