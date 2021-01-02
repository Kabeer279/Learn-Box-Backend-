package com.project.LearnBox.Controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.LearnBox.Service.CommentService;
import com.project.LearnBox.dto.ClassRoomDto;
import com.project.LearnBox.dto.CommentDto;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

	@Autowired
	CommentService commserv;
	
	@PostMapping(path = "/save")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<?> createComment(@RequestBody CommentDto comment ) {
		
		 try {
			 
			 commserv.createComment(comment);
			 System.out.println(comment);
  		   return ResponseEntity.status(OK)
	                    .body("Comment Created");
  	   }
  	   catch(Exception e){
  		   return ResponseEntity.status(OK)
	                    .body("No Comment");
  	   }
	}
}
