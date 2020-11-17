package com.example.cuddlehome.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cuddlehome.GlobalVariable;
import com.example.cuddlehome.R;
import com.example.cuddlehome.dao.AccountDAO;
import com.example.cuddlehome.db.MyDatabase;
import com.example.cuddlehome.entity.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    final Calendar myCalender = Calendar.getInstance();
    private EditText edtDOB;
    private EditText edtUser;
    private EditText edtPassword;
    private EditText edtPhone;
    private EditText edtEmail;
    private RadioGroup radioGroup;
    public String gender = "";
    private Button buttonRegister;
    private boolean sex;
    private MyDatabase myDataBase;

    public void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CHINA);
        edtDOB.setText(sdf.format(myCalender.getTime()));
    }

    public boolean regexPhone(String phone) {
        String regex = "(0)+([0-9]{8,10})";
        return phone.matches(regex);

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalender.set(Calendar.YEAR, year);
            myCalender.set(Calendar.MONTH, month);
            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtPassword = findViewById(R.id.edtInputPassword);
        edtPhone = findViewById(R.id.edtInputPhone);
        edtEmail = findViewById(R.id.edtInputEmail);
        radioGroup = findViewById(R.id.radioGroup);
        edtUser = findViewById(R.id.edtInputUser);
        edtDOB = findViewById(R.id.edtInputDOB);
        buttonRegister = findViewById(R.id.btnRegister1);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDataBase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, GlobalVariable.dbName).allowMainThreadQueries().build();
                AccountDAO accountDAO = myDataBase.createAccountDAO();
                if (getAllCorrect()) {
                    Account account = new Account();
                    account.setUser_name(edtUser.getText().toString());
                    account.setPassword(edtPassword.getText().toString());
                    account.setDate_of_birth(edtDOB.getText().toString());
                    account.setPhone(edtPhone.getText().toString());
                    account.setEmail(edtEmail.getText().toString());
                    account.setGender(sex);
                    account.setRole(2);
                    account.setAccountImage("user_icon");
                    accountDAO.insert(account);
                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean getAllCorrect() {
        if (checkUser() && checkPass() && checkDOB() && checkPhone() && checkEmail() && checkGender()) {
            return true;
        }
        return false;
    }

    public boolean checkUser() {
        String txtUser = edtUser.getText().toString();
        txtUser = txtUser.trim();
        myDataBase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, GlobalVariable.dbName).allowMainThreadQueries().build();
        AccountDAO accountDAO = myDataBase.createAccountDAO();
        String user_name = accountDAO.checkUserName(txtUser);

        if (txtUser.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập tài khoản", Toast.LENGTH_SHORT).show();
            return false;
        } else
            if (user_name != null){
                Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (txtUser.length() < 3) {
            edtUser.requestFocus();
            edtUser.selectAll();
            Toast.makeText(getApplicationContext(), "Độ dài tên phải có ít nhất 3 ký tự!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkPass() {
        String pass = edtPassword.getText().toString();
        pass = pass.trim();
        if (pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (pass.length() < 8) {
            edtPassword.requestFocus();
            edtPassword.selectAll();
            Toast.makeText(getApplicationContext(), "Mật khẩu phải có ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkDOB() {
        String dateString = edtDOB.getText().toString();
        if (dateString.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập ngày sinh", Toast.LENGTH_SHORT).show();
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,....
        try {
            df.parse(dateString);
            edtDOB.setText(dateString);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(), "Invalid date", Toast.LENGTH_SHORT).show();
            edtDOB.setText("");
            return false;
        }
        return true;
    }

    public boolean checkPhone() {
        String phone = edtPhone.getText().toString();
        phone = phone.trim();
        if (phone.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.length() > 11 || phone.length() < 9) {
            edtPhone.requestFocus();
            edtPhone.selectAll();
            Toast.makeText(getApplicationContext(), "Số điện thoại không đúng", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!regexPhone(phone)) {
            edtPhone.requestFocus();
            edtPhone.selectAll();
            Toast.makeText(getApplicationContext(), "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean checkEmail() {
        String email = edtEmail.getText().toString();
        email = email.trim();
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValid(email)) {
            edtEmail.requestFocus();
            edtEmail.selectAll();
            Toast.makeText(getApplicationContext(), "Email không đúng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkGender() {
        int id = radioGroup.getCheckedRadioButtonId();
        if (id == 1) {
            sex = true;
        } else if (id == 0) {
            sex = false; // female
        }
        if (id == -1) {
            Toast.makeText(getApplicationContext(), "Bạn chưa chọn giới tính", Toast.LENGTH_LONG).show();
            return false;
        }
        RadioButton radioButton = findViewById(id);
        gender = radioButton.getText().toString();
        return true;
    }
}