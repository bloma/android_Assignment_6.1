package com.example.aphish.movierental.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aphish on 2016/04/22.
 */
public class Rental implements Serializable {
    private Long id;
    private String rentalNumber;
    private  String rentalDate;
    private List<Movie> movies;




    private Rental(){}

    public Long getId() {
        return id;
    }

    public String getRentalNumber() {
        return rentalNumber;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public List<Movie> getMovies(){
        return movies;
    }

    public Rental(Builder builder){

        this.id = builder.id;
        this.rentalNumber = builder.rentalNumber;
        this.rentalDate = builder.rentalDate;
        this.movies = builder.movies;

    }

    public static class Builder{

        private Long id;
        private String rentalNumber;
        private  String rentalDate;
        private List<Movie> movies;



        public Builder rentalNumber(String rentalNumber) {
            this.rentalNumber = rentalNumber;
            return this;
        }

        public Builder rentalDate(String rentalDate){
            this.rentalDate = rentalDate;
            return this;
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder movies(List<Movie> movies){
            this.movies = movies;
            return this;
        }

        public Builder copy(Rental rental){
            this.id = rental.id;
            this.rentalDate = rental.rentalDate;
            this.rentalNumber = rental.rentalNumber;
            this.movies = rental.movies;

            return this;
        }

        public Rental build(){
            return new Rental(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rental rental = (Rental) o;

        return id.equals(rental.id);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
