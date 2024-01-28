package com.example.storyplayer2;

import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import com.hisham.jazzyviewpagerlib.JazzyViewPager;
import com.hisham.jazzyviewpagerlib.JazzyViewPager.TransitionEffect;

//TODO
public class StoryPlayerActivity extends AppCompatActivity {
//    private JazzyViewPager vpage;
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

//        // inside in create method below line is use to make a full screen.
//
//        // on below line we are initializing our variables.
//        storiesProgressView = (StoriesProgressView) findViewById(R.id.stories);
//
//        // on below line we are setting the total count for our stories.
//        storiesProgressView.setStoriesCount(ImageURls.length);
//
//        // on below line we are setting story duration for each story.
//        storiesProgressView.setStoryDuration(3000L);
//
//        // on below line we are calling a method for set
//        // on story listener and passing context to it.
//        storiesProgressView.setStoriesListener(this);
//
//        // below line is use to start stories progress bar.
//        storiesProgressView.startStories(counter);
//
//        // initializing our image view.
//        image = (ImageView) findViewById(R.id.image);
//
//        profileImage = findViewById(R.id.profile_image);
//        usernameTV = findViewById(R.id.usernameTV);
//
//        Intent intent = getIntent();
//        this.position = intent.getIntExtra("position",1);
//        this.usernameList = Objects.requireNonNull(intent.getStringArrayExtra("usernameList"));
//        this.ppUrlList = Objects.requireNonNull(intent.getStringArrayExtra("ppUrlList"));
//
//        // on below line we are setting image to our image view.
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
//
//        vpage = (JazzyViewPager) findViewById(R.id.jazzy_pager);
//        vpage.setTransitionEffect(TransitionEffect.CubeOut);
//        vpage.setAdapter(new MainAdapter());
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

//    private class MainAdapter extends PagerAdapter {
//        @Override
//        public Object instantiateItem(ViewGroup container, final int position) {
//            TextView text = new TextView(StoryPlayerActivity.this);
//            text.setGravity(Gravity.CENTER);
//            text.setTextSize(30);
//            text.setTextColor(Color.WHITE);
//            text.setText("Page " + position);
//            text.setPadding(30, 30, 30, 30);
//            int bg = Color.rgb((int) Math.floor(Math.random()*128)+64,
//                    (int) Math.floor(Math.random()*128)+64,
//                    (int) Math.floor(Math.random()*128)+64);
//            text.setBackgroundColor(bg);
//            container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            vpage.setObjectForPosition(text, position);
//            return text;
//        }
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object obj) {
//            container.removeView((View) obj);
//        }
//        @Override
//        public int getCount() {
//            return 10;
//        }
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {
//            return arg0 == arg1;
//        }
//    }

}