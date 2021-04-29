package com.project.LearnBox.Service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LearnBox.Mapper.CommentMapper;
import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Model.Comment;
import com.project.LearnBox.Model.CurrentUser;
import com.project.LearnBox.Model.User;
import com.project.LearnBox.Repository.ClassRepository;
import com.project.LearnBox.Repository.CommentRepository;
import com.project.LearnBox.Repository.CurrentUserRespository;
import com.project.LearnBox.dto.CommentDto;

@Service
public class CommentService {

	@Autowired
	CommentRepository comrepo;
	@Autowired
	CurrentUserRespository cuserrepo;
	@Autowired
	ClassRepository classrepo;
	
	CommentMapper commapper = new CommentMapper();
	
	public CommentDto createComment(CommentDto dto)
	{
	
		Comment com = new Comment();
		
		List<CurrentUser> cuser = new ArrayList<>();
		cuser = (List<CurrentUser>) cuserrepo.findAll(); //to get the current user;
		
		User user = new User();
		user.setId(cuser.get(0).getId());
		user.setName(cuser.get(0).getName());
		user.setPassword(cuser.get(0).getPassword());
		com = commapper.dtoToComment(dto);
		com.setUser(user);
		com.setClassroom(classrepo.findById(dto.getClassId()).get());
		
		System.out.println(com + "service");
		
		CommentDto comdto = commapper.commentToDto(comrepo.save(com));
		comdto.setUsername(cuser.get(0).getName());
		System.out.println(comdto);
		return comdto;
		
	
	}
	
	public List<CommentDto> getAllComments(Long classid)
	{
		ClassRoom classroom = classrepo.findById(classid).get();
		List<Comment> comments = comrepo.findByClassroom(classroom);
		
		System.out.println(comments);
		return comrepo.findByClassroom(classroom)
                .stream()
                .map(commapper::commentToDto).collect(toList());
	}
}
