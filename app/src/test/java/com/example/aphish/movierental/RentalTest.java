package com.example.aphish.movierental;

import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.domain.Rental;
import com.example.aphish.movierental.factories.CustomersFactory;
import com.example.aphish.movierental.factories.MoviesFactory;
import com.example.aphish.movierental.factories.RentalFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Aphish on 2016/04/22.
 */
public class RentalTest {
    private RentalFactory factory;
    private MoviesFactory movieFactory;
    private CustomersFactory customerFactory;

    @Before
    public void setUp() throws Exception {
        factory = RentalFactory.getInstance();
        customerFactory = CustomersFactory.getInstance();
        movieFactory = MoviesFactory.getInsance();
    }

    @Test
    public void testRentalCreation() throws Exception {

        Movie movie = movieFactory.createMovies("The one","2:20:30","18/02/2016");
        Customers customer = customerFactory.createCustomers("Aphiwe","Blom","23");
        Rental rental = factory.createRental("17/04/2016",movie,customer);
        Assert.assertEquals("Aphiwe",customer.getName());
        Assert.assertEquals("The one",movie.getName());
        Assert.assertEquals("17/04/2016",rental.getRentalDate());
    }

    @Test
    public void testRentalUpdate() throws Exception {

        Movie movie = movieFactory.createMovies("The one","2:20:30","18/02/2016");
        Customers customer = customerFactory.createCustomers("Aphiwe","Blom","23");
        Rental rental = factory.createRental("17/04/2016",movie,customer);

        Rental rent = new Rental
                .Builder()
                .rentalDate("18/05/2016")
                .movie(movie)
                .customers(customer)
                .build();

        Assert.assertEquals("The one",movie.getName());

    }
}
