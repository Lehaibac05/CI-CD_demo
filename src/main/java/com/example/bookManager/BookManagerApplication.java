package com.example.bookManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagerApplication.class, args);
		System.out.println("Book Manager Application is running!");
		System.out.println("Visit http://localhost:8080 to access the application.");
	}

}
