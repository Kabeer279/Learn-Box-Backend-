package com.project.LearnBox.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Repository.ClassRepository;
import com.project.LearnBox.dto.ClassRoomDto;

@Service
public class ClassService {

	@Autowired
	ClassRepository classrepo;
	
	
	public void createClass(ClassRoomDto classroomdto) {
		
		ClassRoom classroom = new ClassRoom();
		classroom.setName(classroomdto.getName());
		classroom.setClasscode(classroomdto.getClassCode());
		classroom.setUser(classroomdto.getUser());
		classrepo.save(classroom);
	}
	
	public List<ClassRoom> getAllClass()
	{
		List<ClassRoom> classess = new ArrayList<>();
		classrepo.findAll()
				.forEach(classess::add);
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
