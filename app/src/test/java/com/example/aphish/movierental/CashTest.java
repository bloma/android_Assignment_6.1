package com.example.aphish.movierental;

import com.example.aphish.movierental.domain.Cash;
import com.example.aphish.movierental.factories.CashFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CashTest {
    private CashFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = CashFactory.getInstance();
    }

    @Test
    public void testCashCreation() throws Exception {
        Cash cash = factory.createCash(230,"23/02/2016");
        Assert.assertEquals(230,cash.getCashPayed(),0.0);
    }

    @Test
    public void testCashUpdate() throws Exception {
        Cash cash = factory.createCash(230,"23/02/2016");
        Cash newCash = new Cash
                .Builder()
                .cashPayed(300)
                .date("23/03/2016")
                .copy(cash)
                .build();
        Assert.assertEquals(230,cash.getCashPayed(),0.00);
    }
}
