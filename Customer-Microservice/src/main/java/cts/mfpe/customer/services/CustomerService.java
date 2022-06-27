package cts.mfpe.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.mfpe.customer.clients.PropertyServiceClient;
import cts.mfpe.customer.entities.Customer;
import cts.mfpe.customer.entities.Property;
import cts.mfpe.customer.entities.Requirement;
import cts.mfpe.customer.repos.CustomerRepository;
import cts.mfpe.customer.repos.RequirementRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private RequirementRepository requirementRepo;
	
	@Autowired
	private PropertyServiceClient propertyClient;
	
	public List<Requirement> getAllRequirements(){
		return requirementRepo.findAll();
	}
	
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	public void createCustomer(Customer customer) {
		customerRepo.save(customer);
	}
	
	public Customer getCustomerDetails(int id) {
		Optional<Customer> result = customerRepo.findById(id);
		Customer customer = null;
		if(result.isPresent()) {
			customer=result.get();
		}
		return customer;
	}
	
	public List<Property> getAllProperties(){
		return propertyClient.getAllProperties();
	}

	public void assignRequirements(int custid, int reqid) {
		Customer customer = customerRepo.findById(custid).get();
		Requirement requirement = requirementRepo.findById(reqid).get();
		customer.getRequirements().add(requirement);
		customerRepo.save(customer);
	}
	
}
