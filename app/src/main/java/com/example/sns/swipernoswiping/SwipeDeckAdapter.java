package com.example.sns.swipernoswiping;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SwipeDeckAdapter extends BaseAdapter {

    private List<String> data;
    private List<Integer> imageData1;
    private List<Integer> imageData2;
    private Context context;

    public SwipeDeckAdapter(List<String> data, List<Integer> imageData1, List<Integer> imageData2, Context context) {
        this.data = data;
        this.imageData1 = imageData1;
        this.imageData2 = imageData2;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            // normally use a viewholder
            v = inflater.inflate(R.layout.activity_main, parent, false);
        }
        ((TextView) v.findViewById(R.id.textView2)).setText(data.get(position));

        //TODO: turn this on when you have image for the prompts
        Log.i("Norman","Trying to set images");
        ((ImageView) v.findViewById(R.id.oldprint1)).setImageResource(imageData1.get(position));
        ((ImageView) v.findViewById(R.id.oldprint2)).setImageResource(imageData2.get(position));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = (String)getItem(position);
                Log.i("MainActivity", item);
            }
        });

        return v;
    }
}