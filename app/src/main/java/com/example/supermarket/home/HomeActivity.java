
package com.example.supermarket.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supermarket.R;
import com.example.supermarket.databinding.ActivityHomeBinding;
import com.example.supermarket.home.Adapters.ImageAdapter;
import com.example.supermarket.home.Adapters.ImagePagerAdapter;
import com.example.supermarket.home.Adapters.Product;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityHomeBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setup view
        ViewPager viewPager = binding.viewPager;
        TextView seeAllTV = binding.seeAllTV;
        RecyclerView recyclerView = binding.recyclerView;

        seeAllTV.setOnClickListener(this);

        //setup adapters
        List<Product> products = new ArrayList<>();
        products.add(new Product("Clean",R.drawable.img1));
        products.add(new Product("Vegetables",R.drawable.img2));
        products.add(new Product("Snacks",R.drawable.img3));
        products.add(new Product("Milks",R.drawable.img4));
        products.add(new Product("Vegetables",R.drawable.img5));
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.img);
        images.add(R.drawable.img2);
        images.add(R.drawable.img3);
        images.add(R.drawable.img4);
        images.add(R.drawable.img5);

        ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(images);
        viewPager.setAdapter(pagerAdapter);

        ImageAdapter imageAdapter = new ImageAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomeActivity.this, ImagesActivty.class);
        startActivity(intent);
    }
}







