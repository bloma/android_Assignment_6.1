package com.example.aphish.movierental.services.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.aphish.movierental.conf.util.DomainState;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.factories.MoviesFactory;
import com.example.aphish.movierental.repository.factories.MoviesRepository;
import com.example.aphish.movierental.services.MoviesService;

/**
 * Created by Aphish on 2016/05/08.
 */
public class MoviesServiceImpl extends Service implements MoviesService {

    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private MoviesRepository moviesRepository;

    @Override
    public String activateMovie(String name, String duration, String date) {

        if (true){
            Movie movie = MoviesFactory.createMovies(name, duration, date);
            return DomainState.ACTIVATED.name();
        }else{
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isActivated() {
        return moviesRepository.findAll().size() > 0;
    }

    @Override
    public boolean isDeactivated() {
       int rows = moviesRepository.deleteAll();
        return rows > 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder{
        public MoviesServiceImpl getService(){
            return MoviesServiceImpl.this;
        }
    }
}
