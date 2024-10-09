package com.assignmentportal.submission.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.assignmentportal.submission.Repositories.AssignmentRepository;
import com.assignmentportal.submission.Repositories.UserDetailsRepository;
import com.assignmentportal.submission.model.Assignment;

@Service
public class AssignmentService {
	AssignmentRepository assignmentRepository;
	UserDetailsRepository userDetailsRepository;
	
	// constructor injection 
	@Autowired
	public AssignmentService(AssignmentRepository assignmentRepository, UserDetailsRepository userDetailsRepository) {
		super();
		this.assignmentRepository = assignmentRepository;
		this.userDetailsRepository = userDetailsRepository;
	}
	
	// uploading assignment and saving in MongoDB Database using Data JPA
	public ResponseEntity<?> uploadAssignment(Assignment assignmentdetails) {
		if(assignmentdetails==null) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Assignment details are not correct");
		assignmentRepository.save(assignmentdetails);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
	
	// getting assignments for admin currently logged in assignment Portal
	public ResponseEntity<?> getAssignments(String admin) {
		List<Assignment> assignments = assignmentRepository.findByAdminOrderBySubmittedAt(admin);
		return ResponseEntity.ok(assignments);
	}
	
	//Get Assignment for specific assignment id
	public Assignment getAssignment(String asignmentId) {
		Optional<Assignment> existingAssignment = assignmentRepository.findById(asignmentId);
		if(existingAssignment.isPresent()) return existingAssignment.get();
		else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	// update assignment
	public void updateAssignment(Assignment assignment) {
		assignmentRepository.save(assignment);
	}

}
