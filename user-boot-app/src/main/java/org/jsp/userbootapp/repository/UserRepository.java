package org.jsp.userbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.phone=?1 and u.password=?2")
	public Optional<User> verify(long phone, String password);

	@Query("select u from User u where u.email=?1 and u.password=?2")
	public Optional<User> verifyByEmail(String email, String password);

	@Query("select u from User u where u.id=?1 and u.password=?2")
	public Optional<User> verifyById(int id, String password);

	@Query("select u from User u where u.name=?1")
	public Optional<List<User>> findByName(String name);

	@Query("select u from User u where u.phone=?1")
	public Optional<User> findByPhone(long phone);

	@Query("select u from User u where u.email=?1")
	public Optional<User> findByEmail(String emails);

	@Query("select u from User u where u.id=?1 and u.email=?2")
	public Optional<User> findByIdAndEmail(int id, String email);

	@Query("select u from User u where u.id=?1 and u.phone=?2")
	public Optional<User> findByIdAndPhone(int id, long phone);

}
