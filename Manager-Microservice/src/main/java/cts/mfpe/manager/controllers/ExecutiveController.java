package cts.mfpe.manager.controllers;

import java.util.List;

import javax.validation.Valid;

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
import cts.mfpe.manager.exceptions.CustomerNotFoundException;
import cts.mfpe.manager.exceptions.ExecutiveNotFoundException;
import cts.mfpe.manager.services.ExecutiveService;

@RestController
public class ExecutiveController {

	@Autowired
	private ExecutiveService executiveService;
	
	@PostMapping("/createExecutive")
	public ResponseEntity<String> createExecutive(@RequestBody @Valid Executive executive) throws Exception {
		executiveService.createExecutive(executive);
		return new ResponseEntity<String>("Executive Created Successfully!", HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllExecutives")
	public ResponseEntity<List<Executive>> getAllExecutives(){
		return ResponseEntity.ok(executiveService.getAllExecutives());
	}
	
	@GetMapping("/getAllExecutivesByLocality/{locality}")
	public ResponseEntity<List<Executive>> getAllExecutivesByLocality(@PathVariable String locality) throws Exception{
		List<Executive> executives = executiveService.getAllExecutivesByLocality(locality);
		if(executives.size()==0) {
			throw new ExecutiveNotFoundException("Executives in locality "+locality+" not found");
		}
		return ResponseEntity.ok(executives);
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return ResponseEntity.ok(executiveService.getAllCustomers());
	}
	
	@GetMapping("/getCustomersById/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int id) throws Exception {
		Customer customer = executiveService.getCustomerById(id);
		if(customer==null) {
			throw new CustomerNotFoundException("Customer with id "+id+" not found");
		}
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping("/{executiveId}/assignExecutive/{customerId}")
	public ResponseEntity<String> assignExecutive(@PathVariable("executiveId") int execid, @PathVariable("customerId") int custid){
		executiveService.assignExecutive(execid, custid);
		return new ResponseEntity<>("Executive Assigned Successfully!",HttpStatus.OK);
	}
}
