package com.assignmentportal.submission.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.assignmentportal.submission.Service.AssignmentService;
import com.assignmentportal.submission.Service.UserService;
import com.assignmentportal.submission.model.Assignment;

@RestController
@RequestMapping("/AssignmentSubmission")
public class AssignmentController {

	@Autowired
	AssignmentService assignmentService;

	@Autowired
	UserService userService;

//	@PostMapping("/upload")
//	public ResponseEntity<?> uploadAssignment(@RequestBody Assignment assignment){
//		return assignmentService.uploadAssignment(assignment);
//	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadAssignment(@RequestParam(value = "task") String task,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "admin") String admin,
			@RequestHeader String userName) {
		// checking if user is admin , then uploading assignment is not allowed
		if (userService.isAdmin(userName))
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
		// creating AssignmentObject
		Assignment assignment = new Assignment(null, task, userId, admin, LocalDateTime.now(), false);
		// calling assignmentService
		return assignmentService.uploadAssignment(assignment);

	}

	@GetMapping("/assignments") // getting assignments tagged for admin username
	public ResponseEntity<?> getAllAssignments(@RequestHeader String username) {
		if (!userService.isAdmin(username))
			return null;
		return assignmentService.getAssignments(username);
	}

	@PostMapping("/assignment/{id}/accept") // accepting assignment
	public HttpStatus acceptAssignment(@PathVariable String assignmentId) {
		Assignment assignment = assignmentService.getAssignment(assignmentId);

		assignment.setAccepted(true);
		assignmentService.updateAssignment(assignment);
		return HttpStatus.ACCEPTED;
	}

	@PostMapping("/assignment/{id}/reject") // rejecting assignment
	public HttpStatus rejectAssignment(@PathVariable String assignmentId) {
		Assignment assignment = assignmentService.getAssignment(assignmentId);

		assignment.setAccepted(false);
		assignmentService.updateAssignment(assignment);
		return HttpStatus.NOT_ACCEPTABLE;

	}

}
