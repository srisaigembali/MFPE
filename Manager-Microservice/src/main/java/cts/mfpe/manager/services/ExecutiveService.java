package cts.mfpe.manager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cts.mfpe.manager.entities.Customer;
import cts.mfpe.manager.entities.Executive;

@Service
public interface ExecutiveService {

	public void createExecutive(Executive executive);
	
	public List<Executive> getAllExecutives();
	
	public List<Executive> getAllExecutivesByLocality(String locality);
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(int id);
	
	public void assignExecutive(int executiveid, int customerid);
}
