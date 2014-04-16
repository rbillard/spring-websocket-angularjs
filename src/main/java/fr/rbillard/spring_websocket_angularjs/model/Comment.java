package fr.rbillard.spring_websocket_angularjs.model;

import java.util.Date;

public class Comment {

	
	private long id;
    private String content;
    private long votes;
	private /*LocalDateTime*/ Date date;
	private User user;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public long getVotes() {
		return votes;
	}
	public void setVotes(long votes) {
		this.votes = votes;
	}
	
	
	public /*LocalDateTime*/ Date getDate() {
		return date;
	}
	public void setDate(/*LocalDateTime*/ Date date) {
		this.date = date;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
