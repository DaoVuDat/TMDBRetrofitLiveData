package com.example.tmdbclient.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.tmdbclient.model.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tmdbclient.R;

public class MovieActivity extends AppCompatActivity {

    private Result movie;
    private ImageView imageView;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.ivMovieLarge);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            movie = intent.getParcelableExtra("movie");
            Toast.makeText(getApplicationContext(), movie.getOriginalTitle(), Toast.LENGTH_SHORT).show();

            image = movie.getPosterPath();

            String path = "https://image.tmdb.org/t/p/w500" + image;

            Glide.with(this)
                    .load(path)
                    .into(imageView);

            getSupportActionBar().setTitle(movie.getTitle());
        }
    }


}