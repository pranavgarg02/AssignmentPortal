package com.assignmentportal.submission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentportal.submission.Service.UserService;
import com.assignmentportal.submission.model.UserDetails;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> doRegistration(@RequestBody UserDetails newUser) {
		if (newUser == null)
			return null;
		return userService.doRegisterUser(newUser);

	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam(required = true, value = "username") String username,
			@RequestParam(value = "password", required = true) String password) {
		return userService.getUser(username, password);
	}

	@GetMapping("/admins")
	public List<UserDetails> getadmins() {
		return userService.getAllAdmins();
	}

}
