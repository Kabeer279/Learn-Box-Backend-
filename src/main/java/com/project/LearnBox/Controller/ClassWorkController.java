package com.project.LearnBox.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.LearnBox.Model.ClassWork;
import com.project.LearnBox.Service.ClassService;
import com.project.LearnBox.Service.ClassWorkService;
import com.project.LearnBox.dto.ClassRoomDto;

@RestController
@RequestMapping(path = "/dashboard/classwork")
public class ClassWorkController {

	@Autowired
	ClassWorkService classworkservice;
	
	@CrossOrigin
	@PostMapping(path = "/upload/{classId}")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file,@PathVariable Long classId)
	
	{
		System.out.println(file);
		classworkservice.saveFile(file,classId);
		
			return "gooood";
	}
	
	@CrossOrigin
	@GetMapping(path = "/download/{fileId}")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		ClassWork doc = classworkservice.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	@CrossOrigin
	@GetMapping(path = "/nooffiles/{classId}")
	@ResponseBody
	public List<Integer> returnNoOfFiles(@PathVariable Long classId)
	
	{
		//int[]arr = 	classworkservice.nooffiles(classId);
		List<Integer> array = new ArrayList<>();
		array = classworkservice.nooffiles(classId);
		return array;
	}
	
}
