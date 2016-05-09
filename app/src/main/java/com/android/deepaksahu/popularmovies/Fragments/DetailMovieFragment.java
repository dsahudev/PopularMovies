package com.android.deepaksahu.popularmovies.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.deepaksahu.popularmovies.R;
import com.android.deepaksahu.popularmovies.Utils.Movie;


public class DetailMovieFragment extends Fragment {

    public DetailMovieFragment() {
        // Required empty public constructor
    }


    Movie movie;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        movie = (Movie)bundle.getSerializable("movie");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false);
    }

}
