package com.example.aphish.movierental.services;

/**
 * Created by Aphish on 2016/05/08.
 */
public interface CustomerService {
    String activateCustomer(String name,String surname,String age);
    boolean isActivated();
    boolean isDeactivated();
}
