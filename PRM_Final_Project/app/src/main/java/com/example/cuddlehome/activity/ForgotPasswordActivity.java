package com.example.cuddlehome.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cuddlehome.GlobalVariable;
import com.example.cuddlehome.R;
import com.example.cuddlehome.dao.AccountDAO;
import com.example.cuddlehome.db.MyDatabase;
import com.example.cuddlehome.entity.Account;

import java.util.List;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button buttonConfirm;
    private EditText editTextUser;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private MyDatabase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        buttonConfirm = findViewById(R.id.buttonConfirm);
        editTextUser = findViewById(R.id.edtForgotUser);
        editTextEmail = findViewById(R.id.edtForEmail);
        editTextPhone = findViewById(R.id.edtForPhone);
        confirm();
    }

    public void confirm() {
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkUser()) {
                    Intent intent = new Intent(ForgotPasswordActivity.this, ChangePasswordActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("user_name", editTextUser.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Thông tin không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkUser() {
        String user = editTextUser.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        myDataBase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, GlobalVariable.dbName).allowMainThreadQueries().build();
        AccountDAO accountDAO = myDataBase.createAccountDAO();
        List<Account> lstAccount = accountDAO.getAllUser();
        for (Account u : lstAccount) {
            if (u.getUser_name().equals(user) && u.getEmail().equals(email) && u.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }
}