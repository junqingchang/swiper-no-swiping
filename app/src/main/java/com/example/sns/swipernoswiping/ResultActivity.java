package com.example.sns.swipernoswiping;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    Button restartBtn;
    TextView archetypeTextView;
    TextView youAreTextView;
    TextView descriptionTextView;
    TextView superArchetypeTextView;
    TextView mapEndTextView;
    ImageView mapNameImageView;
    TextView mapNamePleasePickUp;
    Handler handler;
    Runnable runnable;
    int delayMilliseconds;
    String superArchetype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Force Landscape mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_result);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                Toast.makeText(ResultActivity.this, getResources().getString(R.string.inactivity_message),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResultActivity.this, SimpleViewFlipActivity.class);
                startActivity(intent);

            }
        };

        delayMilliseconds = getResources().getInteger(R.integer.inactivity_timeout);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        archetypeTextView = findViewById(R.id.resultsTextView);
        superArchetypeTextView = findViewById(R.id.resultsSuperarchetypeTextView);

        youAreTextView = findViewById(R.id.resultsYouAre);
        descriptionTextView = findViewById(R.id.resultsDescription);
        mapEndTextView = findViewById(R.id.resultsMapEnd);
        mapNameImageView = findViewById(R.id.resultsMapNameImage);
        mapNamePleasePickUp = findViewById( R.id.resultsPleaseTake);
        boolean isOA, isPE, isHS, isDD, isSS, isQD;


        if (b!=null){
            String type = (String) b.get("type");
            archetypeTextView.setText(type + "!");

            isOA = type.equals(getResources().getString(R.string.archetype_OA));
            isPE = type.equals(getResources().getString(R.string.archetype_PE));
            isSS = type.equals(getResources().getString(R.string.archetype_SS));
            isQD = type.equals(getResources().getString(R.string.archetype_QD));
            isHS = type.equals(getResources().getString(R.string.archetype_HS));
            isDD = type.equals(getResources().getString(R.string.archetype_DD));

            //set You are a vs You are an
            if( isOA){
                youAreTextView.setText(getResources().getString(R.string.resultsYouAreAn));
            }else{
                youAreTextView.setText(getResources().getString(R.string.resultsYouAreA));
            }

            //now the description
            if(isOA) descriptionTextView.setText(getResources().getString(R.string.description_OA));

            if(isPE) descriptionTextView.setText(getResources().getString(R.string.description_PE));

            if(isSS) descriptionTextView.setText(getResources().getString(R.string.description_SS));

            if(isQD) descriptionTextView.setText(getResources().getString(R.string.description_QD));

            if(isHS) descriptionTextView.setText(getResources().getString(R.string.description_HS));

            if(isDD) descriptionTextView.setText(getResources().getString(R.string.description_DD));

            if(isOA || isPE) {
                superArchetype = getResources().getString(R.string.super_archetype_OA_PE);
                mapNameImageView.setImageResource(R.drawable.superarchetype_thrillseeker);
                mapNamePleasePickUp.setTextColor(getResources().getColor(R.color.colorBR));
                mapEndTextView.setTextColor(getResources().getColor(R.color.colorBR));

            }

            if(isDD || isHS){
                superArchetype = getResources().getString(R.string.super_archetype_DD_HS);
                mapNameImageView.setImageResource(R.drawable.superarchetype_speedyshopper);
                mapNamePleasePickUp.setTextColor(getResources().getColor(R.color.colorQS));
                mapEndTextView.setTextColor(getResources().getColor(R.color.colorQS));



            }

            if(isSS || isQD){
                superArchetype = getResources().getString(R.string.super_archetype_SS_QD);
                mapNameImageView.setImageResource(R.drawable.superarchetype_carefulshopper);
                mapNamePleasePickUp.setTextColor(getResources().getColor(R.color.colorCS));
                mapEndTextView.setTextColor(getResources().getColor(R.color.colorCS));

            }

            superArchetypeTextView.setText(superArchetype);
            //mapNameTextView.setText(superArchetype);

        }
        restartBtn = findViewById(R.id.restartBtn);

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onUserInteraction() {
        // TODO Auto-generated method stub
        super.onUserInteraction();
        stopHandler();//stop first and then start
        startHandler();
    }
    public void stopHandler() {
        handler.removeCallbacks(runnable);
    }
    public void startHandler() {
        handler.postDelayed(runnable, delayMilliseconds); //for 5 minutes
    }
}
