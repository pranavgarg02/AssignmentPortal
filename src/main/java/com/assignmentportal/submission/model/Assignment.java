package com.assignmentportal.submission.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document(collection = "Assignment")
@Data
@AllArgsConstructor
public class Assignment {

	@Id
	private String id;
	private String task;
	private String userId;
	private String admin;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime submittedAt;
	private boolean accepted;

}
