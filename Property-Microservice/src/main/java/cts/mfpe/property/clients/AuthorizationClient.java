package cts.mfpe.property.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Authorization-Microservice", url = "http://localhost:8084/auth")
public interface AuthorizationClient{
	
	@PostMapping(value = "/authorize")
	public boolean authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
	
	@PostMapping(value = "/authorize-manager")
	public boolean authorizeTheRequestForManager(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) ;

	@PostMapping(value = "/authorize-executive")
	public boolean authorizeTheRequestForExecutive(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) ;

	@PostMapping(value = "/authorize-customer")
	public boolean authorizeTheRequestForCustomer(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) ;

}
