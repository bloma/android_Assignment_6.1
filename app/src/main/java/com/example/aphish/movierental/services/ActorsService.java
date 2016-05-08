package com.example.aphish.movierental.services;

/**
 * Created by Aphish on 2016/05/08.
 */
public interface ActorsService {

    String activateActor(String name,String surname,String height,String age);
    boolean isActivated();
    boolean isDeactivated();
}
