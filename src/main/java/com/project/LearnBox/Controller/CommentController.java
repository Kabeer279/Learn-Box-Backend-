package com.project.LearnBox.Controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentdto ) {
		 
			 return ResponseEntity.status(OK)
	                    .body(commserv.createComment(commentdto));
  	   
//  	   catch(Exception e){
//  		   return ResponseEntity.status(OK)
//	                    .body("No Comment");
//  	   }
	}
	
	
	@GetMapping(path = "/getallcomments/{classId}")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<List<CommentDto>> getallComment(@PathVariable Long classId) {
		 
			 return ResponseEntity.status(OK)
	                    .body(commserv.getAllComments(classId));
  	   
	}
	
}
