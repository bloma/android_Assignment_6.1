package com.example.aphish.movierental.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.services.impl.CashServiceImpl;
import com.example.aphish.movierental.services.impl.CreditServiceImpl;

import junit.framework.Assert;

/**
 * Created by Aphish on 2016/05/08.
 */
public class CashServiceTest extends AndroidTestCase {

    private CashServiceImpl cashService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(),CashServiceImpl.class);
        App.getAppContext().bindService(intent,connection,CashServiceImpl.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CashServiceImpl.ActivateServiceLocalBinder binder
                    =(CashServiceImpl.ActivateServiceLocalBinder) service;
            cashService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
        }
    };

    public void testActivate() throws Exception {

        String activate = cashService.activateCash(60.00,"21/02/2016");
        Assert.assertEquals("ACTIVATED",activate);

    }

    public void testIsActivated() throws Exception {

        Boolean activated = cashService.isActivated();
        Assert.assertTrue("ACTIVATED",activated);
    }

    public void testDeactivate() throws Exception {

        Boolean deactivate = cashService.isDeactivated();
        Assert.assertTrue("ACTIVATED",deactivate);

    }
}
