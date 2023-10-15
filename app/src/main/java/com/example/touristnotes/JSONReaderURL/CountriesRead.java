package com.example.touristnotes.JSONReaderURL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.touristnotes.R;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class CountriesRead extends ArrayAdapter<JSONObject> {
    int listLayout;
    ArrayList<JSONObject> usersList;
    Context context;
    public CountriesRead(Context context, int listLayout, int field, ArrayList<JSONObject> usersList) {
        super(context, listLayout, field, usersList);
        this.context = context;
        this.listLayout = listLayout;
        this.usersList = usersList;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(listLayout, null, false);
        TextView c_id = listViewItem.findViewById(R.id.li_id);
        TextView c_name = listViewItem.findViewById(R.id.li_name);
        try {
            c_id.setText(usersList.get(position).getString("id"));
            c_name.setText(usersList.get(position).getString("name"));
            Picasso.get().load(usersList.get(position).getString("image")).into((ImageView) listViewItem.findViewById(R.id.li_img));
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return listViewItem;
    }
}
