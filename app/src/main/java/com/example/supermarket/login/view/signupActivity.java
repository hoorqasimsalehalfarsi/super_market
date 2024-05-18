package com.example.supermarket.login.view;

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
import com.example.supermarket.databinding.ActivitySignupBinding;
import com.example.supermarket.home.HomeActivity;
import com.example.supermarket.login.data.DatabaseHelper;

public class signupActivity extends AppCompatActivity implements View.OnClickListener {
    TextView goToLoginTV;
    EditText emailET,passwordET,nameET;
    Button signupBtn;
    String email,password,name;
    ActivitySignupBinding binding;
    Intent intent;
    DatabaseHelper db;
    SQLiteDatabase sqLiteDatabase;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //data
        db = new DatabaseHelper(this);
        sqLiteDatabase = db.getReadableDatabase();
        preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        editor = preferences.edit();

        //setup view
        goToLoginTV =binding.goToLoginT;
        emailET =binding.emailET;
        passwordET =binding.passwordET;
        nameET = binding.nameET;
        signupBtn =binding.signupBtn;
        //chang color for text
        SpannableString spannableString = new SpannableString(goToLoginTV.getText());
        int startIndex = goToLoginTV.getText().toString().indexOf("login");
        int color = Color.RED;
        spannableString.setSpan(new ForegroundColorSpan(color), startIndex, startIndex + "login".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        goToLoginTV.setText(spannableString);


        //setupData
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        name = nameET.getText().toString();


        //setup onClick
        goToLoginTV.setOnClickListener(signupActivity.this);
        signupBtn.setOnClickListener(signupActivity.this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.goToLoginT){
            //text
            intent = new Intent(this, loginActivity.class);
            startActivity(intent);
        } else if (id == R.id.signupBtn) {
            email = emailET.getText().toString();
            password = passwordET.getText().toString();
            name = nameET.getText().toString();
            if (email.isEmpty()&&password.isEmpty()&&name.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please Enter the data",Toast.LENGTH_LONG).show();

            }else{
                adduser();
                finish();
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }

        }
    }

    private void adduser() {
        db.insertData(name,email,password);
        editor.putString("user_name",name);
        editor.putString("user_email",email);
        editor.putString("user_password",password);
        editor.putBoolean("isLoading",true);
        editor.commit();
    }
}