package com.example.aphish.movierental.services.impl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.repository.factories.MoviesRepository;
import com.example.aphish.movierental.repository.factories.impl.MoviesRepositoryImpl;
import com.example.aphish.movierental.services.MoviesService;

import java.util.Set;

//This is a Bound Service
//Bound service offers interaction between client and server
//customers will login and choose a movie to rent


public class MoviesServiceImpl implements MoviesService {

private final IBinder localBinder = new MovieServiceLocalBinder();
private MoviesRepository moviesRepository;

    MoviesServiceImpl(){
        moviesRepository = new MoviesRepositoryImpl(App.getAppContext());
    }

    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    private class MovieServiceLocalBinder extends Binder{
        public MoviesServiceImpl getService(){
            return MoviesServiceImpl.this;
        }
    }

    @Override
    public Movie findByID(Long id) {
        if (moviesRepository.findById(id)==null){
            return null;
        }else {
            return moviesRepository.findById(id);
        }
    }

    @Override
    public Movie save(Movie movie) {
        return moviesRepository.save(movie);
    }

    @Override
    public Set<Movie> findAll() {
        return moviesRepository.findAll();
    }

    @Override
    public void delete(Movie entity) {
        moviesRepository.delete(entity);
    }
}
