package com.example.aphish.movierental.domain;

import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */
public class Cash implements Serializable,PaymentType {
    private double cashPayed;
    private String date;

    private Cash(){}

    public Cash(Builder builder){
        this.cashPayed=builder.cashPayed;
        this.date=builder.date;
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
        private double cashPayed;
        private String date;

        public Builder cashPayed(double cashPayed){
            this.cashPayed=cashPayed;
            return this;
        }

        public Builder date(String date){
            this.date=date;
            return this;
        }

        public Builder copy(Cash cash){
            this.cashPayed=cash.cashPayed;
            this.date=cash.date;
            return this;
        }

        public Cash build(){
            return  new Cash(this);
        }


    }

}
