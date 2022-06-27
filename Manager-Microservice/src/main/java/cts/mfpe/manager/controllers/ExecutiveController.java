package cts.mfpe.manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cts.mfpe.manager.entities.Customer;
import cts.mfpe.manager.entities.Executive;
import cts.mfpe.manager.services.ExecutiveService;

@RestController
public class ExecutiveController {

	@Autowired
	private ExecutiveService executiveService;
	
	@PostMapping("/createExecutive")
	public ResponseEntity<String> createExecutive(@RequestBody Executive executive) {
		executiveService.createExecutive(executive);
		return new ResponseEntity<String>("Executive Created Successfully!", HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllExecutives")
	public ResponseEntity<List<Executive>> getAllExecutives(){
		return ResponseEntity.ok(executiveService.getAllExecutives());
	}
	
	@GetMapping("/getAllExecutivesByLocality/{locality}")
	public ResponseEntity<List<Executive>> getAllExecutivesByLocality(@PathVariable String locality){
		return ResponseEntity.ok(executiveService.getAllExecutivesByLocality(locality));
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return ResponseEntity.ok(executiveService.getAllCustomers());
	}
	
	@GetMapping("/getCustomersById/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
		return ResponseEntity.ok(executiveService.getCustomerById(id));
	}
	
	@PutMapping("/{executiveId}/assignExecutive/{customerId}")
	public ResponseEntity<String> assignExecutive(@PathVariable("executiveId") int execid, @PathVariable("customerId") int custid){
		return new ResponseEntity<>("Executive Assigned Successfully!",HttpStatus.OK);
	}
}
