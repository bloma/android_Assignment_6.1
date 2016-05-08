package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.domain.Rental;

import java.util.UUID;

/**
 * Created by Aphish on 2016/04/22.
 */
public class RentalFactory {
    private static RentalFactory factory = null;

    private RentalFactory(){}

    public static RentalFactory getInstance(){
        if (factory == null)
            factory = new RentalFactory();
        return factory;
    }

    public static Rental createRental(String date, Movie movie, Customers customers){
        Rental rental = new Rental
                .Builder()
                .rentalNumber(UUID.randomUUID().toString())
                .rentalDate(date)
                .movie(movie)
                .customers(customers)
                .build();
        return rental;
    }

}
