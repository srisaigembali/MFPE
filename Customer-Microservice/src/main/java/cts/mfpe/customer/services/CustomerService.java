package cts.mfpe.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.mfpe.customer.models.Customer;
import cts.mfpe.customer.models.Requirement;
import cts.mfpe.customer.repos.CustomerRepository;
import cts.mfpe.customer.repos.RequirementRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private RequirementRepository requirementRepo;
	
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

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	public Customer assignRequirementToCustomer(int custId, int reqId) {
		Requirement requirement = requirementRepo.findById(reqId).get();
		Customer customer = customerRepo.findById(custId).get();
		customer.addRequirement(requirement);
		return customerRepo.save(customer);
	}
	
}
