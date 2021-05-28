package com.example.tmdbclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tmdbclient.R;
import com.example.tmdbclient.model.Result;

import java.text.NumberFormat;

public class FirstFragment extends Fragment {

    private Result movie;

    private TextView movieTitle, movieSynopsis, movieRating, movieReleaseDate;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        movieTitle = view.findViewById(R.id.tvMovieTitle);
        movieSynopsis = view.findViewById(R.id.tvPlotsynopsis);
        movieRating = view.findViewById(R.id.tvMovieRating);
        movieReleaseDate = view.findViewById(R.id.tvReleaseDate);

        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("movie")) {
            movie = intent.getParcelableExtra("movie");

            movieTitle.setText(movie.getTitle());
            movieSynopsis.setText(movie.getOverview());
            movieRating.setText(String.valueOf(movie.getVoteAverage()));
            movieReleaseDate.setText(movie.getReleaseDate());
        }



        // Inflate the layout for this fragment
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }
}