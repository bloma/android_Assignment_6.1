package com.example.aphish.movierental.domain;

import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */

public class Actors implements Serializable{

    private Long id;
    private String name;
    private String age;
    private String surname;
    private String height;

    private Actors(){}

    public Actors(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.surname=builder.surname;
        this.age=builder.age;
        this.height=builder.height;
    }

    public Long getId(){
        return id;
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

        private Long id;
        private String name;
        private String age;
        private String surname;
        private String height;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

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

            this.id=actors.id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actors actors = (Actors) o;

        if (!id.equals(actors.id)) return false;
        if (name != null ? !name.equals(actors.name) : actors.name != null) return false;
        if (age != null ? !age.equals(actors.age) : actors.age != null) return false;
        if (surname != null ? !surname.equals(actors.surname) : actors.surname != null)
            return false;
        return height != null ? height.equals(actors.height) : actors.height == null;

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
