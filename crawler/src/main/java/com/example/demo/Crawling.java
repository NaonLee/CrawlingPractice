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

	

	
	public String preProcess(String URL, String pURL) {
		Pattern urlPattern, protocol;
		
		protocol = Pattern.compile("^([a-zA-Z]+://)");			
		urlPattern = Pattern.compile("^([a-zA-Z]+://)(www.)?[a-zA-Z0-9#$&%*+-_./=-~]+$");					//URL 형태 체크를 위한 패턴
		Pattern gTLD = Pattern.compile("(\\.[a-zA-Z]+)$");
		
		
		Matcher matcher;
		
		if(URL.startsWith("#") || URL.toLowerCase().contains("javascript:") || URL.startsWith("@")) {			
			return null;
		} else {
			matcher = urlPattern.matcher(URL);					
			
			if(!matcher.find()) {
				
				if(URL.startsWith("/") || URL.startsWith("?")) {	
					URL = pURL + URL;
				}
				matcher = protocol.matcher(URL);
				if(!matcher.find()) {																//프로토콜 미 포함 시
					matcher = protocol.matcher(pURL);
					
					if(matcher.find()) {						
						URL = matcher.group(0) + URL;														//부모 URL의 프로토콜 붙임
					} else {
						URL = "http://" + URL;																//부모 URL에도 프로토콜이 없을 경우 임의로 http 붙임
					}
					
				}
				
			}
		}
		
		matcher = urlPattern.matcher(URL);						
		if(matcher.find()) {
//			matcher = gTLD.matcher(URL);
//			if(matcher.find()) {
//				if(!fileExtension.contains(matcher.group(0)))
//					URL += "/";
//			}
			System.out.println("MID processEd: " + URL);
			return URL;
		} else {
			return null;																					//URL 패턴에 부합하지 않을 경우 null 반환
		}
	}

	public List<String> getURL(String URL, String pURL) {
		ArrayList<String> URLs = new ArrayList<String>();
		int count = 0;
		URL = preProcess(URL, pURL);

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
