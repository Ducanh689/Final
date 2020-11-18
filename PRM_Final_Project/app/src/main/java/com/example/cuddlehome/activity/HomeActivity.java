package com.example.cuddlehome.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cuddlehome.GlobalVariable;
import com.example.cuddlehome.OnPostClickListener;
import com.example.cuddlehome.R;
import com.example.cuddlehome.adpter.PostListAdapterHorizontal;
import com.example.cuddlehome.adpter.PostListAdapterVertical;
import com.example.cuddlehome.dao.ImageDAO;
import com.example.cuddlehome.dao.PostDAO;
import com.example.cuddlehome.db.MyDatabase;
import com.example.cuddlehome.entity.Favourite;
import com.example.cuddlehome.entity.Image;
import com.example.cuddlehome.entity.Post;
import com.example.cuddlehome.entity.PostImage;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnPostClickListener {

    //RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter adapter;
    private List<PostImage> listPostWithImages;
    private List<PostImage> listPostWithImages2;

    //Database
    private MyDatabase connection;
    private PostDAO postDAO;
    private ImageDAO imageDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Database
        connection = Room.databaseBuilder(this, MyDatabase.class,
                GlobalVariable.dbName)
                .allowMainThreadQueries()
                .build();
        postDAO = connection.createPostDAO();
        imageDAO = connection.createImageDAO();

        //Recycler View
        recyclerView = findViewById(R.id.recyclerView_home);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        listPostWithImages = postDAO.listPostImages();
        //adapter
        adapter = new PostListAdapterHorizontal(listPostWithImages, this);
        recyclerView.setAdapter(adapter);

        //RecyclerView2
        recyclerView2 = findViewById(R.id.recyclerView_home2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        listPostWithImages2 = postDAO.listPostNearBy(GlobalVariable.location);
        adapter = new PostListAdapterVertical(listPostWithImages2, this);
        recyclerView2.setAdapter(adapter);

    }

    @Override
    public void OnPostClick(int position, int option) {
        PostImage postImage = null;
        if (option == 1) {
            postImage = listPostWithImages.get(position);
        } else {
            postImage = listPostWithImages2.get(position);
        }
        Intent intent = new Intent(HomeActivity.this, ViewDetailsActivity.class);
        intent.putExtra("postId", postImage.getPost().getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, FavouriteActivity.class);
                startActivity(intent);
                break;
            case R.id.item2:
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.item3:
                // tiến thêm activity trọ ở đây
                // you follow ?
        }

        return super.onOptionsItemSelected(item);
    }
}