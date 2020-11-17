package com.example.cuddlehome.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.cuddlehome.entity.Post;
import com.example.cuddlehome.entity.PostImage;

import java.util.List;

@Dao
public interface PostDAO {
    @Insert
    public void insert(Post post);

    @Update
    public void update(Post post);

    @Delete
    public void delete(Post post);

    @Query("Select * from Post where Address like '%' || :local || '%'")
    public List<PostImage> listPostNearBy(String local);

    @Transaction
    @Query("Select * from Post Order by Rating Desc")
    public List<PostImage> listPostImages();

    @Query("select * from Post where id = :id")
    public Post getPostById (long id);
}
