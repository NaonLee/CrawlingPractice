package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {

	

	
	private String preProcessing(String URL, String pURL) {
		
		Pattern pattern = Pattern.compile("^(?:https?:\\/\\/)?(?:www\\.)?[a-zA-Z0-9./]+$");
		Matcher matcher;
		
		if(URL.startsWith("#") || URL.contains("javascript".toLowerCase())) {
			URL = null;
		} else {
			matcher = pattern.matcher(URL);
			if(!matcher.find()) {
				if(URL.startsWith("/") || URL.startsWith("?")) {
					URL = pURL + URL;
				}
				
			}
		}
		
		return URL;
	}

	public List<String> getURL(String URL, String pURL) {
		ArrayList<String> URLs = new ArrayList<String>();
		int count = 0;
		URL = preProcessing(URL, pURL);

		if(URL != null) {
			try {
				Document document = Jsoup.connect(URL).get();
				Elements UrlsOnPage = document.select("a[href]");
				
				Iterator<Element> itr = UrlsOnPage.iterator();
				
				while(itr.hasNext() && count < 10) {
					URLs.add(URL);
					count++;
				}
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
			
		return URLs;
		
	}
	


}
