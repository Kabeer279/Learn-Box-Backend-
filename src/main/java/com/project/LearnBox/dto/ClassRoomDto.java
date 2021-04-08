package com.project.LearnBox.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClassRoomDto {

	private Long id;
	private String classname;
	private String description;
	private String classcode;
	private String imgurl;
	//private User user;
}
