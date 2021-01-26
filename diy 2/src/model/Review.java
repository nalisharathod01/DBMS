package model;

public class Review {
	public Review( int user_id, int y_id, String score, String remark) {
		super();
		this.user_id = user_id;
		this.y_id = y_id;
		this.score = score;
		this.remark = remark;
	}
	
	public Review(int review_id, int user_id, int y_id, String score, String remark) {
		super();
		this.review_id = review_id;
		this.user_id = user_id;
		this.y_id = y_id;
		this.score = score;
		this.remark = remark;
	}
	
	private int review_id;
	private int user_id;
	private int y_id;
	
	public int getY_id() {
		return y_id;
	}
	public void setY_id(int y_id) {
		this.y_id = y_id;
	}
	public String score;
	public String remark;
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
