package com.example.cuddlehome.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.cuddlehome.entity.Favourite;

import java.util.List;

@Dao
public interface FavouriteDAO {
    @Transaction
    @Insert
    public void insert(Favourite favourite);

    @Update
    public void update(Favourite favourite);

    @Delete
    public void delete(Favourite favourite);

    @Query("Select * from Favourite")
    public List<Favourite> listFavourite();
}
