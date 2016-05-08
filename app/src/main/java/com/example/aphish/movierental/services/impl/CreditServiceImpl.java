package com.example.aphish.movierental.services.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.aphish.movierental.conf.util.DomainState;
import com.example.aphish.movierental.domain.Credit;
import com.example.aphish.movierental.factories.CreditFactory;
import com.example.aphish.movierental.repository.factories.RentalRepository;
import com.example.aphish.movierental.services.CreditService;

/**
 * Created by Aphish on 2016/05/08.
 */
public class CreditServiceImpl extends Service implements CreditService{

    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private RentalRepository repository;

    @Override
    public String activateCredit(String name, String cardNumber, String securityCode, String pin) {
        if (true){
            Credit credit = CreditFactory.createCredit(name, cardNumber, securityCode, pin);
            return DomainState.ACTIVATED.name();
        }else{
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isActivated() {
        return repository.findAll().size()> 0;
    }

    @Override
    public boolean isDeactivated() {

        int rows = repository.deleteAll();
        return rows>0;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder{
        public CreditServiceImpl getService(){
            return CreditServiceImpl.this;
        }
    }
}
