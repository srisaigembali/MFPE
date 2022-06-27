package cts.mfpe.customer.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cts.mfpe.customer.entities.Property;

@FeignClient(value="propertyClient",url="http://localhost:8082/property")
public interface PropertyServiceClient {

	@GetMapping("/getAllProperties")
	List<Property> getAllProperties();
	
	@GetMapping("/getAllPropertiesByType/{propertyType}")
	List<Property> getAllPropertiesByType(@PathVariable String propertyType);
	
	@GetMapping("/getAllPropertiesByLocality/{locality}")
	List<Property> getAllPropertiesByLocality(@PathVariable String locality);
}
