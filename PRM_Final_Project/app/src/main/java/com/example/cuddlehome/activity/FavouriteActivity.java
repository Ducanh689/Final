package com.example.cuddlehome.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ListView;

import com.example.cuddlehome.GlobalVariable;
import com.example.cuddlehome.R;
import com.example.cuddlehome.adpter.FavouriteAdapter;
import com.example.cuddlehome.dao.FavouriteDAO;
import com.example.cuddlehome.dao.PostDAO;
import com.example.cuddlehome.db.MyDatabase;
import com.example.cuddlehome.entity.Favourite;
import com.example.cuddlehome.entity.Post;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private ListView listFavourite;

    private List<Post> listFavoritePost;
    private List<Favourite> favouriteList;

    private MyDatabase connection;
    private PostDAO postDAO;
    private FavouriteDAO favouriteDAO;

    private FavouriteAdapter favouriteActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        listFavourite = findViewById(R.id.listFavourite);
        connection = Room.databaseBuilder(this, MyDatabase.class,
                GlobalVariable.dbName)
                .allowMainThreadQueries()
                .build();
        favouriteDAO = connection.createFavouriteDAO();
        favouriteList = favouriteDAO.listFavouriteByAccountId(GlobalVariable.UserId);

        postDAO = connection.createPostDAO();
        listFavoritePost = new ArrayList<>();
        for (Favourite favo : favouriteList ){
            Post p = postDAO.getPostById(favo.getPost_id());
            listFavoritePost.add(p);
        }

        favouriteActivity =new FavouriteAdapter(this,R.layout.favourite_detail,listFavoritePost);
        listFavourite.setAdapter(favouriteActivity);
    }
}