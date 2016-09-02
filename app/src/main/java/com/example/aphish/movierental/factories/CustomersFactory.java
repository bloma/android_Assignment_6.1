package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Customers;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CustomersFactory {
    private static CustomersFactory factory = null;

    public CustomersFactory(){}

    public static CustomersFactory getInstance(){
        if (factory == null)
            factory = new CustomersFactory();
        return factory;
    }

    public static Customers createCustomers(Long id,String name,String surname,String age){
        Customers customers = new Customers
                .Builder()
                .id(id)
                .customerName(name)
                .customeruSurname(surname)
                .customerAge(age)
                .build();
        return customers;
    }
}
