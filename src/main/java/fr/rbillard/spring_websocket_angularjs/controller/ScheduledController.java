package fr.rbillard.spring_websocket_angularjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import fr.rbillard.spring_websocket_angularjs.service.CommentService;
import fr.rbillard.spring_websocket_angularjs.service.UserService;

@Configuration
@EnableScheduling
public class ScheduledController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Scheduled( fixedRate = 60000 )
	public void printInfos() {

		System.out.println( "Nombre d'utilisateurs = " + userService.count() );
		System.out.println( "Nombre de commentaires = " + commentService.count() );
		
	}
	
}
