package com.example.cuddlehome.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuddlehome.OnPostClickListener;
import com.example.cuddlehome.R;
import com.example.cuddlehome.entity.Image;
import com.example.cuddlehome.entity.PostImage;

import java.util.List;

public class PostListAdapterVertical extends RecyclerView.Adapter<PostListAdapterVertical.ViewHolder> {

    private List<PostImage> list;
    private Context context;

    public PostListAdapterVertical(List<PostImage> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_post2, parent, false);
        return new ViewHolder(v, (OnPostClickListener) context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostImage imageOfPost = list.get(position);
        List<Image> imageList = imageOfPost.getImages();

        if (imageList.size() != 0) {
            int res1 = context.getResources().getIdentifier(imageList.get(0).getPostImage(), "drawable", context.getPackageName());
            holder.img.setImageResource(res1);
            String person = imageOfPost.getPost().getCurrent_people() + "/" + imageOfPost.getPost().getMax_people();
            holder.name.setText(imageOfPost.getPost().getName());
            holder.price.setText(Double.toString(imageOfPost.getPost().getPrice()));
            holder.person.setText(person);
            holder.address.setText(imageOfPost.getPost().getAddress());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView img;
        public TextView name;
        public TextView price;
        public TextView person;
        public TextView address;
        OnPostClickListener onPostClickListener;

        public ViewHolder(@NonNull View itemView, OnPostClickListener onPostClickListener){
            super(itemView);

            img = itemView.findViewById(R.id.ImageViewMotel);
            name = itemView.findViewById(R.id.txtNameMotel);
            price = itemView.findViewById(R.id.txtPriceMotel);
            person = itemView.findViewById(R.id.txtPersonMotel);
            address = itemView.findViewById(R.id.txtAddressMotel);

            this.onPostClickListener = onPostClickListener;
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            onPostClickListener.OnPostClick(getAdapterPosition(), 2);
        }
    }
}
