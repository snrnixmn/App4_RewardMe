package com.example.rewardme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class KidsAdapter extends ArrayAdapter<Kids> {
    Context context;
    ArrayList<Kids> kids;
    int resource;;
    TextView tvChildName, tvStarCount;
    ImageView ivAvatar;

    public KidsAdapter(Context context, int resource, ArrayList<Kids> notes) {
        super(context, resource, notes);
        this.context = context;
        this.kids = kids;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        tvChildName = (TextView) rowView.findViewById(R.id.tvChildName);
        tvStarCount = (TextView) rowView.findViewById(R.id.tvStarCount);
        ivAvatar = (ImageView) rowView.findViewById(R.id.ivAvatar);

        Kids kid = kids.get(position);

        tvChildName.setText(kid.getName());
        tvStarCount.setText(kid.getStarCount());
        // ivAvatar.setImageResource();

        return rowView;
    }



}

