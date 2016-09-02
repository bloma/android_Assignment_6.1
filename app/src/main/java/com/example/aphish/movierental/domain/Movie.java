package com.example.aphish.movierental.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aphish on 2016/04/22.
 */
public class Movie implements Serializable {
    private Long id;
    private String name;
    private String releaseDate;
    private String durationTime;

    List<Actors> actors;

    private Movie(){}

    private Movie(Builder builder){

        this.id = builder.id;
        this.name = builder.name;
        this.releaseDate = builder.releaseDate;
        this.durationTime = builder.durationTime;
        this.actors = builder.actors;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public List<Actors> getActors(){
        return actors;
    }

    public static class Builder{

        private Long id;
        private String name;
        private String releaseDate;
        private String durationTime;
        private List<Actors> actors;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder durationTime(String durationTime) {
            this.durationTime = durationTime;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder releaseDate(String releaseDate){
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder actors(List<Actors> actors){
            this.actors = actors;
            return this;
        }


        public Builder copy(Movie movie){

            this.id = movie.id;
            this.name = movie.name;
            this.durationTime = movie.durationTime;
            this.releaseDate = movie.releaseDate;
            this.actors = movie.actors;

            return this;
        }

        public Movie build(){
            return new Movie(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id.equals(movie.id);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
