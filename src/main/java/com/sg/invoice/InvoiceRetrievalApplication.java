package com.sg.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class InvoiceRetrievalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceRetrievalApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(InvoiceRetrievalApplication.class);
	}

}
