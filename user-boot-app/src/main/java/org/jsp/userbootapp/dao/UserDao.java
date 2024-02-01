package org.jsp.userbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User u) {
		return repository.save(u);
	}

	public Optional<User> findById(int id) {
		return repository.findById(id);
	}

	public boolean deleteById(int id) {
		Optional<User> recUser = findById(id);
		if (recUser.isPresent()) {
			repository.delete(recUser.get());
			return true;
		}
		return false;
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public Optional<User> verifyUser(long phone, String password) {
		return repository.verify(phone, password);
	}

	public Optional<User> verifyUser(String email, String password) {
		return repository.verifyByEmail(email, password);
	}

}
