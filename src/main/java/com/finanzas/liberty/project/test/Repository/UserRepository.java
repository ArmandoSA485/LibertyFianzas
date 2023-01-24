package com.finanzas.liberty.project.test.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.liberty.project.test.Entity.UserDO;

@Repository
public interface UserRepository extends JpaRepository<UserDO, Serializable>{

    @Query(value = " SELECT r " 
            + " FROM  UserDO r "
            + " WHERE r.id = :id ")
    UserDO findId(
            @Param("id") Long id);	
	

}
