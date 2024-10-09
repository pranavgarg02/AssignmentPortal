package com.assignmentportal.submission.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.assignmentportal.submission.model.Assignment;
import java.util.List;


public interface AssignmentRepository extends MongoRepository<Assignment, String>{
	
	List<Assignment> findByAdminOrderBySubmittedAt(String admin);
	List<Assignment> findByAccepted(boolean accepted);

}
