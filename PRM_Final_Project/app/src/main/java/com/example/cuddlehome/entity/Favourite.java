package com.example.cuddlehome.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity (tableName = "Favourite",
        primaryKeys = {"PostId", "AccountId"},
        foreignKeys = {
        @ForeignKey(
                entity = Post.class,
                parentColumns = {"Id"},
                childColumns = {"PostId"},
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Account.class,
                parentColumns = {"Id"},
                childColumns = {"AccountId"},
                onDelete = ForeignKey.CASCADE)}
        )
public class Favourite {
    @ColumnInfo (name = "PostId")
    private long post_id;
    @ColumnInfo (name = "AccountId")
    private long account_id;

    public Favourite() {
    }

    public Favourite(long post_id, long account_id) {
        this.post_id = post_id;
        this.account_id = account_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }
}
