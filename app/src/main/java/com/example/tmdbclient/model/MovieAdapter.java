package com.example.tmdbclient.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.tmdbclient.R;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());
        holder.movieRating.setText(String.valueOf(movieArrayList.get(position).getVoteAverage()));

        String imagePath = "https://image.tmdb.org/t/p/w500" + movieArrayList.get(position).getPosterPath();

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);

        circularProgressDrawable.start();

        Glide.with(context)
                .load(imagePath)
                .placeholder(circularProgressDrawable)
                .fitCenter()
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView movieTitle;
        private TextView movieRating;
        private ImageView movieImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.tvTitle);
            movieRating = itemView.findViewById(R.id.tvRating);
            movieImage = itemView.findViewById(R.id.ivMovie);

            itemView.setOnClickListener(this);
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
