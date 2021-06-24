package com.example.clickspeedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.clickspeedgame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
 ActivityMainBinding bind;
 private int count = 0;
 CountDownTimer countDownTimer;
 private long timeMilliSeconds = 10000 ; // 10 sec
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.timeTV.setText("10 sec");
        bind.scoreTV.setText("0");
        bind.field.setEnabled(false);

       bind.playBtn.setOnClickListener(v -> {
           play();
       });

       bind.field.setOnClickListener(v -> {
           count++;
           bind.scoreTV.setText(""+count);
       });

    }

    private void play() {
        bind.playBtn.setVisibility(View.GONE);
        bind.field.setEnabled(true);
        count = 0;
        startTime();
    }

   private void startTime(){
      countDownTimer = new CountDownTimer(timeMilliSeconds,1000) {
          @Override
          public void onTick(long millisUntilFinished) {
              timeMilliSeconds = millisUntilFinished;
              update();
          }

          @Override
          public void onFinish() {
              Intent i = new Intent(MainActivity.this,ResultActivity.class);
              i.putExtra("score",count);
              startActivity(i);
          }
      }.start();
   }

    private void update() {
        int seconds = (int) timeMilliSeconds/1000;
        bind.timeTV.setText(""+seconds+" sec");
    }


}