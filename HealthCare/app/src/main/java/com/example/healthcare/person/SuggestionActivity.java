package com.example.healthcare.person;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.healthcare.R;

public class SuggestionActivity extends AppCompatActivity {

    private LinearLayout returnback;
    private EditText editrect;
    private Button poston;
    SuggestionClickListener suggestionClickListener = new SuggestionClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        initView();
    }
    private void initView(){
        returnback = findViewById(R.id.suggest_returnlayout);
        poston = findViewById(R.id.suggest_poston);
        editrect = findViewById(R.id.suggest_editrect);

        returnback.setOnClickListener(suggestionClickListener);
        poston.setOnClickListener(suggestionClickListener);
    }
    private class SuggestionClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.suggest_returnlayout:
                    Log.d("Suggestion","Click return image");
                    finish();
                    break;
                case R.id.suggest_poston:
                    Log.d("Suggestion","Click post on Button");
                    Toast.makeText(getApplicationContext(), "提交成功！", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Log.d("Suggstion","Null click");
                    break;


            }
        }
    }
}