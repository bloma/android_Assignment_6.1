package com.example.aphish.movierental.domain;

import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */
public abstract class Genre implements Serializable{
    private Genre nextGenre;
    private String genreName;

    public abstract String handleMovie(String name);

    public void setNextGenre(Genre nextGenre){
        this.nextGenre = nextGenre;
    }

    public Genre(){}

    private Genre(Builder builder){
        this.genreName = builder.genreName;
        this.nextGenre = builder.nextGenre;
    }

    public Genre getNextGenre() {
        return nextGenre;
    }

    public String getGenreName() {
        return genreName;
    }

    public static class Builder{
        private Genre nextGenre;
        private String genreName;

        public Builder nextGenre(Genre value){
            this.nextGenre=value;
            return this;
        }

        public Builder genreName(String value){
            this.genreName=value;
            return this;
        }

        public Builder copy(Genre value){
            this.genreName=value.genreName;
            this.nextGenre=value.nextGenre;
            return this;
        }

        public Genre build(){
            return new Genre(this) {
                @Override
                public String handleMovie(String name) {
                    return name;
                }
            };
        }

    }

}
