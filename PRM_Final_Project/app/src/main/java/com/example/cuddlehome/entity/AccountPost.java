package com.example.cuddlehome.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class AccountPost {
    @Embedded
    public Account account;
    @Relation(
            parentColumn = "Id",
            entityColumn = "AccountId"
    )
    public List<Post> posts;
}
