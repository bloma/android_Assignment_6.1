package com.example.aphish.movierental.domain;

import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */
public class Customers implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String age;

    private Customers(){}

    public Long getId() {
        return id;
    }

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
        this.id=builder.id;
        this.name=builder.name;
        this.surname=builder.surname;
        this.age=builder.age;
    }

    public static class Builder{

        private Long id;
        private String name;
        private String surname;
        private String age;

        public Builder id(Long id){
            this.id =id;
            return this;
        }

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
            this.id=customers.id;
            this.name=customers.name;
            this.surname=customers.surname;
            this.age=customers.age;
            return this;
        }

        public Customers build(){
            return new Customers(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customers customers = (Customers) o;

        return id.equals(customers.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
