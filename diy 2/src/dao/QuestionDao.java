package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.User;

public class QuestionDao {
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
	
	public int postQuestion(Question question) throws ClassNotFoundException {
    	
        String addUser = "INSERT INTO question" +
            "  (question, tags, user_id, created_on) VALUES " +
            " (? ,?, ?,?);";

        int result = 0;

        try (Connection connection = getDbConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(addUser)) {

            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setString(2, question.getTags());
            preparedStatement.setInt(3, question.getUser_id());
            preparedStatement.setString(4, question.getDateString());
            
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            System.out.println(e);
        }
        return result;
    }
	
	public List<Question> selectAllQuestions() {
		String select_questions= "select * from question";
		List<Question> questions = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_questions);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("q_id");
				String question = rs.getString("question");
				String tags = rs.getString("tags");
				int user_id = rs.getInt("user_id");
				String dateString = rs.getString("created_on");
				questions.add(new Question(id, question, tags, user_id, dateString));
			}
		} catch (SQLException e) {
			System.out.println(e);;
		}
		return questions;
	}
	
	public List<Question> createdToday() {
		String select_questions= "select * from question where created_on = curdate()";
		List<Question> questions = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_questions);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("q_id");
				String question = rs.getString("question");
				String tags = rs.getString("tags");
				int user_id = rs.getInt("user_id");
				String dateString = rs.getString("created_on");
				questions.add(new Question(id, question, tags, user_id, dateString));
			}
		} catch (SQLException e) {
			System.out.println(e);;
		}
		return questions;
	}
	
	
	// number six
	public List<Question> commonQuestions(int user1, int user2) {
		String select_questions= "select q.* from youtube_video as y JOIN question as q on q.q_id = y.q_id WHERE y.user_id in (?,?) GROUP by y.q_id HAVING count(y.q_id) > 1";
		List<Question> questions = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_questions);) {
			
			preparedStatement.setInt(1, user1);
			preparedStatement.setInt(2, user2);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("q_id");
				String question = rs.getString("question");
				String tags = rs.getString("tags");
				int user_id = rs.getInt("user_id");
				String dateString = rs.getString("created_on");
				questions.add(new Question(id, question, tags, user_id, dateString));
			}
		} catch (SQLException e) {
			System.out.println(e);;
		}
		return questions;
	}
	
	public List<Question> poorQuestions() {
		String select_questions= "SELECT q.* FROM reviews as r JOIN youtube_video as y on y.y_id = r.y_id JOIN question as q on q.q_id = y.q_id WHERE r.y_id NOT IN (SELECT y_id FROM reviews where score = \"Excellent\" or score =\"Good\" or score = \"Fair\")";
		List<Question> questions = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_questions);) {
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("q_id");
				String question = rs.getString("question");
				String tags = rs.getString("tags");
				int user_id = rs.getInt("user_id");
				String dateString = rs.getString("created_on");
				questions.add(new Question(id, question, tags, user_id, dateString));
			}
		} catch (SQLException e) {
			System.out.println(e);;
		}
		return questions;
	}
	
	public Question getQuestion(int question_id) {
		String question_info = "select * from question where q_id = ?";
		Question question = null;
		try(Connection connection = getDbConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(question_info);) {
			preparedStatement.setInt(1, question_id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("q_id");
				String questions = rs.getString("question");
				String tags = rs.getString("tags");
				int user_id = rs.getInt("user_id");
				String dateString = rs.getString("created_on");
				question = new Question(id,questions,tags, user_id, dateString);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return question;
	}
	
	public Question topQuestion() {
		String question_info = "SELECT q.* FROM question as q JOIN youtube_video as y on y.q_id = q.q_id GROUP BY q.q_id ORDER BY count(y.y_id) DESC LIMIT 1";
		Question question = null;
		try(Connection connection = getDbConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(question_info);) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("q_id");
				String questions = rs.getString("question");
				String tags = rs.getString("tags");
				int user_id = rs.getInt("user_id");
				String dateString = rs.getString("created_on");
				question = new Question(id,questions,tags, user_id, dateString);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return question;
	}
	
	public List<Question> fuzzySearch(String search) {
		String select_questions = "SELECT * FROM question WHERE question LIKE ? ";
		List<Question> questions = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_questions);) {
			preparedStatement.setString(1, "%" + search + "%");
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("q_id");
				String question = rs.getString("question");
				String tags = rs.getString("tags");
				int user_id = rs.getInt("user_id");
				String dateString = rs.getString("created_on");
				questions.add(new Question(id, question, tags, user_id, dateString));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return questions;
	}
}