package fr.rbillard.spring_websocket_angularjs.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class CommentDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column
	@NotBlank
    private String content;
	
	@Column
    private long votes;
	
	@Column
	@NotNull
	private /*LocalDateTime*/ Date date;
	
	@ManyToOne
	@JoinColumn( name = "USER_ID" )
	private UserDTO user;
	
	
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
	
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	

}
