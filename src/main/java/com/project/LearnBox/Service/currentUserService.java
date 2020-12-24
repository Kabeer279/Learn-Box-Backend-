package com.project.LearnBox.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LearnBox.Model.CurrentUser;
import com.project.LearnBox.Repository.CurrentUserRespository;

@Service
public class currentUserService {
	
	@Autowired
	CurrentUserRespository cuserrepo ;
	
	public void saveCuser(CurrentUser cuser)
	{
		cuserrepo.save(cuser);
	}
}
