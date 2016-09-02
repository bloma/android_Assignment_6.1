package com.example.aphish.movierental.domain;

import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */
public class Cash implements Serializable,PaymentType {

    private Long id;
    private double cashPayed;
    private String date;

    private Cash(){}

    public Cash(Builder builder){
        this.id=builder.id;
        this.cashPayed=builder.cashPayed;
        this.date=builder.date;
    }

    public Long getId() {
        return id;
    }

    public double getCashPayed() {
        return cashPayed;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String paymentType() {
        return "cash payed";
    }

    public static class Builder{

        private Long id;
        private double cashPayed;
        private String date;

        public Builder id(Long id){
            this.id=id;
            return this;
        }

        public Builder cashPayed(double cashPayed){
            this.cashPayed=cashPayed;
            return this;
        }

        public Builder date(String date){
            this.date=date;
            return this;
        }

        public Builder copy(Cash cash){
            this.id=cash.id;
            this.cashPayed=cash.cashPayed;
            this.date=cash.date;
            return this;
        }

        public Cash build(){
            return  new Cash(this);
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cash cash = (Cash) o;

        return id.equals(cash.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
