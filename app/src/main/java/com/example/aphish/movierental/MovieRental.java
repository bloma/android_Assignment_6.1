package com.example.aphish.movierental;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.aphish.movierental.mFregments.ActorFregment;
import com.example.aphish.movierental.mFregments.CustomerFregment;
import com.example.aphish.movierental.mFregments.MovieFregment;
import com.example.aphish.movierental.mFregments.RentalFregment;

public class MovieRental extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{

    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rental);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigation = (AHBottomNavigation)findViewById(R.id.myBottomNavigation_Id);
        bottomNavigation.setOnTabSelectedListener(this);
        this.navigationItems();

        // force connection to open
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    private void navigationItems(){
        //crete items
        AHBottomNavigationItem actorItem = new AHBottomNavigationItem("Actor",R.drawable.notification_background);
        AHBottomNavigationItem movieItem = new AHBottomNavigationItem("Movie",R.drawable.notification_background);
        AHBottomNavigationItem rentalItem = new AHBottomNavigationItem("Rental",R.drawable.notification_background);
        AHBottomNavigationItem customerItem = new AHBottomNavigationItem("Customer",R.drawable.notification_background);

        //add items
        bottomNavigation.addItem(actorItem);
        bottomNavigation.addItem(movieItem);
        bottomNavigation.addItem(rentalItem);
        bottomNavigation.addItem(customerItem);

        //set current item
        bottomNavigation.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(int position, boolean wasSelected) {
        //show fragment
        if(position==0){
            ActorFregment actorFregment = new ActorFregment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,actorFregment).commit();
        }else if (position==1){
            CustomerFregment customerFregment = new CustomerFregment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,customerFregment).commit();
        }else if (position==2){
            RentalFregment rentalFregment = new RentalFregment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,rentalFregment).commit();
        }else if (position==3){
            MovieFregment movieFregment = new MovieFregment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,movieFregment).commit();
        }

    }
}
