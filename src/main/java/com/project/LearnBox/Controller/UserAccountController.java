package com.project.LearnBox.Controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.LearnBox.Config.JwtUtil;
import com.project.LearnBox.Model.AuthenticationRequest;
import com.project.LearnBox.Model.AuthenticationResponse;
import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Model.CurrentUser;
import com.project.LearnBox.Model.User;
import com.project.LearnBox.Repository.CurrentUserRespository;
import com.project.LearnBox.Security.MyUserDetailsService;
import com.project.LearnBox.Service.ClassService;
import com.project.LearnBox.Service.UserService;
import com.project.LearnBox.Service.currentUserService;
import com.project.LearnBox.dto.UserDto;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/useraccount")
public class UserAccountController {

	@Autowired
	UserService userservice;
	@Autowired
	currentUserService cuserservice;
	@Autowired
	CurrentUserRespository cuserrepo ;
	@Autowired
	 AuthenticationManager auth;
	@Autowired
	 MyUserDetailsService userDetailsService;
	@Autowired
	 JwtUtil jwtTokenUtill;
	
	
	
	@PostMapping(path = "/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDto signUpAccount) {
	  	
    	    	 userservice.signUp(signUpAccount);
    	    	 return ResponseEntity.status(OK)
  	                    .body("Signup Successfull");
    
	}
	
	
	@PostMapping(path = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest req) throws Exception {
	  	
		try {
			//System.out.print("auhernf");
    	    	auth.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword()));
    	    	 
		}
		catch(BadCredentialsException e){
			throw new Exception("Incorrect");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
		
		final String jwt  =  jwtTokenUtill.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
    
	}
	
	@PostMapping(path = "/login")
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody UserDto loginAccount ){
	    
		HttpHeaders headers = new HttpHeaders();
		//System.out.println(loginAccount);
		User user = new User();
		CurrentUser cuser = new CurrentUser();  //CurrentUser
		
		user = userservice.login(loginAccount);
		cuser.setId(user.getId());
		cuser.setName(user.getName());
		cuser.setPassword(user.getPassword());
		
		cuserservice.saveCuser(cuser);
	
		if(user!=null)
		{
				//request.getSession().setAttribute("currentUser", user);
    	    	 return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Login Successfull");
		}
		else
		{
			return ResponseEntity.status(OK)
	                    .body("Wrong Creditionals");
		}
     
	}
		
	@DeleteMapping(path = "/logout")
	@ResponseBody
	public ResponseEntity<String> logout() {
	 
		HttpHeaders headers = new HttpHeaders();
		
		cuserrepo.deleteAll();
		System.out.println("LoggedOut");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Logout Successfull");
	
	}
	
	@GetMapping("/check")
	public String check()
	{
		return "hello";
	}
	

//	@PostMapping("/checkusers")
//	public String checkUser(@RequestBody String id)
//	{
//		//System.out.println(id);
//		return id;  
//	}
	
	//public 
	
	
}
