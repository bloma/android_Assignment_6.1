package com.example.aphish.movierental.repository.factories;

import android.test.AndroidTestCase;

import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.repository.factories.impl.MoviesRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Aphish on 2016/04/24.
 */
public class MoviesRepositoryTest extends AndroidTestCase {

    private static final String TAG = "MOVIE TEST";
    private Long id;

    public void testCreateReadUpdateDelete()throws Exception{

        MoviesRepository moviesRepository = new MoviesRepositoryImpl(this.getContext());

        //CREATE
        Movie createEntity = new Movie.Builder()
                .name("Fast&Furious")
                .releaseDate("12/06/2015")
                .durationTime("1:45:40")
                .build();
        Movie insertedMovie = moviesRepository.save(createEntity);
        id = insertedMovie.getId();
        Assert.assertNotNull(TAG + " CREATE",insertedMovie);

        //READ ALL
        Set<Movie> movies = moviesRepository.findAll();
        Assert.assertTrue(TAG + " READ ALL",movies.size()>0);

        //READ ENTITY
       Movie movie = moviesRepository.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY",movie);

        //UPDATE ENTITY
        Movie updateMovie = new Movie.Builder()
                .copy(movie)
                .name("Fast&Furious 6")
                .build();
        moviesRepository.update(updateMovie);
        Movie newMovie = moviesRepository.findById(id);
        Assert.assertEquals(TAG + " UPDATE","Fast&Furious 6",newMovie.getName());

        //DELETE ENTITY
        moviesRepository.delete(updateMovie);
        Movie deletedMovie = moviesRepository.findById(id);
        Assert.assertNull(TAG + " DELETE",deletedMovie);

    }
}
