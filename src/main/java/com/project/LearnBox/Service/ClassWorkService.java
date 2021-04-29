package com.project.LearnBox.Service;

import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Model.ClassWork;
import com.project.LearnBox.Repository.ClassWorkRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClassWorkService {

	@Autowired
	ClassWorkRepository classworkrepo;
	
	
	public ClassWork saveFile(MultipartFile file,Long classId) {
		  String docname = file.getOriginalFilename();
		  try {
			  ClassWork doc = new ClassWork(docname,file.getContentType(),classId,file.getBytes());
			  return classworkrepo.save(doc);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  return null;
	  }
	
	public Optional<ClassWork> getFile(Integer fileId) {
		  return classworkrepo.findById(fileId);
	  }
	
	public List<Integer> nooffiles(Long classId)
	{
		List<ClassWork> docs = new ArrayList<>();
		
		List<Integer> array = new ArrayList<>();
		
		classworkrepo.findAll()
			.forEach(docs::add);
		
		for(ClassWork doc:docs)
		{
			if(doc.getClassId()==classId)
			{
				array.add(doc.getId());
				
			}
		}
		return array;
		
	}
}
