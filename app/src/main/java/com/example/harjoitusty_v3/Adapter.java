package com.example.harjoitusty_v3;

import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    Context constext;
    ArrayList<Movies> arrayList;
    public Adapter(ArrayList<Movies> arrayList, Context context){
        this.arrayList = arrayList;
        this.constext = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(constext).inflate(R.layout.listview_item, viewGroup, false);
        }
        Movies movie = (Movies) getItem(i);

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView date = (TextView) view.findViewById(R.id.date);

        name.setText(movie.getName());
        date.setText(movie.getDate());

        return view;
    }
}
