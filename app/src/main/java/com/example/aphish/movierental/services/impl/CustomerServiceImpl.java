package com.example.aphish.movierental.services.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.aphish.movierental.conf.util.DomainState;
import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.factories.CustomersFactory;
import com.example.aphish.movierental.repository.factories.RentalRepository;
import com.example.aphish.movierental.services.CustomerService;

/**
 * Created by Aphish on 2016/05/08.
 */
public class CustomerServiceImpl extends Service implements CustomerService{

    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private RentalRepository repository;

    public CustomerServiceImpl(){}

    @Override
    public String activateCustomer(String name, String surname,String age) {
        if(true){
            Customers customers = CustomersFactory.createCustomers(name,surname,age);
            return DomainState.ACTIVATED.name();
        }else {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isActivated() {
        return repository.findAll().size()>0;
    }

    @Override
    public boolean isDeactivated() {
        int rows = repository.deleteAll();
        return rows > 0;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public CustomerServiceImpl getService() {
            return CustomerServiceImpl.this;
        }
    }
}
