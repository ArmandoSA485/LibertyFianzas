package com.finanzas.liberty.project.test.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.liberty.project.test.Entity.EmailDO;

@Repository
public interface EmailRepository extends JpaRepository<EmailDO, Long>{
	
	@Query(value = " SELECT r.email" 
	            + " FROM  EmailDO r "
	            + " WHERE r.user.id = :idUser")
	    List<String> findListmail(
	            @Param("idUser") Long idUser);	
	

}
