package com.example.tmdbclient.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tmdbclient.R;
import com.example.tmdbclient.model.MovieAdapter;
import com.example.tmdbclient.model.MovieResponse;
import com.example.tmdbclient.model.Result;
import com.example.tmdbclient.service.MovieAPIService;
import com.example.tmdbclient.utils.MovieAPIUtils;
import com.example.tmdbclient.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("TMDB Popular Movies Today");

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this::getPopularMovies);

    }

    private void getPopularMovies() {

        // Observe Live Data
        mainActivityViewModel.getAllMovies().observe(this, results -> {
            movies = (ArrayList<Result>) results;
            showOnRecyclerView();
            swipeRefreshLayout.setRefreshing(false);
        });


    }

    private void showOnRecyclerView() {
        recyclerView = findViewById(R.id.rvMovies);

        movieAdapter = new MovieAdapter(this, movies);
//        movieAdapter = new MovieAdapter(this, movies, this);
        // Parse qua Constructor hoac setOnClickListener
        movieAdapter.setOnClickListener(this::onItemClick);


        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.notifyDataSetChanged();
    }


    public void onItemClick(int position) {
        Result selectedMovie = movies.get(position);

        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        intent.putExtra("movie", selectedMovie);
        startActivity(intent);
    }
}