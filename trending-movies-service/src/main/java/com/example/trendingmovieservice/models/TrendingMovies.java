package com.example.trendingmovieservice.models;

import java.util.List;

public class TrendingMovies {
    private List<String> trendMoviesIds;

    public TrendingMovies(List<String> trendMoviesIds){
        this.trendMoviesIds = trendMoviesIds;
    }

    public List<String> getTrendMoviesIds() {
        return trendMoviesIds;
    }

    public void setTrendMoviesIds(List<String> trendMoviesIds) {
        this.trendMoviesIds = trendMoviesIds;
    }
}
