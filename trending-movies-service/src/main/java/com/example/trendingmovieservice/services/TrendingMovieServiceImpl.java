package com.example.trendingmovieservice.services;

import com.example.trendingmovieservice.TrendingMovieServiceGrpc;
import com.example.trendingmovieservice.db.DatabaseManager;
import com.example.trendingmovieservice.models.Rating;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import com.example.trendingmovieservice.CatalogItem;
import com.example.trendingmovieservice.models.CatalogItemModel;
import com.example.trendingmovieservice.CatalogItemList;
import com.example.trendingmovieservice.Empty;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class TrendingMovieServiceImpl extends TrendingMovieServiceGrpc.TrendingMovieServiceImplBase {

    private final RestTemplate restTemplate;
    private final MovieInfoService movieInfoService;
    private final DatabaseManager databaseManager;

    public TrendingMovieServiceImpl(RestTemplate restTemplate, MovieInfoService movieInfoService, DatabaseManager databaseManager) {
        this.restTemplate = restTemplate;
        this.movieInfoService = movieInfoService;
        this.databaseManager = databaseManager;
    }


    @Override
    public void getTrendingMovies(Empty request, StreamObserver<CatalogItemList> responseObserver) {

        List<Rating> trendingMoviesRate = databaseManager.getTrendingMovies();
        List<CatalogItemModel> trendingMovies = trendingMoviesRate.stream().map(movieInfoService::getCatalogItem).collect(Collectors.toList());

        List<CatalogItem> catalogItems = new ArrayList<>();
        for (CatalogItemModel catalogItemModel : trendingMovies){
            catalogItems.add(
                    CatalogItem.newBuilder()
                               .setName(catalogItemModel.getName())
                               .setDescription(catalogItemModel.getDescription())
                               .setRating(catalogItemModel.getRating())
                               .build()
            );
        }

        CatalogItemList response = CatalogItemList.newBuilder().addAllItems(catalogItems).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}