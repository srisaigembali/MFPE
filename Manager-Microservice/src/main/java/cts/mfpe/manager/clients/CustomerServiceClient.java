package cts.mfpe.manager.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cts.mfpe.manager.entities.Customer;

@FeignClient(value="customerClient",url = "http://localhost:8081/customer")
public interface CustomerServiceClient {

	@GetMapping("/getCustomerDetails/{id}")
	Customer getCustomerDetails(@PathVariable int id);
	
	@GetMapping("/getAllCustomers")
	List<Customer> getAllCustomers();
}
