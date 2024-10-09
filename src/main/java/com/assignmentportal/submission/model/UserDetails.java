package com.assignmentportal.submission.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document(collection = "UserDetails")
@Data
@AllArgsConstructor
public class UserDetails {
	@Id
	private String id;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean admin;

}
