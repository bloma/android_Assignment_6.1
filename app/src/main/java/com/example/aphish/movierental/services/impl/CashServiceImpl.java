package com.example.aphish.movierental.services.impl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.domain.Cash;
import com.example.aphish.movierental.repository.factories.CashRepository;
import com.example.aphish.movierental.repository.factories.impl.CashRepositoryImpl;
import com.example.aphish.movierental.services.CashService;

import java.util.Set;

/**
 * Created by Aphish on 2016/05/08.
 */
public class CashServiceImpl implements CashService {

    private final IBinder localBinder = new CashServiceLocalBinder();
    private CashRepository repository;
    private static CashServiceImpl service = null;

    public IBinder onBind(Intent intent){
        return localBinder;
    }

    public static CashServiceImpl getInstance(){
        if (service == null){
            service = new CashServiceImpl();
        }
        return service;
    }

    public class CashServiceLocalBinder extends Binder{
        public CashServiceImpl getService(){
            return CashServiceImpl.this;
        }

    }

    public CashServiceImpl(){
        repository = new CashRepositoryImpl(App.getAppContext());
    }

    @Override
    public Cash findByID(Long id) {
        if(repository.findById(id) == null){
            return null;
        }else {
            return repository.findById(id);
        }
    }

    @Override
    public Cash save(Cash rental) {
        return repository.save(rental);
    }

    @Override
    public Set<Cash> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Cash entity) {
        repository.delete(entity);
    }
}
