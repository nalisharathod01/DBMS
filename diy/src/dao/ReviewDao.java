package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;
import model.Video;


public class ReviewDao {
	protected Connection getDbConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/diy","root","root1234");

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		return connection;
	}
	
	public boolean isReviewed(int y_id, int user_id) {
		String check_favorite = "select * from reviews where y_id = ? and user_id=?";
    	boolean isAvailable = false;
    	
    	try(Connection connection = getDbConnection();
	    	PreparedStatement preparedStatement = connection.prepareStatement(check_favorite);){
		   	preparedStatement.setInt(1, y_id);
		   	preparedStatement.setInt(2, user_id);
		   	ResultSet rs = preparedStatement.executeQuery();
		   	isAvailable = rs.next();
    	}
    	
    	catch (SQLException e) {
	        // Handle SQL exception
	        System.out.println(e);
    	}
    	return isAvailable;
	}
	
	public Review getReview(int user_id, int y_id) {
		String check_review = "select * from reviews where y_id = ? and user_id=?";
    	boolean isAvailable = false;
    	Review review=null;
    	try(Connection connection = getDbConnection();
	    	PreparedStatement preparedStatement = connection.prepareStatement(check_review);){
		   	preparedStatement.setInt(1, y_id);
		   	preparedStatement.setInt(2, user_id);
		   	ResultSet rs = preparedStatement.executeQuery();
		   	while (rs.next()) {
				int review_id = rs.getInt("review_id");
				int userid= rs.getInt("user_id");
				String title = rs.getString("title");
				String score = rs.getString("score");
				String remark = rs.getString("remark");
				review = new Review(review_id,userid, score, remark);
			}
    	}
    		
    	catch (SQLException e) {
	        // Handle SQL exception
	        System.out.println(e);
    	}
    	return review;
	}
	
	public int review(Review review) {
		String addV = "INSERT INTO reviews" +
	            "  (user_id,y_id, score, remark) VALUES " +
	            " (? ,? ,? , ?);";

	        int result = 0;

	        try (Connection connection = getDbConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(addV)) {

	            preparedStatement.setInt(1, review.getUser_id());
	            preparedStatement.setInt(2,review.getY_id());
	            preparedStatement.setString(3, review.getScore());
	            preparedStatement.setString(4, review.getRemark());
	            
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // Handle SQL exception
	            System.out.println(e);
	        }
	        return result;
	}
	
	public boolean hasMadeReview(int user_id, int y_id) {
		String check_review = "select * from reviews where y_id = ? and user_id=?";
    	boolean isAvailable = false;
    	
    	try(Connection connection = getDbConnection();
	    	PreparedStatement preparedStatement = connection.prepareStatement(check_review);){
		   	preparedStatement.setInt(1, y_id);
		   	preparedStatement.setInt(2, user_id);
		   	ResultSet rs = preparedStatement.executeQuery();
		   	isAvailable = rs.next();
    	}
    	
    	catch (SQLException e) {
	        // Handle SQL exception
	        System.out.println(e);
    	}
    	return isAvailable;
	}
	
	public int updateReview(int user_id, int y_id, String remark, String score) {
		String update_review=" Update reviews set score = ?, remark=? WHERE user_id=? and y_id=?";
		int result = 0;

        try (Connection connection = getDbConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update_review)) {
        	
        	preparedStatement.setString(1, score);
            preparedStatement.setString(2, remark);
            preparedStatement.setInt(3, user_id);
            preparedStatement.setInt(4,y_id);
            
            
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            System.out.println(e);
        }
        return result;
	}
}
