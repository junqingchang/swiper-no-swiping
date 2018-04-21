package com.example.sns.swipernoswiping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sachin Varma on 18-12-2017.
 */

public class RecyclerViewFlipActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recyclerview_flip);
    List<TestModel> list = new ArrayList<>();
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    for (int i = 0; i < 20; i++) {
      TestModel model = new TestModel();
      model.isFlipped = false;
      list.add(model);
    }
    recyclerView.setAdapter(new SampleAdapter(list));
  }
}
