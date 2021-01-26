package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.Tag;

public class TagDao {
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
	
	public List<Tag> popularTags() {
		String select_tags= "SELECT t.* FROM Tags as t JOIN question as q on q.q_id = t.questionid group by t.tagName HAVING count(*) >= ((select count(*) from User)/2)";
		List<Tag> tags = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_tags);) {
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("tagId");
				String tagName = rs.getString("tagName");
				int question_id = rs.getInt("questionId");
				tags.add(new Tag(id, tagName, question_id));
			}
		} catch (SQLException e) {
			System.out.println(e);;
		}
		return tags;
	}
}
