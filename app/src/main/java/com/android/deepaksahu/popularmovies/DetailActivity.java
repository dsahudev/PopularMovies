package com.android.deepaksahu.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.deepaksahu.popularmovies.Utils.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle args = getIntent().getExtras();
        movie = (Movie)args.getSerializable("movie");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setAllContent();

    }

    private void setAllContent() {
        ImageView iv = (ImageView)findViewById(R.id.imageview_detailfragment);
        Picasso.with(this).load(movie.getBackdropPath()).placeholder(R.drawable.imageplaceholder).into(iv);

        TextView title_tv = (TextView)findViewById(R.id.movie_title_detailView);
        title_tv.setText(movie.getTitle());

        TextView releaseDate_tv = (TextView)findViewById(R.id.release_date_tv);
        releaseDate_tv.setText(movie.getReleaseDate());

        TextView userRating = (TextView)findViewById(R.id.user_rating_tv);
        userRating.setText(movie.getVoteAverage());

        TextView synopsis_tv = (TextView)findViewById(R.id.synopsis_tv);
        synopsis_tv.setText(movie.getOverview());


    }

}
