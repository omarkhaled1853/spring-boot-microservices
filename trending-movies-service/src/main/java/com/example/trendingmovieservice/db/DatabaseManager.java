package com.example.trendingmovieservice.db;

import com.example.trendingmovieservice.models.Rating;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/newdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Engmido123mrs";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Rating> getTrendingMovies(){

        String sql = "{CALL GetTrendingMovies()}";
        List<Rating> ratings = new ArrayList<>();

        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            cstmt = conn.prepareCall(sql);

            rs = cstmt.executeQuery();

            while(rs.next()){
                ratings.add(new Rating(rs.getString(1), (int) rs.getFloat(2)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (cstmt != null) {
                    cstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ratings;
    }
}
