package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {

	Document doc;
	final int COUNT_INDEX = 0;
	ArrayList<String> last = new ArrayList<String>();
	
	public void getURL(String URL, int index) {
		if(index == COUNT_INDEX) return;
		else {
			try {
				ArrayList<String> URLs = new ArrayList<String>();
				doc = Jsoup.connect(URL).get();
				String title = doc.title();
				System.out.println(title);
				
				
				Elements links = doc.select("a");
				for(Element e : links) {
					String link = e.attr("href");
					if(link.isEmpty() == false && link.equals("#") == false && link.contains("paddywagontours") == false
							&& link.startsWith("http")) {
						if(URLs.contains(link) == false) {
							if(link.startsWith("https")) {
								link = link.replaceFirst("[https]*", "http");
							}
							URLs.add(link);
						}
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
