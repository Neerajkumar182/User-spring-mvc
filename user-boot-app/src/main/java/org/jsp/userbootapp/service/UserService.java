package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.ResponceStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.exception.CredentialNotFoundException;
import org.jsp.userbootapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponceStructure<User>> saveUser(@RequestBody User u) {
		ResponceStructure<User> structure = new ResponceStructure<>();
		structure.setData(userDao.saveUser(u));
		structure.setMessage("user saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponceStructure<User>> updateUser(@RequestBody User u) {
		Optional<User> recUser = userDao.findById(u.getId());
		ResponceStructure<User> structure = new ResponceStructure<>();
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setName(u.getName());
			dbUser.setEmail(u.getEmail());
			dbUser.setPhone(u.getPhone());
			dbUser.setPassword(u.getPassword());
			structure.setData(userDao.saveUser(dbUser));
			structure.setMessage("user updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponceStructure<User>> findByid(int id) {
		ResponceStructure<User> structure = new ResponceStructure<>();
		Optional<User> dbUser = userDao.findById(id);
		if (dbUser.isPresent()) {
			structure.setData(dbUser.get());
			structure.setMessage("got the data");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponceStructure<Boolean>> deleteById(int id) {
		Optional<User> dbUser = userDao.findById(id);
		ResponceStructure<Boolean> structure = new ResponceStructure<>();
		if (dbUser.isPresent()) {
			userDao.deleteById(id);
			structure.setData(true);
			structure.setMessage("user  has been deleted");
			structure.setStatusCode(HttpStatus.GONE.value());
			return new ResponseEntity<ResponceStructure<Boolean>>(structure, HttpStatus.GONE);
		} else {
			structure.setData(false);
			structure.setMessage("User not found");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponceStructure<Boolean>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponceStructure<List<User>>> findAll() {
		ResponceStructure<List<User>> structure = new ResponceStructure<>();
		structure.setData(userDao.findAll());
		structure.setMessage("got all the users data from the User Database");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponceStructure<List<User>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponceStructure<User>> verifyUser(@RequestParam long phone, @RequestParam String password) {
		Optional<User> recUser = userDao.verifyUser(phone, password);
		ResponceStructure<User> structure = new ResponceStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("the entered credentinals are valid");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.OK);
		}
		throw new CredentialNotFoundException();
	}

	public ResponseEntity<ResponceStructure<User>> verifyUser(@RequestParam String email,
			@RequestParam String password) {
		Optional<User> recUser = userDao.verifyUser(email, password);
		ResponceStructure<User> structure = new ResponceStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("the entered credentinals are valid");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.OK);
		}
		throw new CredentialNotFoundException();
	}

}
