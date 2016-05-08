package com.example.aphish.movierental.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.services.impl.ActorsServiceImpl;

import junit.framework.Assert;

/**
 * Created by Aphish on 2016/05/08.
 */
public class ActorsServiceTest extends AndroidTestCase {

    private ActorsServiceImpl actorsService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(),ActorsServiceImpl.class);
        App.getAppContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ActorsServiceImpl.ActivateServiceLocalBinder binder
                    =(ActorsServiceImpl.ActivateServiceLocalBinder) service;
            actorsService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
                isBound = false;
        }
    };

    public void testActivateActor() throws Exception {
            String active = actorsService.activateActor("Blom","Aphiwe","1.2","25");
            Assert.assertEquals("ACTIVATED",active);
    }

    public void testisActorActive() throws Exception {
            Boolean active = actorsService.isActivated();
            Assert.assertTrue("ACTIVATED",active);
    }

    public void testDeactivatedActor() throws Exception {
            Boolean deactivate = actorsService.isDeactivated();
            Assert.assertTrue("ACTIVATED",deactivate);
    }
}
