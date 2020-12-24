package com.project.LearnBox.Controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Model.CurrentUser;
import com.project.LearnBox.Model.User;
import com.project.LearnBox.Repository.ClassRepository;
import com.project.LearnBox.Repository.CurrentUserRespository;
import com.project.LearnBox.Service.ClassService;
import com.project.LearnBox.dto.ClassRoomDto;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/dashboard")
public class ClassRoomController {

	@Autowired
	ClassService classservice;
	@Autowired
	ClassRepository classrepo;
	@Autowired
	CurrentUserRespository cuserrepo;
	 
	//(origins = "http://localhost:4200", allowCredentials = "true")
	@PostMapping(path = "/createclass")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<?> createClass(@RequestBody ClassRoomDto classdto , HttpServletRequest req) {
		HttpHeaders headers = new HttpHeaders();
		
			//System.out.println(classdto);
			User user = new User();
			List<CurrentUser> cuser = new ArrayList<>();
			
			cuser = (List<CurrentUser>) cuserrepo.findAll(); //to get the current user;
				//user = (User) req.getSession().getAttribute("currentUser");
			user.setId(cuser.get(0).getId());
			user.setName(cuser.get(0).getName());
			user.setPassword(cuser.get(0).getPassword());
			
			
			classservice.createClass(classdto , user);
			return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Login Successfull");
//			return ResponseEntity.status(OK)	                    .body("Class Created");
   
	}
	
	@CrossOrigin
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
	
	@CrossOrigin
	@PostMapping(path = "/joinclass")
	public ResponseEntity<?> joinClass(@RequestBody String classcode) {
	  	
		User user = new User();
		List<CurrentUser> cuser = new ArrayList<>();
		cuser = (List<CurrentUser>) cuserrepo.findAll();
		user.setId(cuser.get(0).getId());
		user.setName(cuser.get(0).getName());
		user.setPassword(cuser.get(0).getPassword());
		//current user!! up
		//user = (User) session.getAttribute("currentUser");
		//System.out.println(classcode);
		ClassRoom room = new ClassRoom();
		room = classservice.joinClass(classcode);
		room.setSubUsers(user);
		
		classrepo.save(room);
		
		
    	    	 return ResponseEntity.status(OK).body(room);
    	        
    	    
	}
	
	@CrossOrigin
	@PostMapping("/checkusers")
	public String checkUser(@RequestBody String id)
	{
		System.out.println(id);
		return id;  
	}
}
