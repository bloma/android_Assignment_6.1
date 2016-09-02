package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.domain.Rental;

import java.util.List;
import java.util.UUID;

/**
 * Created by Aphish on 2016/04/22.
 */
public class RentalFactory {
    private static RentalFactory factory = null;

    public RentalFactory(){}

    public static RentalFactory getInstance(){
        if (factory == null)
            factory = new RentalFactory();
        return factory;
    }

    public static Rental createRental(Long id, String date, List<Movie> movies){
        Rental rental = new Rental
                .Builder()
                .id(id)
                .rentalNumber(UUID.randomUUID().toString())
                .rentalDate(date)
                .movies(movies)
                .build();
        return rental;
    }

}
