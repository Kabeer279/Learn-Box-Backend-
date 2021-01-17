package com.project.LearnBox.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.LearnBox.Model.ClassRoom;
import com.project.LearnBox.Model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
		
	List<Comment> findByClassroom(ClassRoom classroom);
}
