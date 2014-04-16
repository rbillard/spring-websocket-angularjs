package fr.rbillard.spring_websocket_angularjs.service;

import java.util.List;

import fr.rbillard.spring_websocket_angularjs.model.Comment;

public interface CommentService {

	List<Comment> getComments();

	Comment addComment( Comment message );

	Comment updateComment( Comment message );

	void deleteComment( Comment message );
	
	long count();
  
}
