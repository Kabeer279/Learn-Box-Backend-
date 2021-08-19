package com.project.LearnBox.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Repository.ClassRepository;

@Service
public class MembersService {

	@Autowired
	ClassRepository classrepo;
	
	public String getclassOwner(String classId)
	{
		List<ClassRoom> classess = new ArrayList<>();
		classrepo.findAll()
				.forEach(classess::add);

		for(ClassRoom clas:classess)
		{
			if(clas.getId().equals(Long.parseLong(classId)))
			{
				return clas.getOwner();
			}
		}
		System.out.println(classId+" sadsad");
		return "no such classCode";
		
	}
	
	public List<String> getclassMembers(String classId)
	{	
		List<String> subscribedUsers = new ArrayList<>();
		List<ClassRoom> classess = new ArrayList<>();
		
		classrepo.findAll()
				.forEach(classess::add);

		for(ClassRoom clas:classess)
		{
			if(clas.getId().equals(Long.parseLong(classId)))
			{
				for(int i=0;i<clas.getSubscribedUsers().size();i++)
				{
					subscribedUsers.add( clas.getSubscribedUsers().get(i).getName());
				}
				return subscribedUsers;
			}
		}
		return subscribedUsers;
		
	}
}
