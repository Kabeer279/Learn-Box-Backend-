package com.project.LearnBox.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LearnBox.Model.User;
import com.project.LearnBox.Repository.UserRepository;
import com.project.LearnBox.dto.UserDto;

@Service
public class UserService {

	@Autowired
	UserRepository userrepo;
	
	public void signUp(UserDto sign)
	{
		User user = new User();
		user.setName(sign.getUsername());
		user.setPassword(sign.getPassword());
		
		userrepo.save(user);
		System.out.println("SignedIn:"+user);
	}
	
	public User login(UserDto login)
	{
		
		User user = new User();
		user = userrepo.findByName(login.getUsername());
		//System.out.println(user);
		if(user != null)
		{		
			
			if(user.getPassword().equals(login.getPassword()) )
			{
				System.out.println("LoggedIn:"+user);
				return user;
			//return "Login Successfull";
			}
		}
		else
		{
			return null;
			//return "Wrong Creditionals";
		}
		return null;
	}
	
	
	
}
