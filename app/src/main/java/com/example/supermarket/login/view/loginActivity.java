package com.example.supermarket.login.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supermarket.MainActivity;
import com.example.supermarket.R;
import com.example.supermarket.databinding.ActivityLoginBinding;
import com.example.supermarket.home.HomeActivity;
import com.example.supermarket.login.data.DatabaseHelper;


public class loginActivity extends AppCompatActivity implements View.OnClickListener{
    TextView goToSignupTV,forgetPasswordTV;
    EditText emailET,passwordET;
    Button loginBtn;
    String email,password;
    boolean isLogin = false;
    ActivityLoginBinding binding;
    Intent intent;
    DatabaseHelper db;
    SQLiteDatabase sqLiteDatabase;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //data
        db = new DatabaseHelper(this);
        sqLiteDatabase = db.getReadableDatabase();
        preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        //setup view
        goToSignupTV =binding.goToSignupTV;
        forgetPasswordTV = binding.forgetPasswordTV;
        emailET =binding.emailET;
        passwordET =binding.passwordET;
        loginBtn =binding.loginBtn;

        //chang color for text
        SpannableString spannableString = new SpannableString(goToSignupTV.getText());
        int startIndex = goToSignupTV.getText().toString().indexOf("Register here");
        int color = Color.RED;
        spannableString.setSpan(new ForegroundColorSpan(color), startIndex, startIndex + "Register here".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        goToSignupTV.setText(spannableString);


        //setup onClick
        goToSignupTV.setOnClickListener(loginActivity.this);
        loginBtn.setOnClickListener(loginActivity.this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.goToSignupTV){
            //text
            intent = new Intent(this, signupActivity.class);
            startActivity(intent);
        } else if (id == R.id.forgetPasswordTV) {
        } else if (id == R.id.loginBtn) {
            //login button
            //setupData
            email = emailET.getText().toString();
            password = passwordET.getText().toString();
            if (email.isEmpty()&&password.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please Enter the data",Toast.LENGTH_LONG).show();
            }else {
                if(loginUser(email,password)){
                    finish();
                    intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    public boolean loginUser(String email, String password) {
        if (checkUser(email,password)){
            cursor = db.getAllData();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int userID = cursor.getInt(cursor.getColumnIndex("UserID"));
                    @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex("UserName"));
                    @SuppressLint("Range") String userEmail = cursor.getString(cursor.getColumnIndex("Email"));
                    @SuppressLint("Range") String userPassword = cursor.getString(cursor.getColumnIndex("Password"));
                    editor.putInt("user_id",userID);
                    editor.putString("user_name",userName);
                    editor.putString("user_email",userEmail);
                    editor.putString("user_password",userPassword);
                    editor.putBoolean("isLogin",true);
                    editor.commit();
                    Toast.makeText(getApplicationContext(),"welcome " + userName,Toast.LENGTH_LONG).show();
                } while (cursor.moveToNext());

                cursor.close();
            }

            return true;
        }else {
            Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public boolean checkUser(String userName, String password) {
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users" +  " WHERE Email" + " = ? AND Password" + " = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

}