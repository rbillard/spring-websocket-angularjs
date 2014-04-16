package fr.rbillard.spring_websocket_angularjs.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;
	
	@Column
	private String login;
	
	@OneToMany( mappedBy = "user" )
	private List<CommentDTO> comments;

	
	public long getId() {
		return id;
	}
	public void setId( long id ) {
		this.id = id;
	}

	
	public String getLogin() {
		return login;
	}
	public void setLogin( String login ) {
		this.login = login;
	}
	
	
	public List<CommentDTO> getComments() {
		return comments;
	}
	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
	
	
}
