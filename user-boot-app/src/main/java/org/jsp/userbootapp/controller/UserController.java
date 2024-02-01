package org.jsp.userbootapp.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.ResponceStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.repository.UserRepository;
import org.jsp.userbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseEntity<ResponceStructure<User>> saveUser(@RequestBody User u) {
		return service.saveUser(u);

	}

	@PutMapping("/users")
	public ResponseEntity<ResponceStructure<User>> updateUser(@RequestBody User u) {
		return service.updateUser(u);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<ResponceStructure<User>> findByid(@PathVariable int id) {
		return service.findByid(id);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<ResponceStructure<Boolean>> deleteById(@PathVariable int id) {
		return service.deleteById(id);

	}

	@GetMapping("/users")
	public ResponseEntity<ResponceStructure<List<User>>> findAll() {
		return service.findAll();
	}

	@PostMapping("/users/verify-by-phone")
	public ResponseEntity<ResponceStructure<User>> verify(@RequestParam long phone, @RequestParam String password) {
		return service.verifyUser(phone, password);
	}

	@PostMapping("/users/verify-by-email")
	public ResponseEntity<ResponceStructure<User>> verifyByEmail(@RequestParam String email,
			@RequestParam String password) {
		return service.verifyUser(email, password);
	}

}
