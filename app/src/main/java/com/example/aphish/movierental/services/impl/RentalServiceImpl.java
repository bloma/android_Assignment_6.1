package com.example.aphish.movierental.services.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.conf.util.DomainState;
import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.domain.Rental;
import com.example.aphish.movierental.factories.RentalFactory;
import com.example.aphish.movierental.repository.factories.RentalRepository;
import com.example.aphish.movierental.repository.factories.impl.RentalRepositoryImpl;
import com.example.aphish.movierental.services.RentalService;

public class RentalServiceImpl extends Service implements RentalService{

    private final IBinder localBinder = new RentalServiceLocalBinder();
    private RentalRepository repo;

    public RentalServiceImpl(){}

    @Override
    public IBinder onBind(Intent intent) {

        return localBinder;
    }

    public class RentalServiceLocalBinder extends Binder{
        public RentalServiceImpl getService(){return RentalServiceImpl.this;}
    }

    @Override
    public String activateRental(String date,Movie movie,Customers customer) {
        if (true){
            Rental rental = RentalFactory.createRental(date, movie, customer);
            return DomainState.ACTIVATED.name();
        }else {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isActivated() {

        return repo.findAll().size()>0;
    }

    @Override
    public boolean isDeactivated() {
        int rows = repo.deleteAll();
        return rows>0;
    }
    private Rental createRental(Rental rental){
        repo = new RentalRepositoryImpl(App.getAppContext());
        return repo.save(rental);
    }
}
