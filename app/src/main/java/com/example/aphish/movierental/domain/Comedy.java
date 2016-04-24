package com.example.aphish.movierental.domain;

/**
 * Created by Aphish on 2016/04/22.
 */
public class Comedy extends Genre {
    @Override
    public String handleMovie(String name) {
        return name;
    }
}
