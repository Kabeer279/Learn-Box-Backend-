package com.project.LearnBox.Controller;

import static org.springframework.http.HttpStatus.OK;

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

import com.project.LearnBox.Service.MembersService;

@RestController
@RequestMapping(path = "/dashboard")
public class MembersController {

	@Autowired
	MembersService memberservice;
	
	
	@CrossOrigin
	@GetMapping(path = "members/owner/{classId}")
	@ResponseBody
	public ResponseEntity <?> classOwner(@PathVariable String classId) {
		
    	   try {
    		   return ResponseEntity.status(OK)
	                    .body(memberservice.getclassOwner(classId));
    	   }
    	   catch(Exception e){
    		   return ResponseEntity.status(OK)
 	                    .body("No Classes");
    	   }
    	   
	}
	
    @CrossOrigin
   	@GetMapping(path = "/members/{classCode}")
  	@ResponseBody
    public ResponseEntity <?> classMembers(@PathVariable String classCode) {
    			
    	   try {
    		   return ResponseEntity.status(OK)
    	               .body(memberservice.getclassMembers(classCode));
    	   }
 	      catch(Exception e){
   		   return ResponseEntity.status(OK)
                        .body("No Classes");
    	   }
    	   
}
}
