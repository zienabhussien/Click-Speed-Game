package com.example.clickspeedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import com.example.clickspeedgame.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
 ActivityResultBinding bind;
 Handler handler = new Handler();
SharedPreferences sharedPref;
SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.playAgainBtn.setEnabled(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bind.playAgainBtn.setEnabled(true);
            }
        };
        handler.postDelayed(runnable,2000);

        Intent i = getIntent();
        int scoreRes = i.getIntExtra("score",0);
        bind.scoreResult.setText(""+scoreRes);

        sharedPref = getSharedPreferences("PREF",MODE_PRIVATE);
        int bestScore = sharedPref.getInt("bestScore",0);

        if(scoreRes > bestScore){
            editor = sharedPref.edit();
            editor.putInt("bestScore",scoreRes);
            editor.apply();
            bind.bestScoreResult.setText(""+scoreRes);
        }else{
            bind.bestScoreResult.setText(""+bestScore);
        }

        bind.playAgainBtn.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this,MainActivity.class));
        });
    }
}