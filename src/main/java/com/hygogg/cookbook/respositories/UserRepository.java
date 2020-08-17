package com.hygogg.cookbook.respositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.cookbook.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
}
