package com.example.aphish.movierental.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.aphish.movierental.conf.util.App;
import com.example.aphish.movierental.services.impl.GenreServiceImpl;

import junit.framework.Assert;

/**
 * Created by Aphish on 2016/05/08.
 */
public class GenreServiceTest extends AndroidTestCase {

    private  GenreServiceImpl genreService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(),GenreServiceImpl.class);
        App.getAppContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GenreServiceImpl.ActivateServiceLocalBinder binder
                    =(GenreServiceImpl.ActivateServiceLocalBinder) service;
            genreService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
                isBound = false;
        }
    };

    public void testActivateGenre() throws Exception {

        String activate = genreService.activateGenre("Horror");
        Assert.assertEquals("ACTIVATED",activate);
    }

    public void testIsActivated() throws Exception {

        Boolean activate = genreService.isActivated();
        Assert.assertTrue("ACTIVATED",activate);
    }

    public void testDeactivate() throws Exception {

        Boolean deactivate = genreService.isDeactivated();
        Assert.assertTrue("ACTIVATED",deactivate);
    }
}
