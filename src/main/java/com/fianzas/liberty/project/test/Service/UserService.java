package com.fianzas.liberty.project.test.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fianzas.liberty.project.VO.FindDetailUserVO;
import com.fianzas.liberty.project.VO.FindListUserVO;

public interface UserService {
	
	public Iterable<FindListUserVO> findAll();
	
	public List<FindListUserVO> findList(Pageable pageable);
	
	public FindDetailUserVO findById(Long id);
	
	public FindDetailUserVO createUser (FindDetailUserVO user);
	
	public void deleteById(Long id);
	

}
