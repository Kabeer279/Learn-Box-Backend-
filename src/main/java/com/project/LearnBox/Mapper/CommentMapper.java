package com.project.LearnBox.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.LearnBox.Model.Comment;
import com.project.LearnBox.Model.CurrentUser;
import com.project.LearnBox.Model.User;
import com.project.LearnBox.Repository.ClassRepository;
import com.project.LearnBox.Repository.CurrentUserRespository;
import com.project.LearnBox.dto.CommentDto;


public class CommentMapper {

	@Autowired
	ClassRepository classrepo;
	@Autowired
	CurrentUserRespository cuserrepo;
	
	
	public Comment dtoToComment(CommentDto dto)
	{
		Comment com = new Comment();
		
		com.setId(dto.getId());
		com.setCreateddate(dto.getCreateddate());
		com.setText(dto.getText());
		
		List<CurrentUser> cuser = new ArrayList<>();
		cuser = (List<CurrentUser>) cuserrepo.findAll(); //to get the current user;
		
		User user = new User();
		user.setId(cuser.get(0).getId());
		user.setName(cuser.get(0).getName());
		user.setPassword(cuser.get(0).getPassword());
		
		com.setUser(user);
		com.setClassroom(classrepo.findById(dto.getClassId()).orElse(null));
		
		return com;
	}
	
	public CommentDto commentToDto(Comment com)
	{
		CommentDto dto = new CommentDto();
				
		dto.setId(com.getId());
		dto.setCreateddate(com.getCreateddate());
		dto.setText(com.getText());
		
		return dto;
	}
}
