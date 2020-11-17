package com.example.cuddlehome.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.cuddlehome.entity.Rating;

import java.util.List;
@Dao
public interface RatingDAO {
    @Transaction
    @Insert
    public void insert(Rating rating);

    @Update
    public void update(Rating rating);

    @Delete
    public void delete(Rating rating);

    @Query("Select * from Rating")
    public List<Rating> listRating();
}
