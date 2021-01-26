package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FavoriteDao {
	protected Connection getDbConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/diy","root","root");

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		return connection;
	}
	
	public boolean isFavorite(int y_id, int user_id) {
		String check_favorite = "select * from favorite where y_id = ? and user_id=?";
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
	
	public int insertFavorite(int y_id, int user_id) {
		String addUser = "INSERT INTO favorite" +
	            "  (y_id, user_id) VALUES " +
	            " (? ,?);";

	        int result = 0;

	        try (Connection connection = getDbConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(addUser)) {

	            preparedStatement.setInt(1, y_id);
	            preparedStatement.setInt(2, user_id);
	            
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // Handle SQL exception
	            System.out.println(e);
	        }
	        return result;
	}
	
	public int deleteFavorite(int y_id, int user_id) {
		String delete_fav = "DELETE FROM favorite where y_id=? and user_id=?";

	        int result = 0;

	        try (Connection connection = getDbConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(delete_fav)) {

	            preparedStatement.setInt(1, y_id);
	            preparedStatement.setInt(2, user_id);
	            
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // Handle SQL exception
	            System.out.println(e);
	        }
	        return result;
	}
}
