package com.example.cuddlehome.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.cuddlehome.GlobalVariable;
import com.example.cuddlehome.OnPostClickListener;
import com.example.cuddlehome.R;
import com.example.cuddlehome.adpter.PostListAdapterHorizontal;
import com.example.cuddlehome.dao.ImageDAO;
import com.example.cuddlehome.dao.PostDAO;
import com.example.cuddlehome.dao.RatingDAO;
import com.example.cuddlehome.db.MyDatabase;
import com.example.cuddlehome.entity.Image;
import com.example.cuddlehome.entity.Post;
import com.example.cuddlehome.entity.PostImage;
import com.example.cuddlehome.entity.Rating;

import java.util.List;

public class ViewDetailsActivity extends AppCompatActivity implements OnPostClickListener {
    private SliderLayout sliderShow;
    private MyDatabase connection;

    private PostDAO postDAO;
    private Post post;
    private ImageDAO imageDAO;
    private RatingDAO ratingDAO;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<PostImage> postImageListRecycler;
    private List<Image> imageListSlider;

    private TextView textViewName;
    private TextView textViewPerson;
    private TextView textViewPrice;
    private TextView textViewCondition;
    private TextView textViewSquare;
    private TextView textViewForePay;
    private TextView textViewLocation;
    private TextView textViewPhone;
    private TextView textViewDescription;
    private RatingBar ratingBar;
    private TextView textViewCmt1;
    private RatingBar ratingBarcmt1;
    private TextView textViewCmt2;
    private RatingBar ratingBarcmt2;
    private ImageView ava1;
    private ImageView ava2;
    private List<Rating> ratings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        //Database connect
        connection = Room.databaseBuilder(this, MyDatabase.class,
                GlobalVariable.dbName)
                .allowMainThreadQueries()
                .build();
        postDAO = connection.createPostDAO();
        imageDAO = connection.createImageDAO();

        //Get Post By Id
        long id = getIntent().getLongExtra("postId", 0);
        post = postDAO.getPostById(id);
        textViewName = findViewById(R.id.txtName);
        textViewPerson = findViewById(R.id.txtPersonNear);
        textViewPrice = findViewById(R.id.txtPrice);
        textViewCondition = findViewById(R.id.txtCondition);
        textViewSquare = findViewById(R.id.txtSquare);
        textViewForePay = findViewById(R.id.txtForePay);
        textViewLocation = findViewById(R.id.txtLocation);
        textViewPhone = findViewById(R.id.txtPhone);
        textViewDescription = findViewById(R.id.txtDescription);
        ratingBar = findViewById(R.id.ratingBar);

        textViewName.setText(post.getName());
        textViewPerson.setText(post.getCurrent_people() + "/" + post.getMax_people());
        textViewPrice.setText(Double.toString(post.getPrice()));
        if (post.isCondition() == true){
            textViewCondition.setText("Còn");
        }else {
            textViewCondition.setText("Không còn");
        }
        textViewSquare.setText(Double.toString(post.getSquare()) + "m2");
        textViewForePay.setText(Double.toString(post.getFore_payment()));
        textViewLocation.setText(post.getAddress());
        textViewPhone.setText(post.getPhone());
        textViewDescription.setText(post.getDescription());
        ratingBar.setRating((float) post.getRating());


        //SliderShow
        sliderShow = (SliderLayout)findViewById(R.id.slider);

        imageListSlider = imageDAO.listImageOfPost(1);

        for(Image image : imageListSlider){
            int res = getResources().getIdentifier(image.getPostImage(), "drawable", getPackageName());
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            defaultSliderView
                    .image(res)
                    .setScaleType(BaseSliderView.ScaleType.Fit);


            sliderShow.addSlider(defaultSliderView);
        }

        //RecyclerView
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        postImageListRecycler = postDAO.listPostNearBy(GlobalVariable.location);
        adapter = new PostListAdapterHorizontal(postImageListRecycler, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void OnPostClick(int position, int option) {
        PostImage postImage = postImageListRecycler.get(position);
        Intent intent = new Intent(this, ViewDetailsActivity.class);
        intent.putExtra("postId",postImage.getPost().getId());
        startActivity(intent);
        finish();
    }
}