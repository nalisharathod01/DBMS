package model;

public class Tag {
	private int id;
	private String tagName;
	private int questionId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public Tag(int id, String tagName, int questionId) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.questionId = questionId;
	}
	public Tag(String tagName, int questionId) {
		super();
		this.tagName = tagName;
		this.questionId = questionId;
	}
	
	
}
