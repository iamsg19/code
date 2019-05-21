package com.usa.nj.ed.gov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.usa.nj.ed.gov.constants.StringConstants.PACKAGE;
import static com.usa.nj.ed.gov.constants.StringConstants.TITLE;
import static com.usa.nj.ed.gov.constants.StringConstants.DESCRIPTION;
import static com.usa.nj.ed.gov.constants.StringConstants.VERSION;
import static com.usa.nj.ed.gov.constants.StringConstants.TERMS_OF_SERVICE_URL;
import static com.usa.nj.ed.gov.constants.StringConstants.LICENSE;
import static com.usa.nj.ed.gov.constants.StringConstants.LICENSE_URL;
import static com.usa.nj.ed.gov.constants.StringConstants.NAME;
import static com.usa.nj.ed.gov.constants.StringConstants.URL;
import static com.usa.nj.ed.gov.constants.StringConstants.EMAIL;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket ssaWebApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(PACKAGE)).paths(PathSelectors.any())
				.build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(TITLE, DESCRIPTION, VERSION,
				TERMS_OF_SERVICE_URL, new Contact(NAME, URL, EMAIL),
				LICENSE, LICENSE_URL);
		return apiInfo;
	}
}
