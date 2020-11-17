package com.example.cuddlehome;

import android.content.Context;

import androidx.room.Room;

import com.example.cuddlehome.dao.AccountDAO;
import com.example.cuddlehome.dao.FavouriteDAO;
import com.example.cuddlehome.dao.ImageDAO;
import com.example.cuddlehome.dao.PostDAO;
import com.example.cuddlehome.dao.RatingDAO;
import com.example.cuddlehome.db.MyDatabase;
import com.example.cuddlehome.entity.Account;
import com.example.cuddlehome.entity.Favourite;
import com.example.cuddlehome.entity.Image;
import com.example.cuddlehome.entity.Post;
import com.example.cuddlehome.entity.Rating;

public class DatabaseDummy {
    private Context context;
    private MyDatabase connection;
    private PostDAO postDAO;
    private ImageDAO imageDAO;
    private AccountDAO accountDAO;
    private FavouriteDAO favouriteDAO;
    private RatingDAO ratingDAO;

    public DatabaseDummy(Context context) {
        this.context = context;
    }

    public void CreateDatabase(){
        connection = Room.databaseBuilder( context, MyDatabase.class,
                GlobalVariable.dbName)
                .allowMainThreadQueries()
                .build();
        postDAO = connection.createPostDAO();
        imageDAO = connection.createImageDAO();
        accountDAO = connection.createAccountDAO();
        favouriteDAO = connection.createFavouriteDAO();
        ratingDAO = connection.createRatingDAO();
        CreateAccount();
        CreatePost();
        CreateImage();
    }

    public void CreateAccount(){
        Account account = new Account("Beo", "12345678",
                "1/10/2020", "0912345827",
                "Beo@gmail.com", true, 2, "user_icon");
        accountDAO.insert(account);
        Account account1 = new Account("Tien", "12345678",
                "16/12/2020", "0912482739",
                "Tien@gmail.com", true, 2, "user_icon");
        accountDAO.insert(account1);
        Account account2 = new Account("Minh", "12345678",
                "20/3/2020", "0912374767",
                "Minh@gmail.com", false, 2, "user_icon");
        accountDAO.insert(account2);
    }

    public void CreatePost(){
        Post post1 = new Post( "Nhà trọ1", 2, 4, 5,
                    3000000, "0912600878", true, 40.3, 500000,
                    "Tổ 91 phường Mường Trà thành phố Điện Biên Phủ tỉnh Điện Biên","Rất tốt và các thứ nhé các bạn",1);
            postDAO.insert(post1);
        Post post2 = new Post("Nhà trọ2", 5, 4, 5,
                2000000, "0912600878", true, 28, 500000,
                "Tổ 29 phường Mường Trà thành phố Điện Biên Phủ tỉnh Điện Biên","Rất tốt và các thứ nhé các bạn",1);
        postDAO.insert(post2);
        Post post3 = new Post("Nhà trọ3", 1, 4, 4,
                3000000, "0912600878", false, 50.2, 1000000,
                "Tổ 34 phường Mường Trà thành phố Hà Nội","Rất tốt và các thứ nhé các bạn",3);
        postDAO.insert(post3);
        Post post4 = new Post("Nhà trọ4", 3, 4, 5,
                2500000, "0912600878", true, 35, 1000000,
                "Tổ 12 phường Thạch Thất thành phố Hà Nội","Rất tốt và các thứ nhé các bạn",1);
        postDAO.insert(post4);
        Post post5 = new Post("Nhà trọ5", 5, 3, 3,
                3000000, "0912600878", false, 44, 500000,
                "Tổ 33 phường Mường Trà thành phố Điện Biên Phủ tỉnh Điện Biên","Rất tốt và các thứ nhé các bạn",2);
        postDAO.insert(post5);
        Post post6 = new Post("Nhà trọ6", 4, 4, 5,
                2000000, "0912600878", true, 22, 500000,
                "Tổ 24 phường Thạch Thất thành phố Hà Nội ","Rất tốt và các thứ nhé các bạn",2);
        postDAO.insert(post6);
        Post post7 = new Post("Nhà trọ7", 2, 4, 5,
                3000000, "0912600878", true, 34, 1000000,
                "Tổ 16 phường Thạch Thất thành phố Hà Nội","Rất tốt và các thứ nhé các bạn",3);
        postDAO.insert(post7);
        Post post8 = new Post("Nhà trọ8", 3, 4, 4,
                4000000, "0912600878", false, 37, 500000,
                "Tổ 10 phường Thạch Thất thành phố Hà Nội","Rất tốt và các thứ nhé các bạn",3);
        postDAO.insert(post8);
        Post post9 = new Post("Nhà trọ9", 1, 4, 5,
                3500000, "0912600878", true, 25, 1000000,
                "Tổ 9 phường Thạch Thất thành phố Hà Nội","Rất tốt và các thứ nhé các bạn",1);
        postDAO.insert(post9);
        Post post10 = new Post("Nhà trọ10", 2, 5, 5,
                3000000, "0912600878", false, 38, 500000,
                "Tổ 3 phường Thạch Thất thành phố Hà Nội","Rất tốt và các thứ nhé các bạn",2);
        postDAO.insert(post10);
    }

    public void CreateImage(){
         Image image1 = new Image(1, "tro_1");
        imageDAO.insert(image1);
        Image image2 = new Image(1, "tro_2");
        imageDAO.insert(image2);
        Image image3 = new Image(1, "tro_3");
        imageDAO.insert(image3);
        Image image4 = new Image(2, "tro_4");
        imageDAO.insert(image4);
        Image image5 = new Image(2, "tro_5");
        imageDAO.insert(image5);
        Image image6 = new Image(2, "tro_6");
        imageDAO.insert(image6);
        Image image7 = new Image(3, "tro_7");
        imageDAO.insert(image7);
        Image image8 = new Image(3, "tro_8");
        imageDAO.insert(image8);
        Image image9 = new Image(3, "tro_9");
        imageDAO.insert(image9);
        Image image10 = new Image(4, "tro_1");
        imageDAO.insert(image10);
        Image image11 = new Image(4, "tro_2");
        imageDAO.insert(image11);
        Image image12 = new Image(4, "tro_3");
        imageDAO.insert(image12);
        Image image13 = new Image(5, "tro_4");
        imageDAO.insert(image13);
        Image image14 = new Image(5, "tro_5");
        imageDAO.insert(image14);
        Image image15 = new Image(5, "tro_6");
        imageDAO.insert(image15);
        Image image16 = new Image(6, "tro_7");
        imageDAO.insert(image16);
        Image image17 = new Image(6, "tro_8");
        imageDAO.insert(image17);
        Image image18 = new Image(6, "tro_9");
        imageDAO.insert(image18);
        Image image19 = new Image(7, "tro_1");
        imageDAO.insert(image19);
        Image image20 = new Image(7, "tro_2");
        imageDAO.insert(image20);
        Image image21 = new Image(7, "tro_3");
        imageDAO.insert(image21);
        Image image22 = new Image(8, "tro_4");
        imageDAO.insert(image22);
        Image image23 = new Image(8, "tro_5");
        imageDAO.insert(image23);
        Image image24 = new Image(8, "tro_6");
        imageDAO.insert(image24);
        Image image25 = new Image(9, "tro_7");
        imageDAO.insert(image25);
        Image image26 = new Image(9, "tro_8");
        imageDAO.insert(image26);
        Image image27 = new Image(9, "tro_9");
        imageDAO.insert(image27);
        Image image28 = new Image(10, "tro_1");
        imageDAO.insert(image28);
        Image image29 = new Image(10, "tro_2");
        imageDAO.insert(image29);
        Image image30 = new Image(10, "tro_3");
        imageDAO.insert(image30);
    }

//    public void CreateFavourite(){
//        Favourite favourite = new Favourite();
//        Favourite favourite = new Favourite();
//        Favourite favourite = new Favourite();
//        Favourite favourite = new Favourite();
//        Favourite favourite = new Favourite();
//        Favourite favourite = new Favourite();
//        Favourite favourite = new Favourite();
//        Favourite favourite = new Favourite();
//        Favourite favourite = new Favourite();
//        Favourite favourite = new Favourite();
//    }

//    public void CreateRating(){
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//        Rating rating = new Rating();
//    }

}
