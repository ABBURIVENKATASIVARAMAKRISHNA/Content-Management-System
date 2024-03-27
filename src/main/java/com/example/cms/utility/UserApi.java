package com.example.cms.utility;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition
public class UserApi {

	@Bean
	Info info()
	{
		return new Info().title("Content Management System").description("More Efficient to work")
				.version("0.1").contact(contact());
	}
	
	@Bean
	Contact contact()
	{
		return new Contact().email("abc@gmail.com").name("siva").url("contentManagement");
				
	}
	
	@Bean
	OpenAPI openApi()
	{
		return new OpenAPI().info(info());
	}
}
