package com.example.cuddlehome.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuddlehome.DatabaseDummy;
import com.example.cuddlehome.GlobalVariable;
import com.example.cuddlehome.R;
import com.example.cuddlehome.dao.AccountDAO;
import com.example.cuddlehome.db.MyDatabase;
import com.example.cuddlehome.entity.Account;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    //Database
    private MyDatabase connection;
    private AccountDAO accountDAO;
    //View
    private TextView textViewLink;
    private Button buttonLogin;
    private Button buttonRegister;
    private Button buttonForgotPassword;
    private MyDatabase myDataBase;
    private EditText editTextUser;
    private EditText editTextPassword;

    //Location
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewLink = findViewById(R.id.txtLink);
        textViewLink.setText(Html.fromHtml(getString(R.string.link)));
        textViewLink.setMovementMethod(LinkMovementMethod.getInstance());

        buttonLogin = findViewById(R.id.btnLogin);
        buttonRegister = findViewById(R.id.btnRegister);
        buttonForgotPassword = findViewById(R.id.btnForgotPassWord);

        editTextUser = findViewById(R.id.edtUserName);
        editTextPassword = findViewById(R.id.edtPassword);

        //Initialdatabase
//        DatabaseDummy databaseDummy = new DatabaseDummy(getApplicationContext());
//        databaseDummy.CreateDatabase();
        //
        Login();
        Register();
        ForgotPassWord();
        //Initialize Location
        getLocation();

    }

    public void Login() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDataBase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, GlobalVariable.dbName).allowMainThreadQueries().build();
                AccountDAO accountDAO = myDataBase.createAccountDAO();
                List<Account> list = accountDAO.getAllUser();
                String username = editTextUser.getText().toString();
                String password = editTextPassword.getText().toString();
                boolean logined = false;
                for (Account u : list) {
                    if (username.trim().equals(u.getUser_name()) && password.trim().equals(u.getPassword())) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        logined = true;
                        GlobalVariable.UserId = u.getId();
                        startActivity(intent);
                        finish();
                    }
                }
                if (logined == false) {
                    Toast.makeText(getApplicationContext(), "Sai tên tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Register() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ForgotPassWord() {
        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }


    private void getLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null){
                        try {
                            //Initialize GeoCoder
                            Geocoder geocoder = new Geocoder(LoginActivity.this,
                                    Locale.getDefault());
                            //Initialize address list
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                                    location.getLongitude(), 1);
//                            GlobalVariable.location = addresses.get(0).getSubAdminArea();
                            GlobalVariable.location = "Thạch Thất";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
        }

    }

}