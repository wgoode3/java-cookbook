package com.hygogg.cookbook.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.hygogg.cookbook.models.User;
import com.hygogg.cookbook.respositories.UserRepository;


@Service
public class UserService {

	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User create(User u) {
		String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
		u.setPassword(hashed);
		return userRepo.save(u);
	}
	
	public User getOne(String email) {
		Optional<User> userInDb = userRepo.findByEmail(email);
		return userInDb.isPresent() ? userInDb.get() : null;
	}
	
}
