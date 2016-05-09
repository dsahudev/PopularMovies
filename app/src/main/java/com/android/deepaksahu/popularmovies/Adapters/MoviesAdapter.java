package com.android.deepaksahu.popularmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.deepaksahu.popularmovies.DetailActivity;
import com.android.deepaksahu.popularmovies.R;
import com.android.deepaksahu.popularmovies.Utils.Movie;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by deepaksahu on 20/04/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    private Context context;

    public MoviesAdapter(Context context, List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_layout,parent,false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Movie movie = moviesList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle args = new Bundle();
                args.putSerializable("movie", (Serializable) movie);


                Intent i = new Intent(context, DetailActivity.class);

                i.putExtras(args);

                context.startActivity(i);
            }
        });

        Picasso.with(context).load(movie.getPosterPath()).placeholder(R.drawable.imageplaceholder).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageView_movie_card_layout);
        }
    }


}
