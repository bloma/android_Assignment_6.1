package com.example.aphish.movierental.services;

import java.util.Set;

/**
 * Created by Aphish on 2016/09/02.
 */
public interface Service<E,Long> {

    E findByID(Long id);

    E save(E entity);

    void delete(E entity);

    Set<E> findAll();

}
