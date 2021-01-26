package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDao {

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
	
	
    public int register(User user) throws ClassNotFoundException {
    	
        String addUser = "INSERT INTO user" +
            "  (firstname, lastname, password, email, gender, birthday) VALUES " +
            " (? ,? ,? , ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/diy","root","root");
            PreparedStatement preparedStatement = connection.prepareStatement(addUser)) {

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setString(6, user.getBirthday());
            
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            System.out.println(e);
        }
        return result;
    }
    
    public boolean ifUserExist(String email) {
    	String check_user = "select email from user where email = ?";
    	boolean isAvailable = false;
    	
    	try(Connection connection = getDbConnection();
	    	PreparedStatement preparedStatement = connection.prepareStatement(check_user))
    	{
		   	preparedStatement.setString(1, email);
		   	ResultSet rs = preparedStatement.executeQuery();
		   	isAvailable = rs.next();
    	}
    	
    	catch (SQLException e) {
	        // Handle SQL exception
	        System.out.println(e);
    	}
    	return isAvailable;
	}
    
    public int validatePassword(String email, String password) {
    	String validate_user = "select email from user where email = ? and password = ?";
    	int a=0;
    	try(Connection connection = getDbConnection();
    	    	PreparedStatement preparedStatement = connection.prepareStatement(validate_user);){
    	    	
    		   	preparedStatement.setString(1, email);
    		   	preparedStatement.setString(2, password);
    		   	ResultSet rs = preparedStatement.executeQuery();
    		   	rs.last(); 
    		   	a = rs.getRow();
    	}
    	
    	catch (SQLException e) {
	        // Handle SQL exception
	        System.out.println(e);
    	}
    	return a;
    }
    
    public User selectUser(String email) {
    	String user_info = "select * from user where email = ?";
		User user = null;
		try(Connection connection = getDbConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(user_info);) {
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("user_id");
				String user_email = rs.getString("email");
				String password = rs.getString("password");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				String birthday = rs.getString("birthday");
				user=new User(id,user_email, password, firstname, lastname, gender, birthday);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return user;
	}
    
}
