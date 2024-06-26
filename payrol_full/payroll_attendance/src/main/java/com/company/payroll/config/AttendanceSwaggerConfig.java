package com.company.payroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class AttendanceSwaggerConfig {
	@Bean
	OpenAPI createRestApi() {
		return new OpenAPI()
				.info(new Info().title("Spring Boot Payroll Attendance API")
				.description("Description for payroll attendance management system")
				.version("1.0")
				.license(new License().name("Apache License, Version 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
				.externalDocs(new ExternalDocumentation().description("External document description")
														.url("testurl"));
	}
}
