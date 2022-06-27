package cts.mfpe.manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.mfpe.manager.clients.CustomerServiceClient;
import cts.mfpe.manager.entities.Customer;
import cts.mfpe.manager.entities.Executive;
import cts.mfpe.manager.repos.ExecutiveRepository;

@Service
public class ExecutiveServiceImpl implements ExecutiveService {

	@Autowired
	private ExecutiveRepository executiveRepo;
	
	@Autowired
	private CustomerServiceClient customerClient;
	
	@Override
	public void createExecutive(Executive executive) {
		executiveRepo.save(executive);
	}

	@Override
	public List<Executive> getAllExecutives() {
		return executiveRepo.findAll();
	}

	@Override
	public List<Executive> getAllExecutivesByLocality(String locality) {
		return executiveRepo.findByLocality(locality);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerClient.getAllCustomers();
	}

	@Override
	public Customer getCustomerById(int id) {
		return customerClient.getCustomerDetails(id);
	}

	@Override
	public void assignExecutive(int executiveid, int customerid) {
		Executive executive = executiveRepo.findById(executiveid).get();
		Customer customer = customerClient.getCustomerDetails(customerid);
		executive.getCustomers().add(customer);
		executiveRepo.save(executive);
	}


}
