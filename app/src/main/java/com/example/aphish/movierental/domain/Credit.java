package com.example.aphish.movierental.domain;

import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */
public class Credit implements Serializable,PaymentType{

    private Long id;
    private String cardNumber;
    private String pin;
    private String cardHolderName;
    private String securityCode;
    private double amount;

    private Credit(){}

    public Credit(Builder builder){
        this.id=builder.id;
        this.cardHolderName=builder.cardHolderName;
        this.cardNumber=builder.cardNumber;
        this.pin=builder.pin;
        this.securityCode=builder.securityCode;
        this.amount=builder.amount;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    @Override
    public String paymentType() {
        return "payed on credit";
    }

    public static class Builder{
        private Long id;
        private String cardNumber;
        private String pin;
        private String cardHolderName;
        private String securityCode;
        private double amount;

        public Builder id(Long id){
            this.id=id;
            return this;
        }

        public Builder cardNumber(String cardNumber){
            this.cardNumber= cardNumber;
            return this;
        }

        public Builder pin(String pin){
            this.pin=pin;
            return this;
        }

        public Builder cardHolderName(String cardHolderName){
            this.cardHolderName=cardHolderName;
            return this;
        }

        public Builder securityCode(String securityCode){
            this.securityCode=securityCode;
            return this;
        }

        public Builder Amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder copy(Credit credit){
            this.id=credit.id;
            this.cardNumber=credit.cardNumber;
            this.pin=credit.pin;
            this.securityCode=credit.securityCode;
            this.cardHolderName=credit.cardHolderName;
            this.amount=credit.amount;
            return this;
        }

        public Credit build(){
            return new Credit(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credit credit = (Credit) o;

        return id.equals(credit.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
