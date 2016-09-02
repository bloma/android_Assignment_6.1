package com.example.aphish.movierental.services.impl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.domain.Actors;
import com.example.aphish.movierental.repository.factories.ActorsRepository;
import com.example.aphish.movierental.repository.factories.impl.ActorsRepositoryImpl;
import com.example.aphish.movierental.services.ActorsService;

import java.util.Set;

/**
 * Created by Aphish on 2016/05/08.
 */
public class ActorsServiceImpl implements ActorsService {
    private final IBinder localBinder = new ActorServiceLocalBinder();
    private ActorsRepository repository;
    private static ActorsServiceImpl service = null;

    public IBinder onBind(Intent intent){
        return localBinder;
    }

    public static ActorsServiceImpl getInstance(){
        if (service == null){
            service = new ActorsServiceImpl();
        }
        return service;
    }

    public class ActorServiceLocalBinder extends Binder{
        public ActorsServiceImpl getService(){
            return ActorsServiceImpl.this;
        }

    }

    public ActorsServiceImpl(){
        repository = new ActorsRepositoryImpl(App.getAppContext());
    }

    @Override
    public Actors findByID(Long id) {
        if(repository.findById(id) == null){
            return null;
        }else {
           return repository.findById(id);
        }
    }

    @Override
    public Actors save(Actors actors) {
        return repository.save(actors);
    }

    @Override
    public Set<Actors> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Actors entity) {
        repository.delete(entity);
    }
}
