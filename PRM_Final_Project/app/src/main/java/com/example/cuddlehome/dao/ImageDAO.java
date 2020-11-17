package com.example.cuddlehome.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.cuddlehome.entity.Favourite;
import com.example.cuddlehome.entity.Image;

import java.util.List;

@Dao
public interface ImageDAO {

    @Transaction
    @Insert
    public void insert(Image image);

    @Update
    public void update(Image image);

    @Delete
    public void delete(Image image);

    @Query("Select * from Image where PostId = :id")
    public List<Image> listImageOfPost(int id);
}
