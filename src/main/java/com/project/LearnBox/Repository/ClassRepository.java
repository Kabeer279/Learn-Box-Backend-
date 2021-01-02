package com.project.LearnBox.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.LearnBox.Model.ClassRoom;

@Repository
public interface ClassRepository extends JpaRepository<ClassRoom,Long> {
		
	
	
	public ClassRoom findByClasscode(String classcode);
	
		
	

}







