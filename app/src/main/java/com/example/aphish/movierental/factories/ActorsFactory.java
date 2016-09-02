package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Actors;

/**
 * Created by Aphish on 2016/04/22.
 */
public class ActorsFactory {
    private static ActorsFactory factory = null;

    public ActorsFactory(){}

    public static ActorsFactory getInstance(){
        if (factory == null){
            factory = new ActorsFactory();
        }
        return factory;
    }

    public static Actors createActors(Long id,String name,String surname,String height,String age){
        Actors actors = new Actors
                .Builder()
                .id(id)
                .name(name)
                .surname(surname)
                .age(age)
                .height(height)
                .build();
        return actors;
    }
}
