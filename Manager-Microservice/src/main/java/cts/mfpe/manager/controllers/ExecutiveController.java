package cts.mfpe.manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cts.mfpe.manager.entities.Executive;
import cts.mfpe.manager.services.ExecutiveService;

@RestController
public class ExecutiveController {

	@Autowired
	private ExecutiveService executiveService;
	
	@PostMapping("/createExecutive")
	public ResponseEntity<String> createExecutive(@RequestBody Executive executive) {
		executiveService.createExecutive(executive);
		return new ResponseEntity<String>("Executive Created", HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllExecutives")
	public List<Executive> getAllExecutives(){
		return executiveService.getAllExecutives();
	}
	
	@GetMapping("/getAllExecutivesByLocality/{locality}")
	public List<Executive> getAllExecutivesByLocality(@PathVariable String locality){
		return executiveService.getAllExecutivesByLocality(locality);
	}
	
}
