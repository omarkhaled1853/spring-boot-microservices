package com.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.trendingmovieservice.CatalogItem;
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
    public List<com.moviecatalogservice.models.CatalogItem> getTrendingMovies() {
        CatalogItemList catalogItemList = trendingMovieClient.getTrendingMovies();

        List<com.moviecatalogservice.models.CatalogItem>trendingMovies = new ArrayList<>();

        for(CatalogItem catalogItem: catalogItemList.getItemsList()){
            trendingMovies.add(new com.moviecatalogservice.models.CatalogItem(
                    catalogItem.getName(), catalogItem.getDescription(), catalogItem.getRating()
            ));
        }

        return trendingMovies;
    }
}