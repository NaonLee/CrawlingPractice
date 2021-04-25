package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlerApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(CrawlerApplication.class, args);
		String URL = "http://www.paddywagontours.com";
		Crawling cr = new Crawling();
		cr.getURL(URL, 2);
	}

}
