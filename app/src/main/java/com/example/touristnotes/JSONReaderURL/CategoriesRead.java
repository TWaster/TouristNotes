package com.example.touristnotes.JSONReaderURL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.touristnotes.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRead extends ArrayAdapter<JSONObject> {
    int listLayout;
    ArrayList<JSONObject> usersList;
    Context context;

    public CategoriesRead(Context context, int listLayout, int field, ArrayList<JSONObject> usersList) {
        super(context, listLayout, field, usersList);
        this.context = context;
        this.listLayout = listLayout;
        this.usersList = usersList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(listLayout, null, false);
        TextView c_id = listViewItem.findViewById(R.id.li_id);
        TextView c_name = listViewItem.findViewById(R.id.li_name);
        try {
            c_id.setText(usersList.get(position).getString("id"));
            c_name.setText(usersList.get(position).getString("name"));
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return listViewItem;
    }
}
