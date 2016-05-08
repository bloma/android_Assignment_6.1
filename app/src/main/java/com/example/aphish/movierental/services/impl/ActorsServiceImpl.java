package com.example.aphish.movierental.services.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;


import com.example.aphish.movierental.conf.util.DomainState;
import com.example.aphish.movierental.domain.Actors;
import com.example.aphish.movierental.factories.ActorsFactory;
import com.example.aphish.movierental.repository.factories.MoviesRepository;
import com.example.aphish.movierental.services.ActorsService;

/**
 * Created by Aphish on 2016/05/08.
 */
public class ActorsServiceImpl extends Service implements ActorsService {
    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private MoviesRepository moviesRepository;
    @Override
    public String activateActor(String name, String surname, String height, String age) {
        if (true){
             Actors actors = ActorsFactory.createActors(name, surname, height, age);
            return DomainState.ACTIVATED.name();
        }else{
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isActivated() {

        return moviesRepository.findAll().size()>0;
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
        public ActorsServiceImpl getService(){
            return ActorsServiceImpl.this;
        }
    }
}
