package fr.rbillard.spring_websocket_angularjs.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.spring_websocket_angularjs.dto.UserDTO;
import fr.rbillard.spring_websocket_angularjs.model.User;
import fr.rbillard.spring_websocket_angularjs.repository.UserRepository;
import fr.rbillard.spring_websocket_angularjs.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
    private Mapper mapper;

	@Override
	@Transactional
	public User create(User user) {
		UserDTO dto = mapper.map(user, UserDTO.class);
        return mapper.map(repository.saveAndFlush(dto), User.class);
	}

	@Override
	@Transactional( readOnly = true )
	public long count() {
		return repository.count();
	}

}
