package com.example.movieinfoservice.resources;


import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import com.example.movieinfoservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private RestTemplate restTemplate;

    @Autowired
    private MovieService movieService;

    public MovieResource(RestTemplate restTemplate, MovieService movieService) {
        this.restTemplate = restTemplate;
        this.movieService = movieService;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        // Get the movie info from TMDB
        Movie movie = movieService.getMovieById(movieId);
        if (movie != null) {
            System.out.println("Movie: " + movieId + " from cache");
            return movie;
        } else {
            final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
            MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);

//            Cache movie
            movieService.addMovie(new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview()));

            System.out.println("Movie: " + movieId + " from cloud");
            return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
        }
    }
}
