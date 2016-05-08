package com.example.aphish.movierental.services.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.aphish.movierental.conf.util.DomainState;
import com.example.aphish.movierental.domain.Cash;
import com.example.aphish.movierental.factories.CashFactory;
import com.example.aphish.movierental.repository.factories.RentalRepository;
import com.example.aphish.movierental.services.CashService;

/**
 * Created by Aphish on 2016/05/08.
 */
public class CashServiceImpl extends Service implements CashService {

    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private RentalRepository rentalRepository;

    public CashServiceImpl(){}

    @Override
    public String activateCash(double money, String date) {
        if (true){
            Cash cash = CashFactory.createCash(money,date);
            return DomainState.ACTIVATED.name();
        }else{
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isActivated() {
        return rentalRepository.findAll().size()>0;
    }

    @Override
    public boolean isDeactivated() {
        int rows = rentalRepository.deleteAll();
        return rows >0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder{
        public CashServiceImpl getService(){ return CashServiceImpl.this;}
    }
}
