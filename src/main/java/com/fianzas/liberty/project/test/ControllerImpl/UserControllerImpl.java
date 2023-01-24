package com.fianzas.liberty.project.test.ControllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fianzas.liberty.project.VO.FindDetailUserVO;
import com.fianzas.liberty.project.test.Controller.UserController;
import com.fianzas.liberty.project.test.Service.UserService;

@RestController
@RequestMapping ("/api")
public class UserControllerImpl implements UserController {
	
	@Autowired
	private UserService userService;
	
	//Crea un nuevo usuario
		@PostMapping(value = "users/create")
		public ResponseEntity<?> createUser (@RequestBody FindDetailUserVO user){
			try {
				FindDetailUserVO Registro = userService.createUser(user);
				return ResponseEntity.status(HttpStatus.CREATED).body(Registro);
			}catch(Exception exception){
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
		}
		
		
	@GetMapping("/users/pageable")
	public ResponseEntity<?> FindListPageable(@PageableDefault(sort = "id", size = 10)Pageable pageable) {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(userService.findList(pageable));
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor)");
	}}
	
	@GetMapping("/users")
	public ResponseEntity<?> FindList() {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor)");
	}}
	
	//Busqueda por id
	@GetMapping("/users/{id}")
		public ResponseEntity<?> findById (@PathVariable(value = "id") Long userId){
		FindDetailUserVO oUser = userService.findById(userId);
			//System.out.println(oUser);
			if(oUser == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(oUser);
		}

		
	//Update	
		@PutMapping ("users/update/{id}")
		public ResponseEntity<?> updateUser (@RequestBody FindDetailUserVO userDetails, @PathVariable (value = "id") Long userId){
			FindDetailUserVO user = userService.findById(userId);
				if(user.equals(null)) {
					return ResponseEntity.notFound().build();
				}
			user.setId(userId);
			user.setName(userDetails.getName());
			//if(userDetails.getStatus()== true)user.setStatus(1);else user.setStatus(false);
			//if(userDetails.getGender())
			user.setStatus(userDetails.getStatus());
			user.setGender(userDetails.getGender());
			user.setPhoto(userDetails.getPhoto());
			user.setEmail(userDetails.getEmail());
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));	
		}
		
	//Delete	
		@DeleteMapping("users/delete/{id}")
		public ResponseEntity<?> deleteUser (@PathVariable (value = "id")Long userId){
			if(userService.findById(userId).equals(null) ) {
				return ResponseEntity.notFound().build();
			}
			userService.deleteById(userId);
			return ResponseEntity.ok().body("Elemento eliminado con exito.");
		}

	
				
}
