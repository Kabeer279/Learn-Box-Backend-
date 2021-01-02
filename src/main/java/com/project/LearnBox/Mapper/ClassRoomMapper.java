package com.project.LearnBox.Mapper;

import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Repository.ClassRepository;
import com.project.LearnBox.Service.ClassService;
import com.project.LearnBox.dto.ClassRoomDto;
import com.project.LearnBox.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ClassRoomMapper {

	
	 public ClassRoom mapClassDtoToClass(ClassRoomDto classroomdto)
	{
		ClassRoom clas = new ClassRoom();
		clas.setClassname(classroomdto.getClassname());
		clas.setDescription(classroomdto.getDescription());
		
		return clas;
	}
	
	
	public ClassRoomDto mapClassToClassDto(ClassRoom classroom)
	{
		ClassRoomDto dto = new ClassRoomDto();
		dto.setId(classroom.getId());
		dto.setClassname(classroom.getClassname());
		dto.setDescription(classroom.getDescription());
		dto.setClasscode(classroom.getClasscode());
		
		
		return dto;
	}
	
	
	
}
