package com.example.aphish.movierental.services;

/**
 * Created by Aphish on 2016/05/08.
 */
public interface CashService {

    String activateCash(double money, String date);
    boolean isActivated();
    boolean isDeactivated();
}
