package com.android.deepaksahu.popularmovies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.deepaksahu.popularmovies.R;
import com.android.deepaksahu.popularmovies.Utils.Movie;
import com.squareup.picasso.Picasso;

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
        Movie movie = moviesList.get(position);

//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        ImageRequest imageRequest = new ImageRequest(movie.getPosterPath(), new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//                holder.iv.setImageBitmap(response);
//            }
//        }, 0, 0, null, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        requestQueue.add(imageRequest);

        Picasso.with(context).load(movie.getPosterPath()).into(holder.iv);
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
