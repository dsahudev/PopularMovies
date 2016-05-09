package com.android.deepaksahu.popularmovies.Utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by deepaksahu on 20/04/16.
 */
public class Movie implements Serializable{

    private static final String BASEURL = "http://image.tmdb.org/t/p/w342/";

    public String getPosterPath() {
        return posterPath;
    }

    private String posterPath;


    public String getOverview() {
        return overview;
    }

    private String overview;


    public String getReleaseDate() {
        return releaseDate;
    }

    private String releaseDate;

    public String getId() {
        return id;
    }

    private String id;

    public String getTitle() {
        return title;
    }

    private String title;

    public String getBackdropPath() {
        return backdropPath;
    }

    private String backdropPath;

    public String getVoteAverage() {
        return voteAverage;
    }

    private String voteAverage;



    public Movie(JSONObject movieJson){
        try {
            this.backdropPath = BASEURL+movieJson.getString("backdrop_path");
            this.posterPath = BASEURL+movieJson.getString("poster_path");
            this.overview = movieJson.getString("overview");
            this.releaseDate = movieJson.getString("release_date");
            this.id = movieJson.getString("id");
            this.title = movieJson.getString("title");
            this.voteAverage = movieJson.getString("vote_average");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(Movie.class.getName(),"Json movie detail parsing error");
            backdropPath = new String();
            posterPath = new String();
            overview = new String();
            releaseDate = new String();
            id = new String();
            title = new String();
            voteAverage = new String();
        }

    }

}
