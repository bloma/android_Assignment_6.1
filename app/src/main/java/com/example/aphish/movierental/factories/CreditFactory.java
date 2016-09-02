package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Cash;
import com.example.aphish.movierental.domain.Credit;
import com.example.aphish.movierental.domain.PaymentType;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CreditFactory {
    private static CreditFactory factory = null;

    public CreditFactory(){}

    public static CreditFactory getInstance(){
        if (factory == null)
            factory = new CreditFactory();
        return factory;
    }

    public static Credit createCredit(Long id,String name,String cardNumber,String securityCode,String pin){
        Credit credit = new Credit
                .Builder()
                .id(id)
                .cardHolderName(name)
                .cardNumber(cardNumber)
                .securityCode(securityCode)
                .pin(pin)
                .build();
        return credit;
    }
}
