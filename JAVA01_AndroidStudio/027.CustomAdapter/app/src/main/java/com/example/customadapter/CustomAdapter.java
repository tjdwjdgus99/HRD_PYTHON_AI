package com.example.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<Person> listdata;
    private Context maincon;
    private  int layout;
    private LayoutInflater inflator;

    public CustomAdapter(Context context, int layout, ArrayList<Person> listdata) {
        this.maincon = context;
        inflator = (LayoutInflater) maincon.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        this.listdata = listdata;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return listdata.get(i).name;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = inflator.inflate(layout, viewGroup, false);
        }

        return null;
    }
}
