package com.example.aphish.movierental.services.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.aphish.movierental.conf.util.DomainState;
import com.example.aphish.movierental.domain.Genre;
import com.example.aphish.movierental.factories.GenreFactory;
import com.example.aphish.movierental.repository.factories.MoviesRepository;
import com.example.aphish.movierental.services.GenreService;

/**
 * Created by Aphish on 2016/05/08.
 */
public class GenreServiceImpl extends Service implements GenreService{

    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private MoviesRepository moviesRepository;

    @Override
    public String activateGenre(String name) {
        if (true){
            Genre genre = GenreFactory.createGenre(name);
            return DomainState.ACTIVATED.name();
        }else{
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isActivated() {
        return false;
    }

    @Override
    public boolean isDeactivated() {
        return false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class ActivateServiceLocalBinder extends Binder{
        public GenreServiceImpl getService(){
            return GenreServiceImpl.this;
        }
    }
}
