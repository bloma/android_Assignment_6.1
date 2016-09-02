package com.example.aphish.movierental.restApi.restImpl;

import com.example.aphish.movierental.domain.Actors;
import com.example.aphish.movierental.restApi.RestAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphish on 2016/08/28.
 */
public class RestActorsAPI implements RestAPI<Actors,Long> {

    final String BASE_URL = "http://localhost//act/";
    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Actors get(Long aLong) {
       final String url = BASE_URL + "actors/" + aLong.toString();
        HttpEntity<Actors> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Actors> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Actors.class);
        Actors actors = responseEntity.getBody();
        return actors;
    }

    @Override
    public String post(Actors entity) {
        final String url = BASE_URL + "actors/create";
        HttpEntity<Actors> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.POST,requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Actors entity) {
        final String url = BASE_URL + "actors/update/" + entity.getId().toString();
        HttpEntity<Actors> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;

    }

    @Override
    public String delete(Actors entity) {
        final String url = BASE_URL + "actors/delete/" + entity.getId().toString();
        HttpEntity<Actors> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.PUT, requestEntity,String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Actors> getAll() {
        List<Actors> actors = new ArrayList<>();
        final String url = BASE_URL+"employees/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Actors[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Actors[].class);
        Actors[] results = responseEntity.getBody();

        for (Actors actor : results) {
            actors.add(actor);
        }
        return actors;
    }
}
