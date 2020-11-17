package com.example.cuddlehome.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Rating",
        foreignKeys = {
                @ForeignKey(
                        entity = Account.class,
                        parentColumns = {"Id"},
                        childColumns = {"AccountId"},
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Post.class,
                        parentColumns = {"Id"},
                        childColumns = {"PostId"},
                        onDelete = ForeignKey.CASCADE)
        })
public class Rating {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "AccountId")
    private long account_id;
    @ColumnInfo(name = "PostId")
    private long post_id;
    @ColumnInfo(name = "UserRating")
    private int rating_from_user;
    @ColumnInfo(name = "Content")
    private String content;

    public Rating() {
    }

    public Rating(long id, long account_id, long post_id, int rating_from_user, String content) {
        this.id = id;
        this.account_id = account_id;
        this.post_id = post_id;
        this.rating_from_user = rating_from_user;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public int getRating_from_user() {
        return rating_from_user;
    }

    public void setRating_from_user(int rating_from_user) {
        this.rating_from_user = rating_from_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
