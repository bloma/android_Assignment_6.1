package com.example.aphish.movierental.services;

/**
 * Created by Aphish on 2016/05/08.
 */
public interface GenreService {
    String activateGenre(String name);
    boolean isActivated();
    boolean isDeactivated();
}
