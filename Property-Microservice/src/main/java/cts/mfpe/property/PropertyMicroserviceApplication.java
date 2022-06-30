package cts.mfpe.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PropertyMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyMicroserviceApplication.class, args);
	}

}
