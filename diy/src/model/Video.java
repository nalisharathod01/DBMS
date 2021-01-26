package model;

public class Video {
	
	
	public Video(String dateToday, int user_id, String url, String title, String description, int q_id) {
		super();
		this.dateToday = dateToday;
		this.user_id = user_id;
		this.url = url;
		this.title = title;
		this.description = description;
		this.q_id = q_id;
	}
	
	public Video(int id, String dateToday, int user_id, String url, String title, String description, int q_id) {
		super();
		this.id = id;
		this.dateToday = dateToday;
		this.user_id = user_id;
		this.url = url;
		this.title = title;
		this.description = description;
		this.q_id = q_id;
	}
	
	private int id;
	private String dateToday;
	private int user_id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getDateToday() {
		return dateToday;
	}
	public void setDateToday(String dateToday) {
		this.dateToday = dateToday;
	}

	private String url;
	private String title;
	private String description;
	private int q_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	
}
