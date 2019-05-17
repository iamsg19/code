package com.ssa.usa.federal.gov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ssa.usa.federal.gov.entity.SsaEntity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SsaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsaWebApplication.class, args);
	}

}
