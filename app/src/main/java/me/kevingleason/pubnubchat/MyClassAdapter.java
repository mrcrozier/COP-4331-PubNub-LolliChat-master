package me.kevingleason.pubnubchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


// Used this tutorial: https://devtut.wordpress.com/2011/06/09/custom-arrayadapter-for-a-listview-android/
public class MyClassAdapter extends ArrayAdapter<Chatroom> {
    private ArrayList<Chatroom> objects;

    public MyClassAdapter(Context context, int textViewResourceId, ArrayList<Chatroom> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }

        Chatroom i = objects.get(position);

        if (i != null) {
            TextView name = (TextView) v.findViewById(R.id.chatroomName);
            TextView distance = (TextView) v.findViewById(R.id.chatroomDistance);

            if (name != null) {
                name.setText(i.name);
            }
            if (distance != null) {
                distance.setText(String.format("%.2f", i.distanceTo) + " mi");
            }
        }

        return v;
    }
}
