package fr.rbillard.spring_websocket_angularjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.rbillard.spring_websocket_angularjs.dto.CommentDTO;

public interface CommentRepository extends JpaRepository<CommentDTO, Long > {

}
