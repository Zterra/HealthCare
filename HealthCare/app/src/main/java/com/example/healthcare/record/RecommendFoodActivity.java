package com.example.healthcare.record;

import androidx.appcompat.app.AppCompatActivity;
import com.example.healthcare.R;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RecommendFoodActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_food);
        LinearLayout return_btn = findViewById(R.id.recommend_return);
        return_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recommend_return:
                finish();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}