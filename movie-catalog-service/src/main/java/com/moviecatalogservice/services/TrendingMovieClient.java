package com.moviecatalogservice.services;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.trendingmovieservice.CatalogItemList;
import com.example.trendingmovieservice.Empty;
import com.example.trendingmovieservice.TrendingMovieServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class TrendingMovieClient {

    private final TrendingMovieServiceGrpc.TrendingMovieServiceBlockingStub blockingStub;

    public TrendingMovieClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext()
                .build();
        blockingStub = TrendingMovieServiceGrpc.newBlockingStub(channel);
    }

    public CatalogItemList getTrendingMovies() {
        Empty request = Empty.newBuilder().build();
        CatalogItemList catalogItemList = blockingStub.getTrendingMovies(request);

        return catalogItemList;
    }
}
