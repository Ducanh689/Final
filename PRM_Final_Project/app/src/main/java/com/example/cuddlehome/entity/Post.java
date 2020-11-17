package com.example.cuddlehome.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Post",
        foreignKeys = @ForeignKey(
                entity = Account.class,
                parentColumns = {"Id"},
                childColumns = {"AccountId"},
                onDelete = ForeignKey.CASCADE)
        )
public class Post {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private long id;
    @ColumnInfo(name = "PostName")
    private String name;
    @ColumnInfo(name = "Rating")
    private int rating;
    @ColumnInfo(name = "MaxPeople")
    private int max_people;
    @ColumnInfo(name = "CurrentPeople")
    private int current_people;
    @ColumnInfo(name = "Price")
    private double price;
    @ColumnInfo(name = "Phone")
    private String phone;
    @ColumnInfo(name = "Condition")
    private boolean condition;
    @ColumnInfo(name = "Square")
    private double square;
    @ColumnInfo(name = "ForePayment")
    private double fore_payment;
    @ColumnInfo(name = "Address")
    private String address;
    @ColumnInfo(name = "Description")
    private String description;
    @ColumnInfo(name = "AccountId")
    private long account_id;

    public Post() {
    }

    public Post(String name, int rating, int max_people, int current_people, double price, String phone, boolean condition, double square, double fore_payment, String address, String description, long account_id) {
        this.name = name;
        this.rating = rating;
        this.max_people = max_people;
        this.current_people = current_people;
        this.price = price;
        this.phone = phone;
        this.condition = condition;
        this.square = square;
        this.fore_payment = fore_payment;
        this.address = address;
        this.description = description;
        this.account_id = account_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public int getCurrent_people() {
        return current_people;
    }

    public void setCurrent_people(int current_people) {
        this.current_people = current_people;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getFore_payment() {
        return fore_payment;
    }

    public void setFore_payment(double fore_payment) {
        this.fore_payment = fore_payment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }
}
