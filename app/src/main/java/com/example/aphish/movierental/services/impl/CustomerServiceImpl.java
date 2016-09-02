package com.example.aphish.movierental.services.impl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.repository.factories.CustomersRepository;
import com.example.aphish.movierental.repository.factories.impl.CustomersRepositoryImpl;
import com.example.aphish.movierental.services.CustomerService;

import java.util.Set;

/**
 * Created by Aphish on 2016/05/08.
 */
public class CustomerServiceImpl implements CustomerService{

    private final IBinder localBinder = new CustomerServiceLocalBinder();
    private CustomersRepository repository;
    private static CustomerServiceImpl service = null;

    public IBinder onBind(Intent intent){
        return localBinder;
    }

    public static CustomerServiceImpl getInstance(){
        if (service == null){
            service = new CustomerServiceImpl();
        }
        return service;
    }

    public class CustomerServiceLocalBinder extends Binder{
        public CustomerServiceImpl getService(){
            return CustomerServiceImpl.this;
        }

    }

    public CustomerServiceImpl(){
        repository = new CustomersRepositoryImpl(App.getAppContext());
    }

    @Override
    public Customers findByID(Long id) {
        if(repository.findById(id) == null){
            return null;
        }else {
            return repository.findById(id);
        }
    }

    @Override
    public Customers save(Customers customers) {
        return repository.save(customers);
    }

    @Override
    public Set<Customers> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Customers entity) {
        repository.delete(entity);
    }
}
