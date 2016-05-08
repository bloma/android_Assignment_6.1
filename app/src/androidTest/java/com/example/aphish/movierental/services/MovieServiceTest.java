package com.example.aphish.movierental.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.services.impl.MoviesServiceImpl;

import junit.framework.Assert;

/**
 * Created by Aphish on 2016/05/08.
 */
public class MovieServiceTest extends AndroidTestCase {

    private MoviesServiceImpl moviesService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(),MoviesServiceImpl.class);
        App.getAppContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MoviesServiceImpl.ActivateServiceLocalBinder binder
                    =(MoviesServiceImpl.ActivateServiceLocalBinder) service;
            moviesService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
        }
    };

    public void testActivateMovie() throws Exception {

        String active = moviesService.activateMovie("WrongTuirn5","1:45:55","15/02/2011");
        Assert.assertEquals("ACTIVATED",active);
    }

    public void testIsActivated() throws Exception {

        Boolean activated = moviesService.isActivated();
        Assert.assertTrue("ACTIVATED",activated);

    }

    public void testDeactivated() throws Exception {

        Boolean deactivated = moviesService.isDeactivated();
        Assert.assertTrue("ACTIVATED",deactivated);
    }
}
