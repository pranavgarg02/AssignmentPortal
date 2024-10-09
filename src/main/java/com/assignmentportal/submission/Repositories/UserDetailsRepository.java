package com.assignmentportal.submission.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.assignmentportal.submission.model.UserDetails;

// Implementing Spring Data JPA
public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {

	Optional<UserDetails> findByUsernameAndPassword(String username, String password);

	List<UserDetails> findByAdmin(boolean admin);

	Optional<UserDetails> findByUsername(String username);

}
