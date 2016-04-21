package com.android.deepaksahu.popularmovies.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.deepaksahu.popularmovies.Adapters.MoviesAdapter;
import com.android.deepaksahu.popularmovies.MainActivity;
import com.android.deepaksahu.popularmovies.R;
import com.android.deepaksahu.popularmovies.Utils.Movie;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by deepaksahu on 20/04/16.
 */
public class ListMovieFragment extends Fragment {

    private Context mContext;
    private static final String POPULARMOVIE_URL = "http://api.themoviedb.org/3/movie/popular?api_key=f2d3b9fb36380e6cb23caf1a605d8207";
    private static final String TOPRATED_URL =   "http://api.themoviedb.org/3/movie/top_rated?api_key=f2d3b9fb36380e6cb23caf1a605d8207";

    private static final int POPULARMOVIEOPTION = 1;
    private static final int TOPRATEDOPTION = 2;

    private int currentOption = 1;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().toString().equalsIgnoreCase("popular")){
            currentOption = 1;
        }else{
            currentOption = 2;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Movie> Movies;


    public ListMovieFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_main,container,false);
        networkCall(view,POPULARMOVIE_URL);
        return view;
    }

    private void networkCall(final View view, String url){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");

                    int totalItem = results.length();
                    Movies = new ArrayList<>();

                    for(int i=0;i<totalItem;i++){
                        Movies.add(new Movie(results.getJSONObject(i)));
                    }
                   recyclerviewHandler(view);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(MainActivity.class.getName(),"network call json parsing err");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(MainActivity.class.getName(), "network call err");
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    private void recyclerviewHandler(View view){

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.content_main_recyclerview);
        MoviesAdapter moviesAdapter = new MoviesAdapter(mContext,Movies);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        RecyclerView.LayoutManager gridLayoutManager;

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            gridLayoutManager = new GridLayoutManager(mContext, 2);
        }else{
            gridLayoutManager = new GridLayoutManager(mContext, 3);
        }
        gridLayoutManager.isAutoMeasureEnabled();


        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesAdapter);

    }


}
