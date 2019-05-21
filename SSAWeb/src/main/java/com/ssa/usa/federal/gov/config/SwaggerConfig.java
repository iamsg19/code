package com.ssa.usa.federal.gov.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {                                    
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.ssa.usa.federal.gov.rest.controller"))
	      .paths(PathSelectors.regex("/get.*"))
	      .build()
	      .apiInfo(apiInfo());
	}
	 
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "SSAWeb Rest API", 
	      "Description of SSAWeb API", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Federal Government", "www.usa.gov", "usa@goverment.gov"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
}