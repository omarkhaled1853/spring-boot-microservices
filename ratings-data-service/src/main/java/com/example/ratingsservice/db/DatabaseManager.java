package com.example.ratingsservice.db;

import com.example.ratingsservice.models.Rating;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/ratings_db";
    private static final String USER = "omar";
    private static final String PASSWORD = "Omar@2003";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Rating> callGetMovieRatings(String userId) {
        String procedureCall = "{CALL GetMovieRatings(?)}";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(procedureCall)) {

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            List<Rating> ratings = new ArrayList<>();
            while (rs.next()) {
                ratings.add(new Rating(rs.getString("movie_id"), rs.getInt("rating")));
            }

            return ratings;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

//    for testing
//    public static void main(String[] args) {
//        System.out.println(callGetMovieRatings(1)); // Example movie_id
//    }
}
