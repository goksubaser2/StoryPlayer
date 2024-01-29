package com.example.storyplayer2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

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

//TODO
public class StoryPlayerActivity extends AppCompatActivity {
    private JazzyViewPager vpage;
//    private int position;
//    private String[] usernameList;
//    private String[] ppUrlList;
//    private final String[] ImageURls = {"https://source.unsplash.com/user/c_v_r/135x240","https://source.unsplash.com/user/c_v_r/150x150"}; //TODO
//
//    long pressTime = 0L;
//    long limit = 500L;
//
//    private StoriesProgressView storiesProgressView;
//    private ImageView image;
//
//    private CircleImageView profileImage;
//    private TextView usernameTV;
//
//    private int counter = 0;

//    private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            switch (motionEvent.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//
//                    // on action down when we press our screen
//                    // the story will pause for specific time.
//                    pressTime = System.currentTimeMillis();
//
//                    // on below line we are pausing our indicator.
//                    storiesProgressView.pause();
//                    return false;
//                case MotionEvent.ACTION_UP:
//
//                    // in action up case when user do not touches
//                    // screen this method will skip to next image.
//                    long now = System.currentTimeMillis();
//
//                    // on below line we are resuming our progress bar for status.
//                    storiesProgressView.resume();
//
//                    // on below line we are returning if the limit < now - presstime
//                    return limit < now - pressTime;
//            }
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_story_player);

//        storiesProgressView = (StoriesProgressView) findViewById(R.id.stories);
//        storiesProgressView.setStoriesCount(ImageURls.length);
//        storiesProgressView.setStoryDuration(3000L);
//        storiesProgressView.setStoriesListener(this);
//        storiesProgressView.startStories(counter);
//        image = (ImageView) findViewById(R.id.image);

//        profileImage = findViewById(R.id.profile_image);
//        usernameTV = findViewById(R.id.usernameTV);

//        this.position = intent.getIntExtra("position",1);
//        this.usernameList = Objects.requireNonNull(intent.getStringArrayExtra("usernameList"));
//        this.ppUrlList = Objects.requireNonNull(intent.getStringArrayExtra("ppUrlList"));

//        glideImage(ImageURls[counter]);
//
//        // below is the view for going to the previous story.
//        // initializing our previous view.
//        View reverse = findViewById(R.id.reverse);
//
//        // adding on click listener for our reverse view.
//        reverse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // inside on click we are
//                // reversing our progress view.
//                storiesProgressView.reverse();
//            }
//        });
//
//        // on below line we are calling a set on touch
//        // listener method to move towards previous image.
//        reverse.setOnTouchListener(onTouchListener);
//
//        // on below line we are initializing
//        // view to skip a specific story.
//        View skip = findViewById(R.id.skip);
//        skip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // inside on click we are
//                // skipping the story progress view.
//                storiesProgressView.skip();
//            }
//        });
//        // on below line we are calling a set on touch
//        // listener method to move to next story.
//        skip.setOnTouchListener(onTouchListener);

        vpage = (JazzyViewPager) findViewById(R.id.jazzy_pager);
        vpage.setTransitionEffect(TransitionEffect.CubeOut);
        Intent intent = getIntent();
        vpage.setAdapter(new MainAdapter(
                Objects.requireNonNull(intent.getStringArrayExtra("usernameList")),
                Objects.requireNonNull(intent.getStringArrayExtra("ppUrlList")))
        );
        vpage.setCurrentItem(intent.getIntExtra("position",0));
    }

//    @Override
//    public void onNext() {
//        // this method is called when we move
//        // to next progress view of story.
//        glideImage(ImageURls[++counter]);
//    }

//    @Override
//    public void onPrev() {
//
//        // this method id called when we move to previous story.
//        // on below line we are decreasing our counter
//        if ((counter - 1) < 0) return;
//        glideImage(ImageURls[--counter]);
//
//        // on below line we are setting image to image view
//    }

//    @Override
//    public void onComplete() {//TODO
//        Intent i = new Intent(StoryPlayerActivity.this, StoryPlayerActivity.class);
//
//        i.putExtra("usernameList",usernameList);
//        i.putExtra("ppUrlList",ppUrlList);
//        i.putExtra("position",position+1);
//        startActivity(i);
//        finish();
//
//    }

//    @Override
//    protected void onDestroy() {
//        // in on destroy method we are destroying
//        // our stories progress view.
//        storiesProgressView.destroy();
//        super.onDestroy();
//    }

//    private void glideImage(String URL)//TODO cubic
//    {
//        Glide.with(this)
//                .load(URL)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        Toast.makeText(StoryPlayerActivity.this, "Failed to load image.", Toast.LENGTH_SHORT).show();
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
//                .into(image);
//
//        usernameTV.setText(usernameList[position]);
//        Glide.with(this)
//                .load(ppUrlList[position])
//                .into(profileImage);
//    }

    private class MainAdapter extends PagerAdapter implements StoriesProgressView.StoriesListener {
        StoryBinding binding;
        private StoriesProgressView storiesProgressView;
        private ImageView image;
        private final String[][] ImageURls = {{"https://source.unsplash.com/user/c_v_r/100x100"},
                {"https://source.unsplash.com/user/c_v_r/125x125", "https://source.unsplash.com/user/c_v_r/150x150"},
                {"https://source.unsplash.com/user/c_v_r/175x175", "https://source.unsplash.com/user/c_v_r/200x200", "https://source.unsplash.com/user/c_v_r/225x225"},
                {"https://source.unsplash.com/user/c_v_r/250x250", "https://source.unsplash.com/user/c_v_r/275x275","https://source.unsplash.com/user/c_v_r/300x300", "https://source.unsplash.com/user/c_v_r/325x325"},
                {"https://source.unsplash.com/user/c_v_r/350x350", "https://source.unsplash.com/user/c_v_r/375x375","https://source.unsplash.com/user/c_v_r/400x400", "https://source.unsplash.com/user/c_v_r/425x425","https://source.unsplash.com/user/c_v_r/450x450"}
        }; //TODO
        private int counter = 0;
        private CircleImageView profileImage;
        private TextView usernameTV;
        private String[] usernameList;
        private String[] ppUrlList;

        public MainAdapter(String[] usernameList,String[] ppUrlList){
            this.usernameList=usernameList;
            this.ppUrlList = ppUrlList;
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            LayoutInflater inflater = (LayoutInflater) container.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = StoryBinding.inflate(inflater);
            View view = binding.getRoot();

            //TODO counter should start at unwatched story

            storiesProgressView = binding.stories;
            storiesProgressView.setStoriesCount(ImageURls[position].length);
            storiesProgressView.setStoryDuration(5000);
            storiesProgressView.setStoriesListener(this);
            storiesProgressView.startStories(counter);
            image = binding.image;

            profileImage = binding.profileImage;
            usernameTV = binding.usernameTV;

            usernameTV.setText(usernameList[position]);
            Glide.with(binding.getRoot())
                    .load(ppUrlList[position])
                    .into(profileImage);

            glideImage(ImageURls[position][counter]);

            container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            vpage.setObjectForPosition(view, position);

            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            container.removeView((View) obj);
        }
        @Override
        public int getCount() {
            return usernameList.length;
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void onNext() {//TODO
            System.out.println("next");
        }

        @Override
        public void onPrev() {//TODO
            System.out.println("prev");
        }

        @Override
        public void onComplete() {//TODO
            System.out.println("complete");

        }
        private void glideImage(String URL)
        {
            Glide.with(binding.getRoot())
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
    }

}