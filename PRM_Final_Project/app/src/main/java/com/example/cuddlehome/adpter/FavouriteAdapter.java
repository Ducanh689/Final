package com.example.cuddlehome.adpter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuddlehome.R;
import com.example.cuddlehome.entity.Post;

import java.util.List;

public class FavouriteAdapter extends BaseAdapter {

    private Activity activity;
    private int layout;
    private List<Post> listPostFavourite;

    public FavouriteAdapter(Activity activity, int layout, List<Post> listPostFavourite) {
        this.activity = activity;
        this.layout = layout;
        this.listPostFavourite = listPostFavourite;
    }



    @Override
    public int getCount() {
        return listPostFavourite.size();
    }

    @Override
    public Object getItem(int position) {
        return listPostFavourite.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ImageView imageView;
        final TextView text1;
        final TextView text2;
        final TextView text3;
        final TextView text4;

        if(view == null) {
            view = activity.getLayoutInflater().inflate(layout,null);
            imageView = view.findViewById(R.id.imageView);
            text1 = view.findViewById(R.id.textView1);
            text2 = view.findViewById(R.id.textView2);
            text3 = view.findViewById(R.id.textView3);
            text4 = view.findViewById(R.id.textView4);

            view.setTag(R.id.imageView,imageView);
            view.setTag(R.id.textView1,text1);
            view.setTag(R.id.textView2,text2);
            view.setTag(R.id.textView3,text3);
            view.setTag(R.id.textView4,text4);
        } else {
            imageView = (ImageView) view.getTag(R.id.imageView);
            text1 = (TextView) view.getTag(R.id.textView1);
            text2 = (TextView) view.getTag(R.id.textView2);
            text3 = (TextView) view.getTag(R.id.textView3);
            text4 = (TextView) view.getTag(R.id.textView4);
        }

        Post post = listPostFavourite.get(position);
        text1.setText(post.getName());
        text2.setText(post.getPrice()+"VDN /Nguoi");
        text3.setText(post.getAddress());
        text4.setText(post.getCurrent_people()+"/"+post.getMax_people()+" Nguoi");
        return view;
    }
}
