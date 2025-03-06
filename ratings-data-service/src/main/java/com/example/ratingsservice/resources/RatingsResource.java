package com.example.ratingsservice.resources;

import com.example.ratingsservice.db.DatabaseManager;
import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @Autowired
    private final DatabaseManager databaseManager;

    public RatingsResource(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @RequestMapping("/{userId}")
    public UserRating getRatingsOfUser(@PathVariable String userId) {
        List<Rating> ratings = databaseManager.callGetMovieRatings(userId);

        return new UserRating(ratings);
    }
}
