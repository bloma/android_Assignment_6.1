package com.example.aphish.movierental.domain;

import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */
public class Rental implements Serializable {
    private long id;
    private String rentalNumber;
    private  String rentalDate;
    private Customers customers;
    private Movie movie;
    private PaymentType paymentType;

    private Rental(){}

    public long getId() {
        return id;
    }

    public String getRentalNumber() {
        return rentalNumber;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public Customers getCustomers() {
        return customers;
    }

    public Movie getMovie() {
        return movie;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Rental(Builder builder){

        this.id = builder.id;
        this.rentalNumber = builder.rentalNumber;
        this.rentalDate = builder.rentalDate;
        this.movie = builder.movie;
        this.customers = builder.customers;
    }

    public static class Builder{

        private long id;
        private String rentalNumber;
        private  String rentalDate;
        private Customers customers;
        private Movie movie;
        private PaymentType paymentType;

        public Builder rentalNumber(String rentalNumber) {
            this.rentalNumber = rentalNumber;
            return this;
        }

        public Builder rentalDate(String rentalDate){
            this.rentalDate = rentalDate;
            return this;
        }

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder customers(Customers customers) {
            this.customers = customers;
            return this;
        }

        public Builder movie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder paymentType(PaymentType paymentType){
            this.paymentType = paymentType;
            return this;
        }

        public Builder copy(Rental rental){
            this.id = rental.id;
            this.rentalDate = rental.rentalDate;
            this.rentalNumber = rental.rentalNumber;
            this.customers = rental.customers;
            this.movie = rental.movie;
            this.paymentType = rental.paymentType;
            return this;
        }

        public Rental build(){
            return new Rental(this);
        }
    }

}
