package com.example.aphish.movierental.services;

/**
 * Created by Aphish on 2016/05/08.
 */
public interface MoviesService {
    String activateMovie(String name,String duration,String date);
    boolean isActivated();
    boolean isDeactivated();
}
