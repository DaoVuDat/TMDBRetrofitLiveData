package com.example.tmdbclient.utils;

import com.example.tmdbclient.service.MovieAPIService;
import com.example.tmdbclient.service.RetrofitClient;

public class MovieAPIUtils {

    private MovieAPIUtils(){}

    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieAPIService getAPIService() {
        return RetrofitClient.getInstance(BASE_URL).create(MovieAPIService.class);
    }

}
