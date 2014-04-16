package fr.rbillard.spring_websocket_angularjs.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.rbillard.spring_websocket_angularjs.aspects.NotifyClients;
import fr.rbillard.spring_websocket_angularjs.model.Comment;
import fr.rbillard.spring_websocket_angularjs.service.CommentService;

@RestController
@RequestMapping( "/comments" )
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@RequestMapping( method = RequestMethod.GET )
    public @ResponseBody List<Comment> getMessages() {
        return service.getComments();
    }
	
	@NotifyClients
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Comment update(@PathVariable long id, @RequestBody Comment comment) {
        comment.setId(id);
        Comment out = service.updateComment(comment);
        return out;
    }
	
	@NotifyClients
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Comment add(@RequestBody Comment comment) {
		comment.setDate( /*LocalDateTime.now()*/ new Date() );
        Comment out = service.addComment(comment);
        return out;
    }
	
	@NotifyClients
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        Comment task = new Comment();
        task.setId(id);
        service.deleteComment(task);
    }

}
