package com.example.cuddlehome.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Image",
        foreignKeys = @ForeignKey(
                entity = Post.class,
                parentColumns = {"Id"},
                childColumns = {"PostId"},
                onDelete = ForeignKey.CASCADE)
        )
public class Image {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private long id;
    @ColumnInfo(name = "PostId")
    private long postId;
    @ColumnInfo(name = "PostImage")
    private String postImage;

    public Image() {
    }

    public Image(long postId, String postImage) {
        this.postId = postId;
        this.postImage = postImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}
