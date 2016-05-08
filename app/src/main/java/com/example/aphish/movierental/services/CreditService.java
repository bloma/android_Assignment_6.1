package com.example.aphish.movierental.services;

/**
 * Created by Aphish on 2016/05/08.
 */
public interface CreditService {

    String activateCredit(String name,String cardNumber,String securityCode,String pin);
    boolean isActivated();
    boolean isDeactivated();

}
