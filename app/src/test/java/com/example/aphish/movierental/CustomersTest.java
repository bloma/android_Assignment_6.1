package com.example.aphish.movierental;

import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.factories.CustomersFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CustomersTest {
    private CustomersFactory factory;
    @Before
    public void setUp() throws Exception {
        factory = CustomersFactory.getInstance();
    }

    @Test
    public void testCustomerCreation() throws Exception {

        Customers customer = factory.createCustomers("Anovuyo","Blom","20");
        Assert.assertEquals("Anovuyo",customer.getName());
    }

    @Test
    public void testCustomerUpdate() throws Exception {

        Customers customer = factory.createCustomers("Anovuyo","Blom","20");
        Customers newCustomer = new Customers
                .Builder()
                .customerName("Anovuyo")
                .customeruSurname("Blom")
                .customerAge("21")
                .copy(customer)
                .build();
        Assert.assertEquals("Anovuyo",customer.getName());

    }

}
