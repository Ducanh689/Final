package com.example.cuddlehome.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuddlehome.GlobalVariable;
import com.example.cuddlehome.R;
import com.example.cuddlehome.dao.AccountDAO;
import com.example.cuddlehome.db.MyDatabase;
import com.example.cuddlehome.entity.Account;

public class AddMotelActivity extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTextPrice;
    private EditText editTextDeposit;
    private EditText editTextSquare;
    private EditText editTextMaxpeople;
    private EditText editTextAvailable;
    private EditText editTextDes;
    private TextView editTextNamePicture;
    private Button buttonCancel;
    private Button buttonOk;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_motel_activity);
        findById();
        imageClicked();
//        getAllCorrect();
        btnHuy();
        btnOk();

    }

    private void findById() {
        editTextName = findViewById(R.id.edtNameMotel);
        editTextAddress = findViewById(R.id.edtAddressMotel);
        editTextPhone = findViewById(R.id.edtPhoneMotel);
        editTextPrice = findViewById(R.id.edtPriceMotel);
        editTextDeposit = findViewById(R.id.edtPrice);
        editTextSquare = findViewById(R.id.edtSquareMotel);
        editTextMaxpeople = findViewById(R.id.edtMaxPeople);
        editTextAvailable = findViewById(R.id.edtAvailablePeople);
        editTextDes = findViewById(R.id.edtDes);
        editTextNamePicture = findViewById(R.id.edtAddressPicture);
        buttonCancel = findViewById(R.id.btnCancel);
        buttonOk = findViewById(R.id.btnOk);
        imageView = findViewById(R.id.imageView10);
    }


    private void imageClicked() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        pickImageGallery();
                    }
                } else {
                    pickImageGallery();
                }
            }
        });
    }

    private void pickImageGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageGallery();
                } else {
                    Toast.makeText(this, "Permission denide...!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageView.setImageURI(data.getData());
            editTextNamePicture.setText(data.getData().getPath());
        }
    }

    // check input
    public boolean getAllCorrect() {
        if (checkAddressMotel() && checkCustom() && checkCustom_Aval() && checkDeposit() && checkNameMotel() && checkPhone() && checkPrice() && checkSquare() && namePicture())
            return true;
        return false;
    }

    public boolean checkNameMotel() {
        String txtNameMotel = editTextName.getText().toString();
        txtNameMotel = txtNameMotel.trim();
//        myDataBase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, GlobalVariable.dbName).allowMainThreadQueries().build();
//        AccountDAO accountDAO = myDataBase.createAccountDAO();
//        String user_name = accountDAO.checkUserName(txtUser);

        if (txtNameMotel.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập tên nhà trọ", Toast.LENGTH_SHORT).show();
            return false;
        } else
//        if (user_name != null){
//            Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (txtUser.length() < 3) {
//            edtUser.requestFocus();
//            edtUser.selectAll();
//            Toast.makeText(getApplicationContext(), "Độ dài tên phải có ít nhất 3 ký tự!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
            return true;
    }

    public boolean checkAddressMotel() {
        String txtNameMotel = editTextAddress.getText().toString();
        txtNameMotel = txtNameMotel.trim();
        if (txtNameMotel.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ nhà trọ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean regexPhone(String phone) {
        String regex = "(0)+([0-9]{8,10})";
        return phone.matches(regex);

    }

    public boolean checkPhone() {
        String phone = editTextPhone.getText().toString();
        phone = phone.trim();
        if (phone.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.length() > 11 || phone.length() < 9) {
            editTextPhone.requestFocus();
            editTextPhone.selectAll();
            Toast.makeText(getApplicationContext(), "Số điện thoại không đúng", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!regexPhone(phone)) {
            editTextPhone.requestFocus();
            editTextPhone.selectAll();
            Toast.makeText(getApplicationContext(), "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkPrice() {
        String price = editTextPrice.getText().toString();
        price = price.trim();
        if (price.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập giá tiền", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(price) < 0) {
            Toast.makeText(getApplicationContext(), "Giá tiền không là số âm", Toast.LENGTH_SHORT).show();
            return false;
        }
        char a = '.';
        int count = 0;
        for (int i = 0; i <= price.length(); i++) {
            if (price.charAt(i) == a) {
                count++;
            }
        }
        if (count >= 2) {
            Toast.makeText(getApplicationContext(), "Giá tiền không đúng định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkDeposit() {
        String price = editTextDeposit.getText().toString();
        price = price.trim();
        if (price.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập tiền đặt cọc", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(price) < 0) {
            Toast.makeText(getApplicationContext(), "Tiền đặt cọc không là số âm", Toast.LENGTH_SHORT).show();
            return false;
        }
        char a = '.';
        int count = 0;
        for (int i = 0; i <= price.length(); i++) {
            if (price.charAt(i) == a) {
                count++;
            }
        }
        if (count >= 2) {
            Toast.makeText(getApplicationContext(), "Số tiền không đúng định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkSquare() {
        String price = editTextSquare.getText().toString();
        price = price.trim();
        if (price.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập diện tích", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(price) < 0) {
            Toast.makeText(getApplicationContext(), "Diện tích không là số âm", Toast.LENGTH_SHORT).show();
            return false;
        }
        char a = '.';
        int count = 0;
        for (int i = 0; i <= price.length(); i++) {
            if (price.charAt(i) == a) {
                count++;
            }
        }
        if (count >= 2) {
            Toast.makeText(getApplicationContext(), "diện tích không đúng định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkCustom() {
        String max_people = editTextMaxpeople.getText().toString();
        String aval_people = editTextAvailable.getText().toString();

        if (max_people.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số người tối đa", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(max_people) < 0) {
            Toast.makeText(getApplicationContext(), "người tối đa không là số âm", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkCustom_Aval() {
//        String max_people = editTextMaxpeople.getText().toString();
        String aval_people = editTextAvailable.getText().toString();

        if (aval_people.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số người hiện có", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(aval_people) < 0) {
            Toast.makeText(getApplicationContext(), "người tối đa không là số âm", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(aval_people) > Integer.parseInt(editTextMaxpeople.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Số người hiện tại không thể lớn hơn số người tối đa", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean namePicture() {
        String name = editTextNamePicture.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void btnHuy() {
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMotelActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void btnOk() {
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getAllCorrect())
                    Toast.makeText(AddMotelActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddMotelActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}