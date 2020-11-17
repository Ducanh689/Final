package com.example.cuddlehome.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.cuddlehome.entity.Account;
import com.example.cuddlehome.entity.AccountPost;

import java.util.List;

@Dao
public interface AccountDAO {
    @Transaction
    @Insert
    public void insert(Account account);

    @Update()
    public void update(Account account);

    @Query("SELECT * FROM Account")
    public List<Account> getAllUser();

    @Query("SELECT Username FROM Account where Username like :x")
    public String checkUserName(String x);

    @Query("SELECT * FROM Account where 1=1 and Username =:x")
    public Account findUserByName(String x);

    @Query("Delete from Account where Id = :id")
    public void deleteAccount(int id);

    @Query("Select * from Account where Id = :id")
    public List<AccountPost> getAccountPost(int id);

}
