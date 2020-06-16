package com.zt.ztgeoservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZtgeoserviceApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ZtgeoserviceApplication.class);
		springApplication.run(ZtgeoserviceApplication.class, args);

	}

}
