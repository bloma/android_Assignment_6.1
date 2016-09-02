package com.example.aphish.movierental.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.services.impl.RentalServiceImpl;

import junit.framework.Assert;

/**
 * Created by Aphish on 2016/05/08.
 */
public class RentalServiceTest extends AndroidTestCase {

    private RentalServiceImpl rentalService;
    private boolean isBound;
    Movie movie;
    Customers customer;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(),RentalServiceImpl.class);
        App.getAppContext().bindService(intent,connection,RentalServiceImpl.BIND_AUTO_CREATE);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RentalServiceImpl.RentalServiceLocalBinder binder
                    =(RentalServiceImpl.RentalServiceLocalBinder) service;
            rentalService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
        }
    };

    public void testActivateRental() throws Exception {

        String activate = rentalService.activateRental("21/06/0216");
        Assert.assertEquals("ACTIVATED",activate);
    }

    public void testIsActivated() throws Exception {

        Boolean activated = rentalService.isActivated();
        Assert.assertTrue("ACTIVATED",activated);
    }

    public void testDeactivated() throws Exception {

        Boolean deactivated = rentalService.isDeactivated();
        Assert.assertTrue("ACTIVATED",deactivated);
    }
}
