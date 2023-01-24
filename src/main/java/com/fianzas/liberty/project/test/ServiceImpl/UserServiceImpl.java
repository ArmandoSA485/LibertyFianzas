package com.fianzas.liberty.project.test.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fianzas.liberty.project.VO.FindDetailUserVO;
import com.fianzas.liberty.project.VO.FindListUserVO;
import com.fianzas.liberty.project.test.Entity.EmailDO;
import com.fianzas.liberty.project.test.Entity.UserDO;
import com.fianzas.liberty.project.test.Repository.EmailRepository;
import com.fianzas.liberty.project.test.Repository.UserRepository;
import com.fianzas.liberty.project.test.Service.UserService;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Override
	public Iterable<FindListUserVO> findAll() {
		List<UserDO> response = userRepository.findAll();
		List<FindListUserVO> NewResponse = new ArrayList<>();
		for(int i=0;i<response.size();i++) {
			FindListUserVO newVO = new FindListUserVO();
			newVO.setId(response.get(i).getId());
			newVO.setName(response.get(i).getName());
			if(response.get(i).getStatus()==200)newVO.setStatus(true); else newVO.setStatus(false);
			if(response.get(i).getGender()==1)newVO.setGender("MALE"); else newVO.setGender("FEMALE");
			newVO.setPhoto(response.get(i).getPhoto());
			newVO.setEmail(emailRepository.findListmail(response.get(i).getId()));	
			NewResponse.add(newVO);
			}
		
		
		return NewResponse;
		
		
	}
	
	@Override
	@Transactional
	public List<FindListUserVO> findList(Pageable pageable) {
		Page<UserDO> response = userRepository.findAll(pageable);
		List<FindListUserVO> NewResponse = new ArrayList<>();
		for(int i=0;i<response.getContent().size();i++) {
			FindListUserVO newVO = new FindListUserVO();
			newVO.setId(response.getContent().get(i).getId());
			newVO.setName(response.getContent().get(i).getName());
			if(response.getContent().get(i).getStatus()==200)newVO.setStatus(true); else newVO.setStatus(false);
			if(response.getContent().get(i).getGender()==1)newVO.setGender("MALE"); else newVO.setGender("FEMALE");
			newVO.setPhoto(response.getContent().get(i).getPhoto());
			newVO.setEmail(emailRepository.findListmail(response.getContent().get(i).getId()));	
			NewResponse.add(newVO);
			}
		
		
		return NewResponse;
			
	}

	

	@Override
	public FindDetailUserVO findById(Long id) {
		UserDO obj = userRepository.findId(id);
		FindDetailUserVO registro = new FindDetailUserVO();
		registro.setId(id);
		registro.setEmail(emailRepository.findListmail(id));
		registro.setName(obj.getName());
		if(obj.getStatus()==200)registro.setStatus(true); else registro.setStatus(false);
		if(obj.getGender()==1)registro.setGender("MALE"); else registro.setGender("FEMALE");
		registro.setPhoto(obj.getPhoto());
		return registro;
	}

	@Override
	public FindDetailUserVO createUser(FindDetailUserVO user) {
		
		FindDetailUserVO registro = new FindDetailUserVO();
		
		
		
		UserDO NewUser = new UserDO();
		NewUser.setId(user.getId());
		NewUser.setName(user.getName());
		NewUser.setPhoto(user.getPhoto());
		
		if(user.getGender().equals("MALE") ) {
			NewUser.setGender((long) 1);}
				else if(user.getGender().equals("FEMALE") ) { 
						NewUser.setGender((long) 2);}
							else {NewUser.setGender(null);}
			
		if(user.getStatus() == true ) {
			NewUser.setStatus((long) 200);
				}else NewUser.setStatus((long) 500);
				
		
		UserDO usuario = userRepository.save(NewUser);
		
		List<String> lista = new ArrayList <String>();
				for(int i=0;i<user.getEmail().size(); i++) {
						EmailDO listEmail= new EmailDO();
						listEmail.setEmail(user.getEmail().get(i));
						listEmail.setUser(usuario);
						lista.add(emailRepository.save(listEmail).getEmail());}
				
		registro.setId(usuario.getId());
		registro.setName(usuario.getName());
		registro.setPhoto(usuario.getPhoto());
		if(usuario.getGender() == 1) {
			registro.setGender ("MALE");
				}else registro.setGender("FEMALE");
		if(usuario.getStatus()==200) {
			registro.setStatus(true);
				}else registro.setStatus(false);
			for(int j=0;j<lista.size();j++) {
				registro.setEmail(lista);}
		
		
		return registro;
	}
	

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	

}
