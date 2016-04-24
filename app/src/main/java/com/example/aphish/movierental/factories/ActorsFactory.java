package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Actors;

/**
 * Created by Aphish on 2016/04/22.
 */
public class ActorsFactory {
    private static ActorsFactory factory;

    private ActorsFactory(){}

    public static ActorsFactory getInstance(){
        if (factory == null){
            factory = new ActorsFactory();
        }
        return factory;
    }

    public Actors createActors(String name,String surname,String height,String age){
        Actors actors = new Actors
                .Builder()
                .name(name)
                .surname(surname)
                .age(age)
                .height(height)
                .build();
        return actors;
    }
}
