package com.example.aphish.movierental.restApi.restImpl;

import com.example.aphish.movierental.domain.Movie;
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
public class RestMovieAPI implements RestAPI<Movie,Long> {
    final String BASE_URL = "Http://localhost//movie/";
    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Movie get(Long aLong) {
        final String url = BASE_URL + "movie/" + aLong.toString();
        HttpEntity<Movie> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Movie> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Movie.class);
        Movie movie = responseEntity.getBody();
        return movie;
    }

    @Override
    public String post(Movie entity) {
        final String url = BASE_URL + "movie/create";
        HttpEntity<Movie> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Movie entity) {
        final String url = BASE_URL + "movie/update" + entity.getId().toString();
        HttpEntity<Movie> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;

    }

    @Override
    public String delete(Movie entity) {
        final String url = BASE_URL + "movie/delete/" + entity.getId().toString();
        HttpEntity<Movie> requestEntity = new HttpEntity<>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();
        final String url = BASE_URL + "movie/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Movie[]> responseEntity = restTemplate.exchange(url,HttpMethod.GET,requestEntity,Movie[].class);
        Movie[] results = responseEntity.getBody();

        for(Movie movie : results){
            movies.add(movie);
        }
        return movies;
    }
}
