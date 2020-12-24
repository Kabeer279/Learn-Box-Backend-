package com.project.LearnBox.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Model.User;
import com.project.LearnBox.Repository.ClassRepository;
import com.project.LearnBox.dto.ClassRoomDto;

@Service
public class ClassService {

	@Autowired
	ClassRepository classrepo;
	
	
	public void createClass(ClassRoomDto classroomdto,User user) {
		
		ClassRoom classroom = new ClassRoom();
		classroom.setName(classroomdto.getClassname());
		classroom.setDescription(classroomdto.getDescription());
		classroom.setOwner(user);
			Random rnd = new Random();
			int number = rnd.nextInt(999999);
		    classroom.setClasscode(String.format("%06d", number));
		
		classrepo.save(classroom);
		System.out.println("CreatedClass:"+classroom);
	}
	
	public List<ClassRoom> getAllClass()
	{
		List<ClassRoom> classess = new ArrayList<>();
		classrepo.findAll()
				.forEach(classess::add);
		System.out.println(classess);
		return classess;
	}
	
	public ClassRoom joinClass(String classcode)
	{
		ClassRoom room = new ClassRoom();
				
		room = classrepo.findByClasscode(classcode);
                //.orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
		System.out.println(room);
		
		if(room !=null)
		{
			return room;
		}
		else
		{
			return null;
		}
			
		
		
	}
}
