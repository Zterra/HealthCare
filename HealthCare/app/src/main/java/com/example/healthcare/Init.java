package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class Init extends AppCompatActivity {
    ImageView title,bg,logo;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        title=findViewById(R.id.init_title);
        bg=findViewById(R.id.init_bg);
        lottieAnimationView=findViewById(R.id.init_lottie);
        logo=findViewById(R.id.init_logo);

        bg.animate().translationY(-2000).setDuration(1000).setStartDelay(4000);
        title.animate().translationY(1600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(4000);

    }
//    private class ScreenSlidePagerAdapters {
//        public ScreenSlidePagerAdapters(FragmentManager fm, int behavior){
//            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        }
//
//    }
}