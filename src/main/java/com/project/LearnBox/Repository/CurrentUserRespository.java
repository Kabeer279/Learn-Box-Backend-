package com.project.LearnBox.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.LearnBox.Model.CurrentUser;


@Repository
public interface CurrentUserRespository extends CrudRepository<CurrentUser,Long>  {

}
