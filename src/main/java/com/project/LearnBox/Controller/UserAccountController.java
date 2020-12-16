package com.project.LearnBox.Controller;


import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Model.User;
import com.project.LearnBox.Service.ClassService;
import com.project.LearnBox.Service.UserService;
import com.project.LearnBox.dto.UserDto;

@RestController
@RequestMapping(path = "/useraccount")
public class UserAccountController {

	@Autowired
	UserService userservice;
		
	@PostMapping(path = "/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDto signUpAccount) {
	  	
    	    	 userservice.signUp(signUpAccount);
    	    	 return new ResponseEntity<>(HttpStatus.CREATED);
    	  
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody UserDto loginAccount , HttpServletRequest request) {
	    	 
		User user = new User();
		user = userservice.login(loginAccount);
		request.getSession().setAttribute("currentUser", user);
    	    	 return ResponseEntity.status(OK)
 	                    .body(user);
       
	}
		
	
	@GetMapping("/check")
	public String check()
	{
		return "hello";
	}
	

	@PostMapping("/checkusers")
	public String checkUser(@RequestBody String id)
	{
		//System.out.println(id);
		return id;  
	}
	
	//public 
	
	
}
