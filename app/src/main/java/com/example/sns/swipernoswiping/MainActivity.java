package com.example.sns.swipernoswiping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SwipeDeck cardStack;

    //TODO: Backend calculations
    private String firstPrompt  = null;
    private String secondPrompt = null;
    private String thirdPrompt = null;
    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.swipe_view);
        cardStack = findViewById(R.id.swipe_deck);

        final ArrayList<String> testData = new ArrayList<>();
        final ArrayList<Integer> imageId1 = new ArrayList<>();
        final ArrayList<Integer> imageId2 = new ArrayList<>();


        //TODO: Change the "Prompt X" to header we want
        testData.add("How do you shop?");

        //TODO: Turn this on when you have image for the prompts, and change respective image left image and right image name
        imageId1.add(R.drawable.br11);
        imageId2.add(R.drawable.qs11);

        testData.add("Prompt 2");

        //TODO: Turn this on when you have image for the prompts, and change respective image left image and right image name
        imageId1.add(R.drawable.br11);
        imageId2.add(R.drawable.qs11);
        testData.add("Prompt 3");

        //TODO: Turn this on when you have image for the prompts, and change respective image left image and right image name
        imageId1.add(R.drawable.br11);
        imageId2.add(R.drawable.qs11);

        testData.add("Prompt 4");

        //TODO: Turn this on when you have image for the prompts, and change respective image left image and right image name
        imageId1.add(R.drawable.br11);
        imageId2.add(R.drawable.qs11);

        testData.add("Prompt 5");

        //TODO: Turn this on when you have image for the prompts, and change respective image left image and right image name
        imageId1.add(R.drawable.br11);
        imageId2.add(R.drawable.qs11);

        testData.add("Prompt 6");

        //TODO: Turn this on when you have image for the prompts, and change respective image left image and right image name
        imageId1.add(R.drawable.br11);
        imageId2.add(R.drawable.qs11);

        testData.add("Prompt 7");

        //TODO: Turn this on when you have image for the prompts, and change respective image left image and right image name
        imageId1.add(R.drawable.br11);
        imageId2.add(R.drawable.qs11);

        testData.add("Prompt 8");

        //TODO: Turn this on when you have image for the prompts, and change respective image left image and right image name
        imageId1.add(R.drawable.br11);
        imageId2.add(R.drawable.qs11);



        final SwipeDeckAdapter adapter = new SwipeDeckAdapter(testData, imageId1, imageId2,this);
        cardStack.setAdapter(adapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + position);

                //TODO: points system
                if(position == 0){
                    firstPrompt = "left";
                }
                if(position == 1){
                    secondPrompt = "left";
                }
                if(position == 2){
                    thirdPrompt = "left";
                }
                if(position == 3){
                    points += 0;
                }
                if(position == 4){
                    points += 0;
                }
                if(position == 5){
                    points += 0;
                }
                if(position == 6){
                    points += 0;
                }
                if(position == 7){
                    points += 0;
                }
                if(position == 8){
                    points += 0;
                }

            }

            @Override
            public void cardSwipedRight(int position) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + position);

                //TODO: points system
                if(position == 0){
                    firstPrompt = "right";
                }
                if(position == 1){
                    secondPrompt = "right";
                }
                if(position == 2){
                    thirdPrompt = "right";
                }
                if(position == 3){
                    points += 0;
                }
                if(position == 4){
                    points += 0;
                }
                if(position == 5){
                    points += 0;
                }
                if(position == 6){
                    points += 0;
                }
                if(position == 7){
                    points += 0;
                }
                if(position == 8){
                    points += 0;
                }
            }

            @Override
            public void cardsDepleted() {
                Log.i("MainActivity", "no more cards");

                //TODO: Backend calculation
                String typeOfPerson = "";
                if(firstPrompt.equals("left")){
                    typeOfPerson = "something";
                }else{

                }
                if(secondPrompt.equals("left")){

                }else{

                }
                if(thirdPrompt.equals("left")){

                }else{

                }
                if(points < 10){

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
        });
    }


}
