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

        testData.add(getResources().getString(R.string.question11));
        imageId1.add(R.drawable.br11);
        imageId2.add(R.drawable.qs11);

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
                    testData.add(1,getResources().getString(R.string.question21));
                    imageId1.add(1,R.drawable.br21);
                    imageId2.add(1,R.drawable.cs21);
                    adapter.notifyDataSetChanged();

                    Log.i("Norman","position" + position + "left");
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

                    testData.add(1, getResources().getString(R.string.question31));
                    imageId1.add(1,R.drawable.qs31);
                    imageId2.add(1,R.drawable.cs31);
                    adapter.notifyDataSetChanged();

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


}
