package com.example.tmdbclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.tmdbclient.R;
import com.example.tmdbclient.databinding.MovieListItemBinding;
import com.example.tmdbclient.model.Result;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private Context context;
    private ArrayList<Result> movieArrayList;
    private ItemClickListener itemClickListener;

    public MovieAdapter(Context context,
                        ArrayList<Result> movieArrayList
//                        ItemClickListener itemClickListener
    ) {
        this.context = context;
        this.movieArrayList = movieArrayList;
//        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);

//        return new ViewHolder(view);
        MovieListItemBinding movieListItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.movie_list_item,
                        parent,
                        false);

        return new ViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Result movie = movieArrayList.get(position);

        holder.binding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        MovieListItemBinding binding;


        public ViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.binding = movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION) {

                itemClickListener.onItemClick(position);
            }

        }
    }
}
