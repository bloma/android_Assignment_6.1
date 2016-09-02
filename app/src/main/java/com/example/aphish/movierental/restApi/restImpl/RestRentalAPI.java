package com.example.aphish.movierental.restApi.restImpl;

import com.example.aphish.movierental.domain.Rental;
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
public class RestRentalAPI implements RestAPI<Rental,Long> {
    final String BASE_URL = "http://local;host//rent/";
    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Rental get(Long aLong) {
        final String url = BASE_URL + "rental/" + aLong.toString();
        HttpEntity<Rental> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Rental> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Rental.class);
        Rental rental = responseEntity.getBody();
        return rental;
    }

    @Override
    public String post(Rental entity) {
        final String url = BASE_URL + "rental/create";
        HttpEntity<Rental> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.POST, requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;

    }

    @Override
    public String put(Rental entity) {
        final String url = BASE_URL + "rental/update/" + entity.getId().toString();
        HttpEntity<Rental> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(Rental entity) {
        final String url = BASE_URL + "rental/delete/" + entity.getId().toString();
        HttpEntity<Rental> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.POST,requestEntity,String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Rental> getAll() {
        List<Rental> rentals = new ArrayList<>();
        final String url = BASE_URL+"rental/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Rental[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Rental[].class);
        Rental[] results = responseEntity.getBody();

        for (Rental rental : results) {
            rentals.add(rental);
        }
        return rentals;
    }
}
