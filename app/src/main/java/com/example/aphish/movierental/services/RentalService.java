package com.example.aphish.movierental.services;

import android.content.Context;

import com.example.aphish.movierental.domain.Actors;
import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.domain.Rental;

/**
 * Created by Aphish on 2016/05/07.
 */
public interface RentalService {

   String activateRental(String date, Movie movie, Customers customers);
    boolean isActivated();
    boolean isDeactivated();
}
