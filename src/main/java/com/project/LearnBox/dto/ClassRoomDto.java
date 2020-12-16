package com.project.LearnBox.dto;

import com.project.LearnBox.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomDto {

	private String name;
	private String classCode;
	private User user;
}
