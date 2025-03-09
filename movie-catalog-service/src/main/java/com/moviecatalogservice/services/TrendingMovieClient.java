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
        // Create a channel to connect to the trending-movie service
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext() // For testing only; use SSL/TLS in production
                .build();
        blockingStub = TrendingMovieServiceGrpc.newBlockingStub(channel);
    }

    public CatalogItemList getTrendingMovies() {
        // Call the gRPC service
        Empty request = Empty.newBuilder().build();
        return blockingStub.getTrendingMovies(request);
    }
}
