package com.example.flixter.models;


import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    int movieId;
    String posterPath;
    String title;
    String overview;
    double rating;
    String releaseDate;

    //empty constructor need by the Parcler library
    public Movie(){};

    public Movie(JSONObject jsonObject) throws JsonRpcException, JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");
        releaseDate = jsonObject.getString("release_date");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException, JsonRpcException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
