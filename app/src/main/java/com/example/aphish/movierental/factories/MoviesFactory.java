package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Actors;
import com.example.aphish.movierental.domain.Movie;

import java.util.List;

/**
 * Created by Aphish on 2016/04/22.
 */
public class MoviesFactory {
    private static MoviesFactory factory = null;

    public MoviesFactory(){}

    public static MoviesFactory getInstance(){
        if (factory == null)
            factory = new MoviesFactory();
        return factory;
    }

    public static Movie createMovies(Long id, String name, String duration, String date, List<Actors> actors){
        Movie movie = new Movie
                .Builder()
                .id(id)
                .name(name)
                .durationTime(duration)
                .releaseDate(date)
                .actors(actors)
                .build();
        return movie;
    }
}
