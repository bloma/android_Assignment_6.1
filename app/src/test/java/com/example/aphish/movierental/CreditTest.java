package com.example.aphish.movierental;

import com.example.aphish.movierental.domain.Credit;
import com.example.aphish.movierental.factories.CreditFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CreditTest {
    private CreditFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = CreditFactory.getInstance();
    }

    @Test
    public void testCreditCreation() throws Exception {
        Credit credit = factory.createCredit("Aphiwe","125623","325674","123");
        Assert.assertEquals("Aphiwe",credit.getCardHolderName());

    }

    @Test
    public void testCreditUpdate() throws Exception {
        Credit credit = factory.createCredit("Aphiwe","125623","325674","123");
        Credit newCredit = new Credit
                .Builder()
                .cardHolderName("Aphiwe")
                .cardNumber("125623")
                .Amount(230)
                .copy(credit)
                .build();
    }
}
