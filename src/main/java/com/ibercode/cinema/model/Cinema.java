package com.ibercode.cinema.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@DynamoDbBean
public class Cinema {

    private String cinemaId;
    private String name;
    private List<String> movies;

    public Cinema() {
    }

    public Cinema(String cinemaId, String name, List<String> movies) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.movies = movies;
    }

    @DynamoDbPartitionKey
    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMovies() {

        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaId='" + cinemaId + '\'' +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
