package fr.rbillard.spring_websocket_angularjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.rbillard.spring_websocket_angularjs.model.User;
import fr.rbillard.spring_websocket_angularjs.service.UserService;

@Controller
@RequestMapping( "/" )
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping( method = RequestMethod.GET )
	public String connexion() {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User create(@RequestBody User user) {
        return service.create( user );
    }

}
