package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DropandCreate {
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
	
	public void dropDB() throws SQLException {
		// JDBC driver name and database URL

		   Connection conn = null;
		   Statement stmt = null;
		   try{

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = getDbConnection();
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Deleting database...");
		      stmt = conn.createStatement();
		      
		      String sql = "DROP DATABASE diy";
		      stmt.executeUpdate(sql);
		      System.out.println("Database deleted successfully...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Have A Nice Day");
	}//end main

	
	public void createDB() throws SQLException {
		 Connection conn = null;
		   Statement stmt = null;
		   try{

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = getDbConnection();
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Deleting database...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE DATABASE diy";
		      stmt.executeUpdate(sql);
		      System.out.println("Database deleted successfully...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Have A Nice Day");
	}
	
	public void createTables() throws SQLException {
		String sql="CREATE TABLE user(\r\n" + 
				"	user_id int primary key auto_increment,\r\n" + 
				"    email varchar(100),\r\n" + 
				"    password varchar(100),\r\n" + 
				"    firstname varchar(100),\r\n" + 
				"    lastname varchar(100),\r\n" + 
				"    gender varchar(10),\r\n" + 
				"    birthday date\r\n" + 
				");";
		
		String sql2="CREATE TABLE question(\r\n" + 
				"	q_id int primary key auto_increment,\r\n" + 
				"    question varchar(500),\r\n" + 
				"    tags varchar(200),\r\n" + 
				"    user_id int,\r\n" + 
				"    foreign key (user_id) references user(user_id)\r\n" + 
				");";
		
		String sql3="CREATE TABLE youtube_video(\r\n" + 
				"	y_id int primary key auto_increment,\r\n" + 
				"    user_id int,\r\n" + 
				"    url varchar(200),\r\n" + 
				"    title varchar(50),\r\n" + 
				"    description varchar(300),\r\n" + 
				"    q_id int,\r\n" + 
				"    date_entered date,\r\n" + 
				"    foreign key (user_id) references user(user_id),\r\n" + 
				"    foreign key (q_id) references question(q_id)\r\n" + 
				");";
		String sql4="create table reviews(\r\n" + 
				"	review_id int primary key auto_increment,\r\n" + 
				"	user_id int,\r\n" + 
				"    y_id int,\r\n" + 
				"    score varchar(20),\r\n" + 
				"    remark varchar(100),\r\n" + 
				"    foreign key (user_id) references user(user_id),\r\n" + 
				"    foreign key (y_id) references youtube_video(y_id)\r\n" + 
				");";
		String sql5="CREATE TABLE favorite(\r\n" + 
				"	user_id int,\r\n" + 
				"    y_id int,\r\n" + 
				"    foreign key (user_id) references user(user_id),\r\n" + 
				"    foreign key (y_id) references youtube_video(y_id)\r\n" + 
				");";
		try(Connection connection = getDbConnection();
			    PreparedStatement preparedStatement = connection.prepareStatement(sql);){
				preparedStatement.executeUpdate(sql);
			}

		try(Connection connection = getDbConnection();
			    PreparedStatement preparedStatement = connection.prepareStatement(sql2);){
				preparedStatement.executeUpdate(sql);
			}
		try(Connection connection = getDbConnection();
			    PreparedStatement preparedStatement = connection.prepareStatement(sql3);){
				preparedStatement.executeUpdate(sql);
			}
		try(Connection connection = getDbConnection();
			    PreparedStatement preparedStatement = connection.prepareStatement(sql4);){
				preparedStatement.executeUpdate(sql);
			}
		try(Connection connection = getDbConnection();
			    PreparedStatement preparedStatement = connection.prepareStatement(sql5);){
				preparedStatement.executeUpdate(sql);
			}
	}
}
