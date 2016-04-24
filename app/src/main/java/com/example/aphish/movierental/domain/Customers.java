package com.example.aphish.movierental.domain;

import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */
public class Customers implements Serializable {
    private String name;
    private String surname;
    private String age;

    private Customers(){}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAge() {
        return age;
    }

    public Customers(Builder builder){
        this.name=builder.name;
        this.surname=builder.surname;
        this.age=builder.age;
    }

    public static class Builder{

        private String name;
        private String surname;
        private String age;

        public Builder customerName(String name){
            this.name=name;
            return this;
        }

        public Builder customeruSurname(String surname){
            this.surname=surname;
            return this;
        }

        public Builder customerAge(String age){
            this.age=age;
            return this;
        }

        public Builder copy(Customers customers){
            this.name=customers.name;
            this.surname=customers.surname;
            this.age=customers.age;
            return this;
        }

        public Customers build(){
            return new Customers(this);
        }

    }

}
