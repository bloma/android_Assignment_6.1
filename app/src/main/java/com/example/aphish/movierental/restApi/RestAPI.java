package com.example.aphish.movierental.restApi;

import java.util.List;

/**
 * Created by Aphish on 2016/08/28.
 */
public interface RestAPI<S,ID> {
    S get(ID id);

    String post(S entity);

    String put(S entity);

    String delete(S entity);

    List<S> getAll();
}
