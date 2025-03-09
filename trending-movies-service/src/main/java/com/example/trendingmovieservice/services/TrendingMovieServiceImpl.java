package com.example.trendingmovieservice.services;

import com.example.trendingmovieservice.TrendingMovieServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import com.example.trendingmovieservice.CatalogItem;
import com.example.trendingmovieservice.CatalogItemList;
import com.example.trendingmovieservice.Empty;

import java.util.Arrays;
import java.util.List;

@GrpcService
public class TrendingMovieServiceImpl extends TrendingMovieServiceGrpc.TrendingMovieServiceImplBase {

    @Override
    public void getTrendingMovies(Empty request, StreamObserver<CatalogItemList> responseObserver) {
        // Create a list of CatalogItem objects
        List<CatalogItem> catalogItems = Arrays.asList(
                CatalogItem.newBuilder().setName("Movie 1").setDescription("Description 1").setRating(5).build(),
                CatalogItem.newBuilder().setName("Movie 2").setDescription("Description 2").setRating(4).build()
        );

        // Build the response
        CatalogItemList response = CatalogItemList.newBuilder().addAllItems(catalogItems).build();

        // Send the response to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}