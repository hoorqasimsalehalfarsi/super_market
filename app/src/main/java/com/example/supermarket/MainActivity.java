package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.supermarket.databinding.ActivityLoginBinding;
import com.example.supermarket.databinding.ActivityMainBinding;
import com.example.supermarket.home.HomeActivity;
import com.example.supermarket.login.view.loginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    Button startBtn;
    Intent intent;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startBtn = binding.startBtn;
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        boolean isLogin = preferences.getBoolean("isLogin",false);
        String user = preferences.getString("user_name","");
        System.out.println(user+ "=============");
        if (isLogin){
            intent= new Intent(this, HomeActivity.class);
        }else {
            intent= new Intent(this, loginActivity.class);
        }
        startActivity(intent);
    }
}