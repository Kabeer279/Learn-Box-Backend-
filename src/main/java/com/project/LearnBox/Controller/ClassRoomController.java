package com.project.LearnBox.Controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Model.User;
import com.project.LearnBox.Service.ClassService;
import com.project.LearnBox.dto.ClassRoomDto;


@RestController
@RequestMapping(path = "/useraccount")
public class ClassRoomController {

	@Autowired
	ClassService classservice;
	

	@PostMapping(path = "/createclass")
	public ResponseEntity<?> createClass(@RequestBody ClassRoomDto classdto , HttpSession session) {
	
			User user = new User();
			user = (User) session.getAttribute("currentUser");
			System.out.println(user);
			classdto.setUser(user);
	        classservice.createClass(classdto);
	        return new ResponseEntity<>(HttpStatus.CREATED);
   
	}
		
	@GetMapping(path = "/getallclass")
	public ResponseEntity <List<ClassRoom>> getClassess() {
		
    	    try {
    	    		return ResponseEntity.status(OK)
    	                    .body(classservice.getAllClass());
    	        }
    	    catch (Exception e)
    	    	{				
    	    		return (ResponseEntity<List<ClassRoom>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    	    				
    	    	}
	}
	
	@RequestMapping(path = "/joinclass")
	public ResponseEntity<?> joinClass(@RequestBody String classcode ) {
	  	
		ClassRoom room = new ClassRoom();
		room = classservice.joinClass(classcode);
		
    	    	 return ResponseEntity.status(OK).body(room);
    	        
    	    
	}
}
