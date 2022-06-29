package cts.mfpe.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ManagerMicroserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ManagerMicroserviceApplication.class, args);
	}

}
