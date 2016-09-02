package com.example.aphish.movierental.services.impl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.domain.Credit;
import com.example.aphish.movierental.repository.factories.CreditRepository;
import com.example.aphish.movierental.repository.factories.impl.CreditRepositoryImpl;
import com.example.aphish.movierental.services.CreditService;

import java.util.Set;

/**
 * Created by Aphish on 2016/05/08.
 */
public class CreditServiceImpl implements CreditService{

    private final IBinder localBinder = new RentalServiceLocalBinder();
    private CreditRepository repository;
    private static CreditServiceImpl service = null;

    public IBinder onBind(Intent intent){
        return localBinder;
    }

    public static CreditServiceImpl getInstance(){
        if (service == null){
            service = new CreditServiceImpl();
        }
        return service;
    }

    public class RentalServiceLocalBinder extends Binder{
        public CreditServiceImpl getService(){
            return CreditServiceImpl.this;
        }

    }

    public CreditServiceImpl(){
        repository = new CreditRepositoryImpl(App.getAppContext());
    }

    @Override
    public Credit findByID(Long id) {
        if(repository.findById(id) == null){
            return null;
        }else {
            return repository.findById(id);
        }
    }

    @Override
    public Credit save(Credit credit) {
        return repository.save(credit);
    }

    @Override
    public Set<Credit> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Credit entity) {
        repository.delete(entity);
    }

}
