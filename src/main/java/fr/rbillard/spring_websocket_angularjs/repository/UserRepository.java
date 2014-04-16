package fr.rbillard.spring_websocket_angularjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.rbillard.spring_websocket_angularjs.dto.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

}
