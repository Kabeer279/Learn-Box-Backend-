package com.project.LearnBox.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LearnBox.Mapper.CommentMapper;
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
	
	
	CommentMapper commapper = new CommentMapper();
	
	public void createComment(CommentDto dto)
	{
		System.out.println(dto);
		Comment com = new Comment();
		com = commapper.dtoToComment(dto);
		
		comrepo.save(com);
		
	}
}
