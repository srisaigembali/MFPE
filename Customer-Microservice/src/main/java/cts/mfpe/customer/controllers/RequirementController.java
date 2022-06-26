package cts.mfpe.customer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cts.mfpe.customer.models.Requirement;
import cts.mfpe.customer.services.RequirementService;

@RestController
public class RequirementController {
	
	@Autowired
	private RequirementService requirementService;
	
	@GetMapping("/getProperties")
	public ResponseEntity<List<Requirement>> getProperties(){
		List<Requirement> requirements = requirementService.getRequirements();
		if(requirements.size()==0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(requirements));
	}
}
