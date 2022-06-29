package cts.mfpe.customer.controllers;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import cts.mfpe.customer.clients.AuthorizationClient;
import cts.mfpe.customer.entities.Customer;
import cts.mfpe.customer.entities.Property;
import cts.mfpe.customer.entities.Requirement;
import cts.mfpe.customer.exceptions.AuthorizationException;
import cts.mfpe.customer.exceptions.CustomerNotFoundException;
import cts.mfpe.customer.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AuthorizationClient authorizationClient;
	
	@GetMapping("/getAllRequirements")
	public ResponseEntity<List<Requirement>> getAllRequirements(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException {
		if (authorizationClient.authorizeTheRequestForCustomer(requestTokenHeader)) {
			return ResponseEntity.ok(customerService.getAllRequirements());
		}
		throw new AuthorizationException("Not Allowed");
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException{
		if (authorizationClient.authorizeTheRequestForManager(requestTokenHeader)) {
			return ResponseEntity.ok(customerService.getAllCustomers());
		}
		throw new AuthorizationException("Not Allowed");
	}
	
	@PostMapping("/createCustomer")
	public ResponseEntity<String> createCustomer(@RequestBody @Valid Customer customer,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws Exception{
	    if (authorizationClient.authorizeTheRequestForCustomer(requestTokenHeader)) {
	    	customerService.createCustomer(customer);
	    	return new ResponseEntity<>("Customer Created Successfully!",HttpStatus.CREATED);
		}
		throw new AuthorizationException("Not Allowed");
	}
	
	@GetMapping("/getCustomerDetails/{id}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable int id,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws Exception{
		Customer customer = customerService.getCustomerDetails(id);
		if(customer==null) {
			throw new CustomerNotFoundException("Customer Not Found!");
		}
		if (authorizationClient.authorizeTheRequest(requestTokenHeader)) {
			return ResponseEntity.ok(customer);
		}
		throw new AuthorizationException("Not Allowed");
	}
	
	@GetMapping("/getProperties")
	public ResponseEntity<List<Property>> getAllProperties(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws Exception{
		if (authorizationClient.authorizeTheRequestForCustomer(requestTokenHeader)) {
			return ResponseEntity.ok(customerService.getAllProperties(requestTokenHeader));
		}
		throw new AuthorizationException("Not Allowed");
	}
	
	@PutMapping("/{customerId}/assignRequirements/{requirementId}")
	public ResponseEntity<String> assignRequirements(@PathVariable("customerId") int custid, 
			@PathVariable("requirementId") int reqid, 
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException{
		if (authorizationClient.authorizeTheRequestForCustomer(requestTokenHeader)) {
			customerService.assignRequirements(custid, reqid);
			return new ResponseEntity<>("Requirement Assigned Successfully!",HttpStatus.OK);
		}
		throw new AuthorizationException("Not Allowed");
	}
}
