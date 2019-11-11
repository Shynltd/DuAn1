package com.example.duan1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.R;

public class LoginActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
EditText edUserName,edUserPassWord;
CheckBox chkRemember;
Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng nhập");
        init();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edUserName.getText().toString().trim().isEmpty()||edUserPassWord.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Không được để trống thống tin đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    if (edUserName.getText().toString().trim().equalsIgnoreCase("admin")&& edUserPassWord.getText().toString().trim().equalsIgnoreCase("admin")){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Sai tài khoản mật khẩu", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

    private void init() {
        btnSignIn=findViewById(R.id.btnSignIn);
        edUserName=findViewById(R.id.edUserName);
        edUserPassWord=findViewById(R.id.edPassWord);
        chkRemember=findViewById(R.id.chkRemember);

    }
}
