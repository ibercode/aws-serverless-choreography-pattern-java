package com.ibercode.cinema.model;

import com.ibercode.movies.Movie;

import java.util.List;

public class CinemaResponse {
    private String cinema;
    private List<Movie> movies;

    public CinemaResponse() {
    }

    public CinemaResponse(String cinema, List<Movie> movies) {
        this.cinema = cinema;
        this.movies = movies;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
