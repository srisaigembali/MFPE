package cts.mfpe.manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.mfpe.manager.clients.CustomerServiceClient;
import cts.mfpe.manager.entities.Customer;
import cts.mfpe.manager.entities.Executive;
import cts.mfpe.manager.exceptions.ExecutiveAlredyExistsException;
import cts.mfpe.manager.repos.ExecutiveRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ExecutiveRepository executiveRepo;
	
	@Autowired
	private CustomerServiceClient customerClient;
	
	@Override
	public void createExecutive(Executive executive) throws Exception{
		if(checkIfExecutiveAlreadyExists(executive.getName())) {
			throw new ExecutiveAlredyExistsException("Executive Already Exists");
		}
		executiveRepo.save(executive);
	}

	@Override
	public List<Executive> getAllExecutives() {
		return executiveRepo.findAll();
	}

	@Override
	public List<Executive> getAllExecutivesByLocality(String locality) throws Exception{
		return executiveRepo.findByLocality(locality);
	}

	@Override
	public List<Customer> getAllCustomers(String token) throws Exception {
		return customerClient.getAllCustomers(token);
	}

	@Override
	public Customer getCustomerById(int id, String token) throws Exception{
		return customerClient.getCustomerDetails(id, token);
	}

	@Override
	public void assignExecutive(int executiveid, int customerid, String token) throws Exception{
		Executive executive = executiveRepo.findById(executiveid).get();
		Customer customer = customerClient.getCustomerDetails(customerid, token);
		executive.getCustomers().add(customer);
		executiveRepo.save(executive);
	}

	public boolean checkIfExecutiveAlreadyExists(String executiveName) {
		return getAllExecutives().stream().anyMatch(e -> e.getName().equals(executiveName));
	}
	
}
