package com.moviecatalogservice.resources;

import com.example.trendingmovieservice.CatalogItemList;
import com.moviecatalogservice.services.TrendingMovieClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private TrendingMovieClient trendingMovieClient;

    @GetMapping("/trending-movies")
    public CatalogItemList getTrendingMovies() {
        // Call the gRPC client to get trending movies
        return trendingMovieClient.getTrendingMovies();
    }
}