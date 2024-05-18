package com.example.supermarket.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.supermarket.R;
import com.example.supermarket.databinding.ActivityImagesBinding;
import com.example.supermarket.home.Adapters.ImageAdapter;
import com.example.supermarket.home.Adapters.Product;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivty extends AppCompatActivity {
    ActivityImagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView recyclerView = binding.recyclerView;

        List<Product> products = new ArrayList<>();
        products.add(new Product("Clean",R.drawable.img1));
        products.add(new Product("Vegetables",R.drawable.img2));
        products.add(new Product("Snacks",R.drawable.img3));
        products.add(new Product("Milks",R.drawable.img4));
        products.add(new Product("Vegetables",R.drawable.img5));
        ImageAdapter imageAdapter = new ImageAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(imageAdapter);
    }
}