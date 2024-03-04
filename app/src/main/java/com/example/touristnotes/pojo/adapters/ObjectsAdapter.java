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
import com.example.touristnotes.pojo.objects.Object;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class ObjectsAdapter extends ArrayAdapter<Object> {
    List<Object> objectList;
    Context context;
    private LayoutInflater mInflater;

    //Конструктор
    public ObjectsAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        objectList = objects;
    }
    //Функция возвращения позиции выбранного элемента
    @Override
    public Object getItem(int position){
        return objectList.get(position);
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final ImageView objectMarked;
        public final TextView textViewName;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, ImageView objectMarked, TextView textViewName) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.objectMarked = objectMarked;
            this.textViewName = textViewName;
        }
        public static ViewHolder create(RelativeLayout rootView){
            ImageView imageView = (ImageView) rootView.findViewById(R.id.li_img);
            ImageView objectMarked = (ImageView) rootView.findViewById(R.id.item_marked);
            TextView textViewName = (TextView) rootView.findViewById(R.id.li_name);
            return new ViewHolder(rootView, imageView, objectMarked, textViewName);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.list_item_object, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Object item = getItem(position);
        vh.textViewName.setText(item.getName());
        Picasso.get().load(item.getImage()).into(vh.imageView);
        if (Objects.equals(item.getMarked(), "1")){
            vh.objectMarked.setVisibility(View.VISIBLE);
        } else {
            vh.objectMarked.setVisibility(View.INVISIBLE);
        }
        return vh.rootView;
    }
}
