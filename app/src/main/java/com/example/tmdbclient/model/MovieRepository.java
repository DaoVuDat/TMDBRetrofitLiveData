package com.example.tmdbclient.model;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.tmdbclient.R;
import com.example.tmdbclient.service.MovieAPIService;
import com.example.tmdbclient.utils.MovieAPIUtils;
import com.example.tmdbclient.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Result> movies = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {

        MovieAPIService movieAPIService = MovieAPIUtils.getAPIService();

        Call<MovieResponse> call = movieAPIService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if(movieResponse != null && movieResponse.getResults() != null) {
                        movies = (ArrayList<Result>) movieResponse.getResults();
                        mutableLiveData.setValue(movies);
//                        swipeRefreshLayout.setRefreshing(false);
                    }

                } else {
                    Toast.makeText(application.getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }
}
