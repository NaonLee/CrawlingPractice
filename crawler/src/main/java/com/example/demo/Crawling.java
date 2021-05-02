package com.example.demo;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {

	private final int MAX_DEPTH = 2;		//A maximum depth will be 5
	private HashMap<String, String> links;			//There is no need an order(So using HashSet instead of ArrayList)
	
	

	public Crawling() {
		links = new HashMap<String, String>();
	}

	private String preProcessing(String URL, String pURL) {
		
		Pattern pattern = Pattern.compile("^(?:https?:\\/\\/)?(?:www\\.)?[a-zA-Z0-9./]+$");
		Matcher matcher;
		
		if(links.containsValue(URL)) URL = "";
		if(URL.contains("www")) URL = URL.replace("www.", "");
		
		return URL;
	}

	public void getURL(String URL, String pURL, int depth) {
		
		URL = preProcessing(URL, pURL);

		if(depth < MAX_DEPTH && URL != "" && !links.containsValue(URL)) {
			try {
				Document document = Jsoup.connect(URL).get();
				Elements UrlsOnPage = document.select("a[href]");
				
				depth++;
				
				links.put(URL, pURL);
				System.out.println(URL);
				for(Element url : UrlsOnPage) {
					getURL(url.attr("href"), URL, depth);
				}
				
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
			
		
	}
	
	public HashMap getURLs() {
		return links;
	}

}
