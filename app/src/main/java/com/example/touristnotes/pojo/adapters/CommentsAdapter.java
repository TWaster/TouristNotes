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
import com.example.touristnotes.pojo.comments.Comment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentsAdapter extends ArrayAdapter<Comment> {
    List<Comment> commentList;
    Context context;
    private LayoutInflater mInflater;

    //Конструктор
    public CommentsAdapter(Context context, List<Comment> comments) {
        super(context, 0, comments);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        commentList = comments;
    }
    //Функция возвращения позиции выбранного элемента
    @Override
    public Comment getItem(int position){return commentList.get(position);}

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;
        public final TextView textViewDate;
        public final TextView textViewComment;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewName, TextView textViewDate, TextView textViewComment){
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewDate = textViewDate;
            this.textViewComment = textViewComment;
        }
        public static ViewHolder create(RelativeLayout rootView){
            //Определить нужные поля для вывода информации
            ImageView imageView = (ImageView) rootView.findViewById(R.id.user_avatar);
            TextView textViewComment = (TextView) rootView.findViewById(R.id.comment_text);
            TextView textViewDate = (TextView) rootView.findViewById(R.id.comment_date);
            TextView textViewName = (TextView) rootView.findViewById(R.id.comment_user_name);
            return new ViewHolder(rootView, imageView, textViewName, textViewDate, textViewComment);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.comments_item, parent, false);
            vh = CommentsAdapter.ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (CommentsAdapter.ViewHolder) convertView.getTag();
        }
        Comment item = getItem(position);
        vh.textViewName.setText(item.getUser());
        vh.textViewComment.setText(item.getText());
        vh.textViewDate.setText(item.getDate());
        Picasso.get().load(item.getUserImage()).into(vh.imageView);
        return vh.rootView;
    }
}
