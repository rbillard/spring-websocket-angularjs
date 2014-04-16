package fr.rbillard.spring_websocket_angularjs.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.spring_websocket_angularjs.dto.CommentDTO;
import fr.rbillard.spring_websocket_angularjs.model.Comment;
import fr.rbillard.spring_websocket_angularjs.repository.CommentRepository;
import fr.rbillard.spring_websocket_angularjs.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;
	
	@Autowired
    private Mapper mapper;
	
	@Override
	@Transactional( readOnly = true )
	public List<Comment> getComments() {
		
		List<CommentDTO> list = repository.findAll( new Sort( "date" ) );
		
		return list.stream()
			.map( dto -> mapper.map( dto, Comment.class ) )
			.collect( Collectors.toList() );
		
	}

	@Override
	@Transactional
	public Comment addComment(Comment comment) {
		CommentDTO dto = mapper.map(comment, CommentDTO.class);
        return mapper.map(repository.saveAndFlush(dto), Comment.class);
	}

	@Override
	@Transactional
	public Comment updateComment(Comment comment) {
		CommentDTO dto = repository.findOne(comment.getId());
        dto.setVotes(comment.getVotes());
        return mapper.map(repository.saveAndFlush(dto), Comment.class);
	}

	@Override
	@Transactional
	public void deleteComment(Comment comment) {
		repository.delete(comment.getId());
	}

	@Override
	public long count() {
		return repository.count();
	}

}
