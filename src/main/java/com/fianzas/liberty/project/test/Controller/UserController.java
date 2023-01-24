package com.fianzas.liberty.project.test.Controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fianzas.liberty.project.VO.FindDetailUserVO;

public interface UserController {
	
	public ResponseEntity<?> createUser (FindDetailUserVO user);
	
	public ResponseEntity<?> findById (@PathVariable(value = "id") Long userId);
	
	public ResponseEntity<?> FindListPageable (Pageable pageable);
	
	public ResponseEntity<?> FindList();

	public ResponseEntity<?> updateUser (@RequestBody FindDetailUserVO userDetails, @PathVariable (value = "id") Long userId);

	public ResponseEntity<?> deleteUser (@PathVariable (value = "id")Long userId);
	
	
}
