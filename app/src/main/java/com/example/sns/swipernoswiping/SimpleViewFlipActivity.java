package com.example.sns.swipernoswiping;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.lang.System;

import com.wajahatkarim3.easyflipview.EasyFlipView;

/**
 * Created by Sachin Varma on 18-12-2017.
 */

public class SimpleViewFlipActivity extends AppCompatActivity {


    Handler handler;
    Runnable runnable;

    int delayMilliSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_view);

        //Force Landscape mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        delayMilliSeconds = getResources().getInteger(R.integer.inactivity_timeout);

        final EasyFlipView easyFlipView = (EasyFlipView) findViewById(R.id.easyFlipView2);
        easyFlipView.setFlipDuration(1000);
        easyFlipView.setFlipEnabled(true);

        LinearLayout frontcardLinearLayout;
        CardView frontcardCardView;
        frontcardLinearLayout = findViewById(R.id.frontcardBase);

        frontcardLinearLayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        easyFlipView.flipTheView();

                    }
                }
        );

        /* ImageView sellingDreamsLogo = findViewById(R.id.frontcardSellingDreamsLogo);

        sellingDreamsLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                easyFlipView.flipTheView();
                }
            }

        ); */


        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {


                if( easyFlipView.isBackSide() ){
                    //Toast.makeText(SimpleViewFlipActivity.this,"" + getResources().getInteger(R.integer.inactivity_timeout),Toast.LENGTH_SHORT).show();
                    Toast.makeText(SimpleViewFlipActivity.this,getResources().getString(R.string.inactivity_message),Toast.LENGTH_SHORT).show();

                    easyFlipView.flipTheView();
                }
            }
        };

        findViewById(R.id.cardbackBtn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//          Toast.makeText(SimpleViewFlipActivity.this, "Front Card", Toast.LENGTH_SHORT).show();
            easyFlipView.flipTheView();

        }
        });

        findViewById(R.id.cardbackGoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(SimpleViewFlipActivity.this, MainActivity.class);
            startActivity(intent);



            }
        });

        /* findViewById(R.id.frontcardGoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //        Toast.makeText(SimpleViewFlipActivity.this, "Back Card", Toast.LENGTH_SHORT).show();
            easyFlipView.flipTheView();
            }
        }); */

        easyFlipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView easyView, EasyFlipView.FlipState newCurrentSide) {
                //Toast.makeText(SimpleViewFlipActivity.this,
                //"Flip Completed! New Side is: " + newCurrentSide, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onUserInteraction() {
        // TODO Auto-generated method stub
        super.onUserInteraction();
        stopHandler();//stop first and then start
        startHandler();
        Log.i("LOGCAT","On User Interaction " + SimpleViewFlipActivity.class.getSimpleName());

    }

    @Override
    protected void onPause(){
        super.onPause();
        stopHandler();
    }

    public void stopHandler() {

        handler.removeCallbacks(runnable);
        Log.i("LOGCAT","Handler Stopped" + SimpleViewFlipActivity.class.getSimpleName());
    }
    public void startHandler() {
        handler.postDelayed(runnable, delayMilliSeconds); //for 5 minutes
        Log.i("LOGCAT","HandlerStarted " + SimpleViewFlipActivity.class.getSimpleName());
    }
}