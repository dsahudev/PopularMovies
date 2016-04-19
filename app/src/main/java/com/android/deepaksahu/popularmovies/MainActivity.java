package com.android.deepaksahu.popularmovies;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.deepaksahu.popularmovies.Adapters.MoviesAdapter;
import com.android.deepaksahu.popularmovies.Fragments.ListMovieFragment;
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

public class MainActivity extends AppCompatActivity{

    private static final int TAG_POPULAR_MOVIE = 1;
    private static final int TAG_TOP_RATED_MOVIE = 2;

    private static final String POPULARMOVIE_URL = "http://api.themoviedb.org/3/movie/popular?api_key=f2d3b9fb36380e6cb23caf1a605d8207";

    private ArrayList<Movie> Movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //check that the activity is using the layout version with the container_layout_main_activity Coordinate Layout
        if (findViewById(R.id.container_layout_main_activity)!=null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }


            // Create a new Fragment to be placed in the activity layout
            ListMovieFragment listMovieFragment = new ListMovieFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            listMovieFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_layout_main_activity, listMovieFragment).commit();


        }







        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        networkCall();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content_main_recyclerview);
        MoviesAdapter moviesAdapter = new MoviesAdapter(this,Movies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesAdapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(item.isChecked()){
            item.setChecked(true);
        }else{
            item.setChecked(false);
        }


        return super.onOptionsItemSelected(item);
    }


    private void networkCall(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(POPULARMOVIE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");

                    int totalItem = results.length();
                    Movies = new ArrayList<>();

                    for(int i=0;i<totalItem;i++){
                        Movies.add(new Movie(results.getJSONObject(i)));
                    }
                    Toast.makeText(MainActivity.this,"successful", Toast.LENGTH_SHORT).show();

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

}
