package com.example.aphish.movierental.domain;

import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */

public class Actors implements Serializable{
    private String name;
    private String age;
    private String surname;
    private String height;

    private Actors(){}

    public Actors(Builder builder){
        this.name=builder.name;
        this.surname=builder.surname;
        this.age=builder.age;
        this.height=builder.height;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    public String getHeight() {
        return height;
    }

    public static class Builder{
        private String name;
        private String age;
        private String surname;
        private String height;

        public Builder name(String name){
            this.name=name;
            return this;
        }

        public Builder surname(String surname){
            this.surname=surname;
            return this;
        }

        public Builder age(String age){
            this.age=age;
            return this;
        }

        public Builder height(String height){
            this.height=height;
            return this;
        }

        public Builder copy(Actors actors){
            this.name=actors.name;
            this.surname=actors.surname;
            this.age=actors.age;
            this.height=actors.height;
            return this;
        }

        public Actors build(){
            return new Actors(this);
        }
    }

}
