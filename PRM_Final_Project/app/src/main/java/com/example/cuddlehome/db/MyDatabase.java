package com.example.cuddlehome.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cuddlehome.dao.AccountDAO;
import com.example.cuddlehome.dao.FavouriteDAO;
import com.example.cuddlehome.dao.ImageDAO;
import com.example.cuddlehome.dao.PostDAO;
import com.example.cuddlehome.dao.RatingDAO;
import com.example.cuddlehome.entity.Account;
import com.example.cuddlehome.entity.Favourite;
import com.example.cuddlehome.entity.Post;
import com.example.cuddlehome.entity.Image;
import com.example.cuddlehome.entity.Rating;

@Database(entities = {Account.class, Post.class, Image.class, Favourite.class, Rating.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract AccountDAO createAccountDAO();
    public abstract FavouriteDAO createFavouriteDAO();
    public abstract PostDAO createPostDAO();
    public abstract ImageDAO createImageDAO();
    public abstract RatingDAO createRatingDAO();
}
