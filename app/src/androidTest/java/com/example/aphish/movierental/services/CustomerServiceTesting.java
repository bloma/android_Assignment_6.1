package com.example.aphish.movierental.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.services.impl.CustomerServiceImpl;

import junit.framework.Assert;

/**
 * Created by Aphish on 2016/05/08.
 */
public class CustomerServiceTesting extends AndroidTestCase {

    private CustomerServiceImpl customerService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(),CustomerServiceImpl.class);
        App.getAppContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CustomerServiceImpl.ActivateServiceLocalBinder binder
                    = (CustomerServiceImpl.ActivateServiceLocalBinder) service;
            customerService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testActivateCustomer() throws Exception {

        String active = customerService.activateCustomer("Malikah","Osman","20");
        Assert.assertEquals("ACTIVATED",active);
    }

    public void testIsActivated() throws Exception {

        Boolean activated = customerService.isActivated();
        Assert.assertTrue("ACTIVATED",activated);
    }

    public void testDeactivated() throws Exception {

        Boolean deactivated = customerService.isDeactivated();
        Assert.assertTrue("ACTIVATED",deactivated);

    }
}
