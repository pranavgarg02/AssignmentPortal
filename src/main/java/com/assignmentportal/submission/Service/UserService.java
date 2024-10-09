package com.assignmentportal.submission.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.assignmentportal.submission.Repositories.UserDetailsRepository;
import com.assignmentportal.submission.model.UserDetails;

@Service
public class UserService {
	
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	public UserService(UserDetailsRepository detailsRepository) {
		super();
		this.userDetailsRepository = detailsRepository;
	}
	
	
	public ResponseEntity<?> doRegisterUser(UserDetails userdetails){
		userdetails = userDetailsRepository.save(userdetails);
		return ResponseEntity.ok("Save the Id for future Reference" + userdetails.getId());
	}
	
	public ResponseEntity<?> getUser(String userName, String password) {
		Optional<UserDetails> existingUser = userDetailsRepository.findByUsernameAndPassword(userName,password);
		if(existingUser.isPresent()) return ResponseEntity.ok(existingUser.get());
		else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login Failed"); 
	}
	
	public List<UserDetails> getAllAdmins() {
		return userDetailsRepository.findByAdmin(true);
	}
	
	// in case you want list of names of admin then 
	public List<String> getAllAdminNames() {
		List<UserDetails> adminObjectList = userDetailsRepository.findByAdmin(true);
		List<String> adminList = adminObjectList.stream().map(t ->t.getUsername()).collect(Collectors.toList());
		return adminList;
	}
	// checking user is admin or not
	public boolean isAdmin(String userName) {
		UserDetails userdetails = userDetailsRepository.findByUsername(userName).get();
		return userdetails.isAdmin();
	}
	

}
