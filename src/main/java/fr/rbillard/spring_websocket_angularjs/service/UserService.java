package fr.rbillard.spring_websocket_angularjs.service;

import fr.rbillard.spring_websocket_angularjs.model.User;

public interface UserService {
	
	User create( User user );
	long count();

}
