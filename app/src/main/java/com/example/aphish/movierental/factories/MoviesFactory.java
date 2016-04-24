package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Movie;

/**
 * Created by Aphish on 2016/04/22.
 */
public class MoviesFactory {
    private static MoviesFactory factory = null;

    private MoviesFactory(){}

    public static MoviesFactory getInsance(){
        if (factory == null)
            factory = new MoviesFactory();
        return factory;
    }

    public Movie createMovies(String name,String duration,String date){
        Movie movie = new Movie
                .Builder()
                .name(name)
                .durationTime(duration)
                .releaseDate(date)
                .build();
        return movie;
    }
}
