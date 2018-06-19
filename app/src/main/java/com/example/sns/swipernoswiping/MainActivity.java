package com.example.sns.swipernoswiping;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    SwipeDeck cardStack;

    final private String stateBrowser = "br";
    final private String stateQuickShopper = "qs";
    final private String stateCarefulShopper = "cs";

    int ss = 0;
    int qd = 0;
    int pe = 0;
    int oa = 0;
    int hs = 0;
    int dd = 0;
    private String state = null;

    final ArrayList<Integer> Question1_question = new ArrayList<>();
    final ArrayList<Integer> Question2_question = new ArrayList<>();
    final ArrayList<Integer> Question3_question = new ArrayList<>();

    final ArrayList<Integer> Question1_imageLeft = new ArrayList<>();
    final ArrayList<Integer> Question2_imageLeft = new ArrayList<>();
    final ArrayList<Integer> Question3_imageLeft = new ArrayList<>();

    final ArrayList<Integer> Question1_imageRight = new ArrayList<>();
    final ArrayList<Integer> Question2_imageRight = new ArrayList<>();
    final ArrayList<Integer> Question3_imageRight = new ArrayList<>();

    final ArrayList<ArrayList<Integer>> Questions1to3_question
            = new ArrayList<ArrayList<Integer>>();

    Handler handler;
    Runnable runnable;
    int delayMilliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Force Landscape mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.swipe_view);
        cardStack = findViewById(R.id.swipe_deck);

        final ArrayList<String> testData = new ArrayList<>();
        final ArrayList<Integer> imageId1 = new ArrayList<>();
        final ArrayList<Integer> imageId2 = new ArrayList<>();

        delayMilliseconds = getResources().getInteger(R.integer.inactivity_timeout);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                Toast.makeText(MainActivity.this,getResources().getString(R.string.inactivity_message),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,SimpleViewFlipActivity.class);
                startActivity(intent);
            }
        };

        populateQuestion1to3();

        Random randomIndex = new Random();

        int arrayListIndex = randomIndex.nextInt(Question1_question.size());

        //testData.add(getResources().getString(R.string.question11));
        //imageId1.add(R.drawable.br11);
        //imageId2.add(R.drawable.qs11);
        Log.i("Norman", "index " + arrayListIndex);

        testData.add(getResources().getString(Question1_question.get(arrayListIndex)));
        imageId1.add(Question1_imageLeft.get(arrayListIndex));
        imageId2.add(Question1_imageRight.get(arrayListIndex));

        //TODO do the random selection





        final SwipeDeckAdapter adapter = new SwipeDeckAdapter(testData, imageId1, imageId2,this);
        cardStack.setAdapter(adapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + position);

                //TODO: points system
                if(position == 0){

                    state = stateBrowser;

                    Random randomIndex = new Random();
                    int arrayListIndex = randomIndex.nextInt(Question2_question.size());
                    testData.add(1, getResources().getString(Question2_question.get(arrayListIndex)));
                    imageId1.add(1,Question2_imageLeft.get(arrayListIndex));
                    imageId2.add(1, Question2_imageRight.get(arrayListIndex));

                    Log.i("Norman","position" + position + "left" + arrayListIndex);
                }
                if(position == 1){

                    if(state.equals(stateBrowser)){
                        testData.add(getResources().getString(R.string.question61));
                        imageId1.add(R.drawable.oa1);
                        imageId2.add(R.drawable.pe1);
                        testData.add(getResources().getString(R.string.question62));
                        imageId1.add(R.drawable.oa2);
                        imageId2.add(R.drawable.pe2);
                        testData.add(getResources().getString(R.string.question63));
                        imageId1.add(R.drawable.oa3);
                        imageId2.add(R.drawable.pe3);
                    }

                    if(state.equals(stateQuickShopper)){
                        testData.add(getResources().getString(R.string.question51));
                        imageId1.add(R.drawable.hs1);
                        imageId2.add(R.drawable.dd1);
                        testData.add(getResources().getString(R.string.question52));
                        imageId1.add(R.drawable.hs2);
                        imageId2.add(R.drawable.dd2);
                        testData.add(getResources().getString(R.string.question53));
                        imageId1.add(R.drawable.hs3);
                        imageId2.add(R.drawable.dd3);
                    }

                }
                if(position >= 2 && position <= 4){
                    incrementLeftSwipe();
                }


            }

            @Override
            public void cardSwipedRight(int position) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + position);

                //TODO: points system
                if(position == 0){
                    Log.i("Norman","position" + position + "right");

                    Random randomIndex = new Random();
                    int arrayListIndex = randomIndex.nextInt(Question3_question.size());
                    testData.add(1, getResources().getString(Question3_question.get(arrayListIndex)));
                    imageId1.add(1,Question3_imageLeft.get(arrayListIndex));
                    imageId2.add(1, Question3_imageRight.get(arrayListIndex));

                    state = stateQuickShopper;
                }
                if(position == 1){
                    if(state.equals(stateBrowser)){
                        state = stateCarefulShopper;
                        testData.add(getResources().getString(R.string.question41));
                        imageId1.add(R.drawable.ss1);
                        imageId2.add(R.drawable.qd1);
                        testData.add(getResources().getString(R.string.question42));
                        imageId1.add(R.drawable.ss2);
                        imageId2.add(R.drawable.qd2);
                        testData.add(getResources().getString(R.string.question43));
                        imageId1.add(R.drawable.ss3);
                        imageId2.add(R.drawable.qd3);
                    }

                    if(state.equals(stateQuickShopper)){
                        state = stateCarefulShopper;
                        testData.add(getResources().getString(R.string.question41));
                        imageId1.add(R.drawable.ss1);
                        imageId2.add(R.drawable.qd1);
                        testData.add(getResources().getString(R.string.question42));
                        imageId1.add(R.drawable.ss2);
                        imageId2.add(R.drawable.qd2);
                        testData.add(getResources().getString(R.string.question43));
                        imageId1.add(R.drawable.ss3);
                        imageId2.add(R.drawable.qd3);
                    }
                }
                if(position >= 2 && position <= 4){
                    incrementRightSwipe();
                }

            }

            @Override
            public void cardsDepleted() {
                Log.i("MainActivity", "no more cards");

                String typeOfPerson = "";

                if(state.equals(stateBrowser)){

                    if(oa > pe) {
                        typeOfPerson = getResources().getString(R.string.archetype_OA);
                    }else{
                        typeOfPerson = getResources().getString(R.string.archetype_PE);
                    }

                    Log.i("Norman",state + " " + oa + " " + pe + " " + typeOfPerson);
                }

                if(state.equals(stateCarefulShopper)){

                    if(ss > qd) {
                        typeOfPerson = getResources().getString(R.string.archetype_SS);
                    }else{
                        typeOfPerson = getResources().getString(R.string.archetype_QD);
                    }

                    Log.i("Norman",state + " " + ss + " " + qd + " " + typeOfPerson);
                }

                if(state.equals(stateQuickShopper)){

                    if(hs > dd) {
                        typeOfPerson = getResources().getString(R.string.archetype_HS);
                    }else{
                        typeOfPerson = getResources().getString(R.string.archetype_DD);
                    }

                    Log.i("Norman",state + " " + hs + " " + dd + " " + typeOfPerson);
                }

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("type", typeOfPerson);
                startActivity(intent);
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }

            public void incrementLeftSwipe(){

                if(state.equals(stateBrowser)){
                    oa += 1;
                }
                if(state.equals(stateCarefulShopper)){
                    ss += 1;
                }
                if(state.equals(stateQuickShopper)){
                    hs += 1;
                }

            }

            public void incrementRightSwipe(){

                if(state.equals(stateBrowser)){
                    pe += 1;
                }
                if(state.equals(stateCarefulShopper)){
                    qd += 1;
                }
                if(state.equals(stateQuickShopper)){
                    dd += 1;
                }

            }


        });




    }

    public void populateQuestion1to3(){

        Integer [] q1strings = new Integer[] {
                R.string.question11,
                R.string.question12,
                R.string.question13};

        Question1_question.addAll(Arrays.asList(q1strings));

        Integer [] q2strings = new Integer[] {
                R.string.question21,
                R.string.question22,
                R.string.question23};

        Question2_question.addAll(Arrays.asList(q2strings));

        Integer [] q3strings = new Integer[] {
                R.string.question31,
                R.string.question32,
                R.string.question33};

        Question3_question.addAll(Arrays.asList(q3strings));

        Integer [] q1imageLeft = new Integer[] {
                R.drawable.br11,
                R.drawable.br12,
                R.drawable.br13};

        Question1_imageLeft.addAll(Arrays.asList(q1imageLeft));

        Integer [] q2imageLeft = new Integer[] {
                R.drawable.br21,
                R.drawable.br22,
                R.drawable.br23};

        Question2_imageLeft.addAll(Arrays.asList(q2imageLeft));

        Integer [] q3imageLeft = new Integer[] {
                R.drawable.qs31,
                R.drawable.qs32,
                R.drawable.qs33};

        Question3_imageLeft.addAll(Arrays.asList(q3imageLeft));

        Integer [] q1imageRight = new Integer[] {
                R.drawable.qs11,
                R.drawable.qs12,
                R.drawable.qs13};

        Question1_imageRight.addAll(Arrays.asList(q1imageRight));

        Integer [] q2imageRight = new Integer[] {
                R.drawable.cs21,
                R.drawable.cs22,
                R.drawable.cs23};

        Question2_imageRight.addAll(Arrays.asList(q2imageRight));

        Integer [] q3imageRight = new Integer[] {
                R.drawable.cs31,
                R.drawable.cs32,
                R.drawable.cs33};

        Question3_imageRight.addAll(Arrays.asList(q3imageRight));

        Questions1to3_question.add(Question1_question);
        Questions1to3_question.add(Question2_question);
        Questions1to3_question.add(Question3_question);

        for (ArrayList<Integer> al: Questions1to3_question){

            for(Integer rid: al){
                Log.i("Norman", getResources().getString(rid));
            }
        }




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
