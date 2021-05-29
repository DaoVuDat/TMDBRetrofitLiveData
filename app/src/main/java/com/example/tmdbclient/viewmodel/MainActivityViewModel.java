package com.example.tmdbclient.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.tmdbclient.model.MovieDataSource;
import com.example.tmdbclient.model.MovieDataSourceFactory;
import com.example.tmdbclient.model.MovieRepository;
import com.example.tmdbclient.model.Result;
import com.example.tmdbclient.service.MovieAPIService;
import com.example.tmdbclient.utils.MovieAPIUtils;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Result>> moviesPagedList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);

        MovieAPIService movieAPIService = MovieAPIUtils.getAPIService();

        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieAPIService, application);
        movieDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        moviesPagedList = (new LivePagedListBuilder<Long, Result>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<List<Result>> getAllMovies() {
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Result>> getMoviesPagedList() {
        return moviesPagedList;
    }
}
