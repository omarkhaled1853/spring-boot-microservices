package com.example.movieinfoservice.service;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(String movieId) {
        return movieRepository.findByMovieId(movieId);
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
