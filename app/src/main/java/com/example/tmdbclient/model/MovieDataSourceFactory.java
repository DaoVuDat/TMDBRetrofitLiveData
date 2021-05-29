package com.example.tmdbclient.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.tmdbclient.service.MovieAPIService;

import org.jetbrains.annotations.NotNull;

public class MovieDataSourceFactory extends DataSource.Factory{

    private MovieDataSource movieDataSource;
    private MovieAPIService movieAPIService;
    private Application application;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(MovieAPIService movieAPIService, Application application) {
        this.movieAPIService = movieAPIService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource create() {

        movieDataSource = new MovieDataSource(movieAPIService, application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
