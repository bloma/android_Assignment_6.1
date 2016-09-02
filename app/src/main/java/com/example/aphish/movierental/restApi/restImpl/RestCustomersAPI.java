package com.example.aphish.movierental.restApi.restImpl;

import com.example.aphish.movierental.domain.Customers;
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
public class RestCustomersAPI implements RestAPI<Customers,Long> {

    final String BASE_URL = "Http://localhost//cust/";
    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Customers get(Long aLong) {
        final String url = BASE_URL + "customers/" + aLong.toString();
        HttpEntity<Customers> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Customers> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Customers.class);
        Customers customers = responseEntity.getBody();
        return customers;
    }

    @Override
    public String post(Customers entity) {
        final String url = BASE_URL + "customers/create";
        HttpEntity<Customers> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Customers entity) {
       final String url = BASE_URL + "customers/update" + entity.getId().toString();
        HttpEntity<Customers> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;

    }

    @Override
    public String delete(Customers entity) {
        final String url = BASE_URL + "customers/delete/" + entity.getId().toString();
        HttpEntity<Customers> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Customers> getAll() {
        List<Customers> customers = new ArrayList<>();
        final String url = BASE_URL + "customers/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Customers[]> responseEntity = restTemplate.exchange(url,HttpMethod.GET,requestEntity,Customers[].class);
        Customers[] results = responseEntity.getBody();

        for(Customers customer : results){
            customers.add(customer);
        }
        return customers;
    }
}
