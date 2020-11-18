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

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText editTextNewPass;
    private EditText editTextConfirmNewPass;
    private Button buttonChange;
    private MyDatabase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        editTextConfirmNewPass = findViewById(R.id.edtConfirmNewPass);
        editTextNewPass = findViewById(R.id.edtNewPass);
        buttonChange = findViewById(R.id.buttonChange);
        change();
    }

    public void change() {
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPass = editTextNewPass.getText().toString().trim();
                String confirmPass = editTextConfirmNewPass.getText().toString().trim();
                myDataBase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, GlobalVariable.dbName).allowMainThreadQueries().build();
                AccountDAO accountDAO = myDataBase.createAccountDAO();
                if (confirmPass.equals(newPass) && newPass.length() >= 8) {
                    Intent intent1 = getIntent();
                    Bundle bundle = intent1.getExtras();
                    if (bundle != null) {
                        String userName = (String) bundle.get("user_name");
                        Account account = accountDAO.findUserByName(userName);
                        Toast.makeText(getApplicationContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                        account.setPassword(confirmPass);
                        accountDAO.update(account);
                        Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Xác nhận mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
                if(newPass.length() < 8){
                    Toast.makeText(getApplicationContext(), "Mật khẩu ít nhất có 8 ký tự", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}