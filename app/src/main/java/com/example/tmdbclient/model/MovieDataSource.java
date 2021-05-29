package com.example.tmdbclient.model;

import android.app.Application;

import androidx.paging.PageKeyedDataSource;

import com.example.tmdbclient.R;
import com.example.tmdbclient.service.MovieAPIService;
import com.example.tmdbclient.service.RetrofitClient;
import com.example.tmdbclient.utils.MovieAPIUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Result> {

    private MovieAPIService movieAPIService;
    private Application application;

    public MovieDataSource(MovieAPIService movieAPIService, Application application) {
        this.movieAPIService = movieAPIService;
        this.application = application;
    }

    @Override
    public void loadAfter(@NotNull LoadParams<Long> loadParams, @NotNull LoadCallback<Long, Result> loadCallback) {
        movieAPIService = MovieAPIUtils.getAPIService();
        Call<MovieResponse> call = movieAPIService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key),loadParams.key);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Result> movies;

                if (movieResponse != null && movieResponse.getResults() != null) {
                    movies = (ArrayList<Result>) movieResponse.getResults();
                    loadCallback.onResult(movies, loadParams.key + 1);
                }


            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NotNull LoadParams<Long> loadParams, @NotNull LoadCallback<Long, Result> loadCallback) {

    }

    @Override
    public void loadInitial(@NotNull LoadInitialParams<Long> loadInitialParams, @NotNull LoadInitialCallback<Long, Result> loadInitialCallback) {
        movieAPIService = MovieAPIUtils.getAPIService();
        Call<MovieResponse> call = movieAPIService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key),1);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Result> movies;

                movies = (ArrayList<Result>) movieResponse.getResults();

                loadInitialCallback.onResult(movies, null, (long) 2);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
