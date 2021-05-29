package com.example.tmdbclient.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.tmdbclient.databinding.ActivityMovieBinding;
import com.example.tmdbclient.model.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tmdbclient.R;

public class MovieActivity extends AppCompatActivity {

    private Result movie;
    private ActivityMovieBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movie);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            movie = intent.getParcelableExtra("movie");

            binding.setMovie(movie);

            getSupportActionBar().setTitle(movie.getTitle());
        }
    }


}