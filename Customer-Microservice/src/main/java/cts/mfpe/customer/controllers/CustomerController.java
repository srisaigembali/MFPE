package cts.mfpe.customer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cts.mfpe.customer.models.Customer;
import cts.mfpe.customer.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/getAllCustomers")
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
	    customerService.createCustomer(customer);
		return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);
	}
	
	@GetMapping("/getCustomerDetails/{id}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable int id) {
		Customer customer = customerService.getCustomerDetails(id);
		if(customer==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(customer));
	}
	
	@PutMapping("/{customer_id}/assignRequirement/{requirement_id}")
	public Customer assignRequirementToCustomer(@PathVariable("customer_id") int custid, @PathVariable("requirement_id") int reqid) {
		return customerService.assignRequirementToCustomer(custid, reqid);
	}
}
