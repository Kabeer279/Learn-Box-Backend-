package com.project.LearnBox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.LearnBox.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
		

}
