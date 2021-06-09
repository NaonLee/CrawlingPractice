package com.example.demo.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Crawling;
import com.example.demo.board.domain.UrlVO;


@Controller
public class BoardController {
	@RequestMapping(value="/addRoot", method = RequestMethod.GET)
	public UrlVO root(@RequestParam("url") String url) {
		UrlVO urlVO = new UrlVO();
		
		Crawling crawler = new Crawling();
		url = crawler.preProcess(url, url);
		
		if(urlService.search)
		
		return urlVO;
	}
}
