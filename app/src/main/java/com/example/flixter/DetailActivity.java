package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;


import org.w3c.dom.Text;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    public static final String YOUTUBE_API_KEY = "AIzaSyD2c1vDQbPIOvFT0Kwy9YDHFsvILK31oF8";
    public static final String VIDEOS_URL =  "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tvDetailsTitle;
    TextView tvDetailsOverview;
    RatingBar ratingBar;
    TextView tvReleaseDate;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailsTitle = findViewById(R.id.tvDetailsTitle);
        tvDetailsOverview = findViewById(R.id.tvDetailsOverview);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        ratingBar = findViewById(R.id.ratingBar);
        youTubePlayerView = findViewById(R.id.player);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvDetailsTitle.setText(movie.getTitle());
        tvDetailsOverview.setText(movie.getOverview());
        ratingBar.setRating((float)movie.getRating()/2);

        String releaseDate = "Released " + movie.getReleaseDate();

        tvReleaseDate.setText(releaseDate);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if (results.length() == 0) {
                        return;
                    }
                    String youtubeKey = results.getJSONObject(0).getString("key");
                    Log.d("DetailedActivity", youtubeKey);
                    intializeYoutube(youtubeKey);
                } catch (JSONException e) {
                    Log.e("DetailActivity", "Failed to parse Json");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });


    }

    private void intializeYoutube(String youtubeKey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity", "onInitializationSuccess");
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "onInitializationFailure");
            }
        });
    }


}