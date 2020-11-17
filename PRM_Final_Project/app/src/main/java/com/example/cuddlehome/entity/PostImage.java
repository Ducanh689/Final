package com.example.cuddlehome.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PostImage {
    @Embedded
    public Post post;
    @Relation(
            parentColumn = "Id",
            entityColumn = "PostId"
    )
    public List<Image> images;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
