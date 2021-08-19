package com.project.LearnBox.Security;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.LearnBox.Model.CustomUserDetails;
import com.project.LearnBox.Repository.UserRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
	
		final com.project.LearnBox.Model.User user = this.userrepo.findByName(username); 
		//return new User("admin","123",new ArrayList<>());
		System.out.println(userrepo.findAll());
		if(user==null)
		{ 
			throw new UsernameNotFoundException("User not Found");
		}
		else {
			return  new CustomUserDetails(user);
		}
		
	}

}
