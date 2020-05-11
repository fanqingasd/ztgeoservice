package com.zt.ztgeoservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZtgeoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZtgeoserviceApplication.class, args);
		/*SpringApplication springApplication = new SpringApplication(ZtgeoserviceApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);*/
	}

}
