package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlerApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(CrawlerApplication.class, args);
		String URL = "http://www.paddywagontours.com";
		Crawling cr = new Crawling();
		
		List<String> URLs = new ArrayList<String>();
		
		URLs.addAll(cr.getURL(URL, null));
		
		System.out.println(URLs.toString());
	}

}
