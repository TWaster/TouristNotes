package com.example.touristnotes.pojo.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.touristnotes.R;
import com.example.touristnotes.pojo.rating.User;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class RatingAdapter extends ArrayAdapter<User> {
    List<User> UserList;
    Context context;
    private LayoutInflater mInflater;

    //Конструктор
    public RatingAdapter(Context context, List<User> User) {
        super(context, 0, User);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        UserList = User;
    }
    //Функция возвращения позиции выбранного элемента
    @Override
    public User getItem(int position){
        return UserList.get(position);
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final TextView textUserPos;
        public final CircleImageView imageView;
        public final TextView textViewName;

        private ViewHolder(RelativeLayout rootView, TextView textUserPos, CircleImageView imageView, TextView textViewName) {
            this.rootView = rootView;
            this.textUserPos = textUserPos;
            this.imageView = imageView;
            this.textViewName = textViewName;
        }
        public static ViewHolder create(RelativeLayout rootView){
            CircleImageView imageView = (CircleImageView) rootView.findViewById(R.id.user_img);
            TextView textViewName = (TextView) rootView.findViewById(R.id.user_name);
            TextView textUserPos = (TextView) rootView.findViewById(R.id.user_position);
            return new ViewHolder(rootView, textUserPos, imageView, textViewName);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.list_item_rating, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        User item = getItem(position);
        vh.textViewName.setText(item.getName());
        vh.textUserPos.setText(item.getNum());
        Picasso.get().load(item.getImage()).into(vh.imageView);
        switch (item.getNum()) {
            case "1":
                vh.imageView.setBorderColor(Color.rgb(255,235, 59));
                vh.imageView.setBorderWidth(5);
                break;
            case "2":
                vh.imageView.setBorderColor(Color.rgb(192,192, 192));
                vh.imageView.setBorderWidth(5);
                break;
            case "3":
                vh.imageView.setBorderColor(Color.rgb(205,127, 50));
                vh.imageView.setBorderWidth(5);
                break;
        }
        return vh.rootView;
    }
}
