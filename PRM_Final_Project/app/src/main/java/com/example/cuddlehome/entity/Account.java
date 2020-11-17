package com.example.cuddlehome.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Account")
public class Account {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private long id;
    @ColumnInfo(name = "Username")
    private String user_name;
    @ColumnInfo(name = "Password")
    private String password;
    @ColumnInfo(name = "DateOfBirth")
    private String date_of_birth;
    @ColumnInfo(name = "Phone")
    private String phone;
    @ColumnInfo(name = "Email")
    private String email;
    @ColumnInfo(name = "Gender")
    private boolean gender;
    @ColumnInfo(name = "Role")
    private int role;
    @ColumnInfo(name = "AccountImage")
    private String accountImage;

    public Account() {
    }

    public Account(String user_name, String password, String date_of_birth, String phone, String email, boolean gender, int role, String accountImage) {
        this.user_name = user_name;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.role = role;
        this.accountImage = accountImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
    }
}
