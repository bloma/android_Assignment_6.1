package com.example.aphish.movierental.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.services.impl.CreditServiceImpl;

import junit.framework.Assert;

/**
 * Created by Aphish on 2016/05/08.
 */
public class CreditServiceTest extends AndroidTestCase {

    private CreditServiceImpl creditService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(),CreditServiceImpl.class);
        App.getAppContext().bindService(intent,connection,CreditServiceImpl.BIND_AUTO_CREATE);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CreditServiceImpl.ActivateServiceLocalBinder binder
                    =(CreditServiceImpl.ActivateServiceLocalBinder) service;
            creditService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

                isBound = false;
        }
    };

    public void testActivate() throws Exception {

        String activate = creditService.activateCredit("Aphiwe","125632145","56124","25623");
        Assert.assertEquals("ACTIVATED",activate);

    }

    public void testIsActivated() throws Exception {

        Boolean activated = creditService.isActivated();
        Assert.assertTrue("ACTIVATED",activated);

    }

    public void testDeactivated() throws Exception {

        Boolean deactivated = creditService.isDeactivated();
        Assert.assertTrue("ACTIVATED",deactivated);

    }
}
