package com.example.touristnotes.pojo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.touristnotes.R;
import com.example.touristnotes.pojo.collections.Collection;
import com.example.touristnotes.pojo.regions.Region;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CollectionsAdapter extends ArrayAdapter<Collection> {
    List<Collection> collectionList;
    Context context;
    private LayoutInflater mInflater;

    //Конструктор
     public CollectionsAdapter(Context context, List<Collection> objects){
         super(context, 0, objects);
         this.context = context;
         this.mInflater = LayoutInflater.from(context);
         collectionList = objects;
     }
     //Функция возвращения позиции выбранного объекта
    @Override
    public Collection getItem(int position) {return collectionList.get(position);}

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewName) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.li_img);
            TextView textViewName = (TextView) rootView.findViewById(R.id.li_name);
            return new ViewHolder(rootView, imageView, textViewName);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.list_item, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Collection item = getItem(position);
        vh.textViewName.setText(item.getName());
        Picasso.get().load(item.getImage()).into(vh.imageView);
        return vh.rootView;
    }
}

