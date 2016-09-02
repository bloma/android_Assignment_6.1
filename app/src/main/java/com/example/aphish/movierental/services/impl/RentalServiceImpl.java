package com.example.aphish.movierental.services.impl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.domain.Rental;
import com.example.aphish.movierental.repository.factories.RentalRepository;
import com.example.aphish.movierental.repository.factories.impl.RentalRepositoryImpl;
import com.example.aphish.movierental.services.RentalService;

import java.util.Set;

//This is an Intent Service
//This application is meant to rent a movie that a customer will choose
//The intent service is good because it wont affect with user experience

public class RentalServiceImpl implements RentalService{

    private final IBinder localBinder = new RentalServiceLocalBinder();
    private RentalRepository repository;
    private static RentalServiceImpl service = null;

    public IBinder onBind(Intent intent){
        return localBinder;
    }

    public static RentalServiceImpl getInstance(){
        if (service == null){
            service = new RentalServiceImpl();
        }
        return service;
    }

    public class RentalServiceLocalBinder extends Binder{
        public RentalServiceImpl getService(){
            return RentalServiceImpl.this;
        }

    }

    public RentalServiceImpl(){
        repository = new RentalRepositoryImpl(App.getAppContext());
    }

    @Override
    public Rental findByID(Long id) {
        if(repository.findById(id) == null){
            return null;
        }else {
            return repository.findById(id);
        }
    }

    @Override
    public Rental save(Rental rental) {
        return repository.save(rental);
    }

    @Override
    public Set<Rental> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Rental entity) {
         repository.delete(entity);
    }
}
