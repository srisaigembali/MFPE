package cts.mfpe.manager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cts.mfpe.manager.entities.Customer;
import cts.mfpe.manager.entities.Executive;

@Service
public interface ExecutiveService {

	public void createExecutive(Executive executive) throws Exception;
	
	public List<Executive> getAllExecutives();
	
	public List<Executive> getAllExecutivesByLocality(String locality) throws Exception;
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(int id) throws Exception;
	
	public void assignExecutive(int executiveid, int customerid);
}
