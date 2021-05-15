package com.anabneri.netlfix.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Movie {

    @Id
    private String Id;

    private String movieName;
    private String movieType;
    private String principalActor;
    private Integer created_at;

    public Movie(final String id, final String movieName, final String movieType, final String principalActor, final Integer created_at) {
        Id = id;
        this.movieName = movieName;
        this.movieType = movieType;
        this.principalActor = principalActor;
        this.created_at = created_at;
    }

    public String getId() {
        return Id;
    }

    public void setId(final String id) {
        Id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(final String movieType) {
        this.movieType = movieType;
    }

    public String getPrincipalActor() {
        return principalActor;
    }

    public void setPrincipalActor(final String principalActor) {
        this.principalActor = principalActor;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(final Integer created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Movie movie = (Movie) o;
        return Objects.equals(Id, movie.Id) && Objects.equals(movieName, movie.movieName) && Objects.equals(movieType,
            movie.movieType) && Objects.equals(principalActor, movie.principalActor) && Objects.equals(created_at, movie.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, movieName, movieType, principalActor, created_at);
    }

    @Override
    public String toString() {
        return "Movie{" +
            "Id='" + Id + '\'' +
            ", movieName='" + movieName + '\'' +
            ", movieType='" + movieType + '\'' +
            ", principalActor='" + principalActor + '\'' +
            ", created_at=" + created_at +
            '}';
    }

}
