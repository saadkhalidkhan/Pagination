package com.example.hafiz_saad.pagination;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;

    public Movie(String title){
        this.title = title;
    }

    public Movie() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static List<Movie> createMovie(int itemCount){
        List<Movie> movies = new ArrayList<>();
        for (int i=0; i<20; i++){
            Movie movie = new Movie( "Movie " + (itemCount == 0 ?
                    (itemCount + 1 + i) : (itemCount + i)));
            movies.add(movie);
        }
        return movies;
    }
}
