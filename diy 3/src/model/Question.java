package model;

public class Question {
	private int id;
	private String question;
	private String tags;
	private int user_id;
	private String dateString;
	
	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Question(String question, String tags, int user_id, String dateString) {
		super();
		this.question = question;
		this.tags = tags;
		this.user_id = user_id;
		this.dateString = dateString;
	} 
	
	public Question(int id, String question, String tags, int user_id,String dateString) {
		super();
		this.id=id;
		this.question = question;
		this.tags = tags;
		this.user_id = user_id;
		this.dateString = dateString;
	} 
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
