package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmedia.events.VideoTrackSizeListener;

import model.Question;
import model.Video;

public class VideoDao {

	protected Connection getDbConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/diy","root","root");
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e + "True 2");
		}
		
		return connection;
	}
	
	public List<Video> selectAllVideosById(int id) {
		String select_videos= "select * from youtube_video where q_id=?";
		List<Video> videos = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_videos);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int video_id = rs.getInt("y_id");
				String url = rs.getString("url");
				String date= rs.getString("date_entered");
				int user_id=rs.getInt("user_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int q_id = rs.getInt("q_id");
				videos.add(new Video(video_id,date,user_id,url,title, description, q_id));
			}
		} catch (SQLException e) {
			System.out.println(e + "True 1");;
		}
		return videos;
	}
	
	public List<Video> hotYoutubes() {
		String select_videos= "SELECT y.* FROM reviews as r JOIN youtube_video as y on y.y_id = r.y_id GROUP BY r.y_id ORDER BY count(*) DESC LIMIT 3";
		List<Video> videos = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_videos);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int video_id = rs.getInt("y_id");
				String url = rs.getString("url");
				String date= rs.getString("date_entered");
				int user_id=rs.getInt("user_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int q_id = rs.getInt("q_id");
				videos.add(new Video(video_id,date,user_id,url,title, description, q_id));
			}
		} catch (SQLException e) {
			System.out.println(e + "True");;
		}
		return videos;
	}
	
	public List<Video> userVideos(int id) {
		String select_videos= "SELECT * FROM youtube_video where user_id = ?";
		List<Video> videos = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_videos);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int video_id = rs.getInt("y_id");
				String url = rs.getString("url");
				String date= rs.getString("date_entered");
				int user_id=rs.getInt("user_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int q_id = rs.getInt("q_id");
				videos.add(new Video(video_id,date,user_id,url,title, description, q_id));
			}
		} catch (SQLException e) {
			System.out.println(e + "True");;
		}
		return videos;
	}
	
	public int addVideo(Video video) {
		String addV = "INSERT INTO youtube_video" +
	            "  (user_id, date_entered, url,title, description, q_id) VALUES " +
	            " (?, ?, ? ,? ,? , ?);";

	        int result = 0;

	        try (Connection connection = getDbConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(addV)) {
	        	preparedStatement.setInt(1, video.getUser_id());
	        	preparedStatement.setString(2, video.getDateToday());
	        	preparedStatement.setString(3, video.getUrl());
	            preparedStatement.setString(4, video.getTitle());
	            preparedStatement.setString(5,video.getDescription());
	            preparedStatement.setInt(6, video.getQ_id());
	            
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // Handle SQL exception
	            System.out.println(e + "Tru 3");
	        }
	        return result;
	}
	
	public Video singleVideo(int id) {
		String video_info = "select * from youtube_video where y_id = ?";
		Video video = null;
		try(Connection connection = getDbConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(video_info);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int video_id = rs.getInt("y_id");
				String url = rs.getString("url");
				String date= rs.getString("date_entered");
				int user_id=rs.getInt("user_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int q_id = rs.getInt("q_id");
				video = new Video(id,date,user_id,url,title, description, q_id);
			}
		} catch (SQLException e) {
			System.out.println(e + "True 4");
		}
		return video;
	}
	
	public int dailyVideo(int id, String dateToday) {
		String select_videos= "SELECT * from youtube_video WHERE user_id=? and date_entered= ?";
		List<Video> videos = new ArrayList<>();
		try (Connection connection = getDbConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_videos);) {
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, dateToday);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int video_id = rs.getInt("y_id");
				String url = rs.getString("url");
				String date= rs.getString("date_entered");
				int user_id=rs.getInt("user_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int q_id = rs.getInt("q_id");
				videos.add(new Video(id,date,user_id,url,title, description, q_id));
			}
		} catch (SQLException e) {
			System.out.println(e + "True 5");;
		}
		return videos.size();
		
	}
}
