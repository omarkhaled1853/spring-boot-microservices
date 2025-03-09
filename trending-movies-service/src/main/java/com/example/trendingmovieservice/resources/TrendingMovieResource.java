package com.example.trendingmovieservice.resources;

import java.net.CacheRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.trendingmovieservice.models.CatalogItem;
import com.example.trendingmovieservice.models.Rating;
import com.example.trendingmovieservice.models.TrendingMovies;
import com.example.trendingmovieservice.services.MovieInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/trending")
public class TrendingMovieResource {

    private final RestTemplate restTemplate;
    private final MovieInfoService movieInfoService;

    public TrendingMovieResource(RestTemplate restTemplate, MovieInfoService movieInfoService) {
        this.restTemplate = restTemplate;
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("/{movieId}")
    public CatalogItem getMovie(@PathVariable String movieId){
        System.out.println(movieId);
        Rating rating = new Rating(movieId, 4);
        return movieInfoService.getCatalogItem(rating);
    }

//    private static final String DB_URL = "jdbc:mysql://localhost:3306/newdb"; // Replace with your DB URL
//    private static final String DB_USER = "root"; // Replace with your DB username
//    private static final String DB_PASSWORD = "Engmido123mrs"; // Replace with your DB password
//
//
//    @GetMapping
//    public TrendingMovies getTrendingMovies(){
//        System.out.println("fdfds");
//        List<String> topMovies = new ArrayList<>();
//
//        String query = "SELECT movie_id, SUM(rating) AS sum_rating " +
//                       "FROM rating " +
//                       "GROUP BY movie_id " +
//                       "ORDER BY sum_rating DESC " +
//                       "LIMIT 3;";
//
//          try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//               Statement stmt = conn.createStatement();
//               ResultSet rs = stmt.executeQuery(query)) {
//
//            // Process the result set and add movie_ids to the list
//            while (rs.next()) {
//                String movieId = rs.getString("movie_id");
//                topMovies.add(movieId);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return new TrendingMovies(topMovies);
//    }
}
