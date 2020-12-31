package com.project.LearnBox.Service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LearnBox.Mapper.ClassRoomMapper;
import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Model.CurrentUser;
import com.project.LearnBox.Model.User;
import com.project.LearnBox.Repository.ClassRepository;
import com.project.LearnBox.Repository.CurrentUserRespository;
import com.project.LearnBox.dto.ClassRoomDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
public class ClassService {

	@Autowired
	ClassRepository classrepo;
	@Autowired
	CurrentUserRespository cuserrepo;
	
	ClassRoomMapper classmapper = new ClassRoomMapper();
	
	public void createClass(ClassRoomDto classroomdto,User user) {
		
		classroomdto.setClasscode("asasd");
		classroomdto.setId((long) 123);
		//System.out.println(classroomdto);
		
		//System.out.println(classmapper.mapClassDtoToClass(classroomdto));
		ClassRoom classroom = classmapper.mapClassDtoToClass(classroomdto);
		classroom.setOwner(user);
			Random rnd = new Random();
			int number = rnd.nextInt(999999);
		    classroom.setClasscode(String.format("%06d", number));
		classrepo.save(classroom);
		System.out.println("CreatedClass:"+classroom);
	}
	
	public List<ClassRoomDto> getCreatedClassess()
	{
		List<ClassRoom> classess = new ArrayList<>();
		List<ClassRoomDto> classessdto = new ArrayList<>();
		
		
		List<CurrentUser> cuser = new ArrayList<>();
		cuser = (List<CurrentUser>) cuserrepo.findAll(); //to get the current user;
		
		User user = new User();
		user.setId(cuser.get(0).getId());
		user.setName(cuser.get(0).getName());
		user.setPassword(cuser.get(0).getPassword());
		
		
		classrepo.findAll()
				.forEach(classess::add);
		
		for(ClassRoom clas:classess)
		{
			if(clas.getOwner().getName().equals(user.getName()))
			{
				classessdto.add(classmapper.mapClassToClassDto(clas));
			}
		}
		
		System.out.println(classessdto);
		return classessdto;
	}
	
	
	public List<ClassRoomDto> getJoinedClassess()
	{
		List<ClassRoom> classess = new ArrayList<>();
		List<ClassRoomDto> classessdto = new ArrayList<>();
		
		List<CurrentUser> cuser = new ArrayList<>();
		cuser = (List<CurrentUser>) cuserrepo.findAll(); //to get the current user;
		
		User user = new User();
		user.setId(cuser.get(0).getId());
		user.setName(cuser.get(0).getName());
		user.setPassword(cuser.get(0).getPassword());
		
		
		classrepo.findAll()
				.forEach(classess::add);
		
		for(ClassRoom clas:classess)
		{
			if(clas.getSubcribedUsers().contains( user))
			{
				classessdto.add(classmapper.mapClassToClassDto(clas));
			}
		}
		
		System.out.println(classessdto);
		return classessdto;
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
