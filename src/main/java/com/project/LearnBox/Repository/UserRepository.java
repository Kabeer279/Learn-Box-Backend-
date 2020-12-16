package com.project.LearnBox.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.LearnBox.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>  {

	
	public User findByName(String name);
}
