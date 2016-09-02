package com.example.aphish.movierental.repository.factories;

import android.test.AndroidTestCase;

import com.example.aphish.movierental.domain.Rental;
import com.example.aphish.movierental.repository.factories.impl.RentalRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Aphish on 2016/04/24.
 */
public class RentalsRepositoryTest extends AndroidTestCase{

    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete()throws Exception{
        RentalRepository rentalRepository = new RentalRepositoryImpl(this.getContext());

        //CREATE
        Rental createEntity = new Rental.Builder()
                .rentalDate("12/07/2016")
                .build();
        Rental insertedEntity = rentalRepository.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + "CREATE",insertedEntity);

        //READ ALL
        Set<Rental> rentals = rentalRepository.findAll();
        Assert.assertTrue(TAG+"READ ALL",rentals.size()>0);

        //READ ENTITY
        Rental rental = rentalRepository.findById(id);
        Assert.assertNotNull(TAG+"READ ENTITY",rental);

        //UPDATE ENTITY
        Rental updateEntity = new Rental.Builder()
                .copy(rental)
                .rentalDate("13/08/2016")
                .build();
        rentalRepository.update(updateEntity);
        Rental newRental = rentalRepository.findById(id);
        Assert.assertEquals(TAG+"UPDATE ENTITY","13/07/2016",newRental.getRentalDate());

        //DELETE ENTITY
        rentalRepository.delete(updateEntity);
        Rental deletedRental = rentalRepository.findById(id);
        Assert.assertNull(TAG + "DELETE",deletedRental);


    }
}
