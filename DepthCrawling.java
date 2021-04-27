import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * The URL should be the ABSOLUTE PATH (doesn't allow the relative path as 
 */
public class DepthWebCralwer3 {
	private final int MAX_DEPTH = 2;		//A maximum depth will be 5
	private HashSet<String> links;			//There is no need an order(So using HashSet instead of ArrayList)
	
	public DepthWebCralwer3() {
		links = new HashSet<String>();
	}
	
	//Pre-Examination for URLs
	public String preExam(String URL) {			
		Pattern pattern = Pattern.compile("(http://)?(www.)?(\\w+[.,])*(\\w+)/?");				//URL pattern
		Matcher matcher;
		
		if(URL.startsWith("https")) {
			URL = URL.replace("https", "http");													//If the protocol is 'https', change it to 'http'
		} else if(URL.startsWith("www")) {														//Make URL stats with 'http://'
			URL = URL.replace("www.", "http://");
		}
		
		matcher = pattern.matcher(URL);	
		
		//Will find a root URL by pattern and matcher
		if(matcher.find()) return matcher.group(0);												//If it's valid URL, return a root URL
		return "";																				//If it's not, return empty string
	}
	
	
	public void getLinks(String URL, int depth) {								
		
		if(((URL.startsWith("http")) || (URL.startsWith("www"))) && (depth < MAX_DEPTH)) {			//URL should start with 'http' or 'www'. If not, system will assume that URL is a relative path 
			
			URL = preExam(URL);																
			if(!getUrls().contains(URL) && URL != "") {														//URL can't be duplicated and empty 	
				try {		
					
					if(depth != 0) {															//Do not add the first(depth 0) URL as it's already in DB
						links.add(URL);
					}
					
					Document document = Jsoup.connect(URL).get();						//Get the HTML from the URL
					Elements linksOnURL = document.select("a[href]");					//Get the 'a' tags from the page
					
					depth++;															//Depth increases for searching the next level of URLs
					
					for(Element url : linksOnURL) {
						getLinks(url.attr("href"), depth);								//Looking for the next level of URLs until the depth reaches MAX_DEPTH by using recursion
					}
					
				//Exceptions	
				} catch (HttpStatusException e) {
					System.out.println("404 Error - " + URL);
					links.remove(URL);													//If page is not found, remove URL from the list
				} catch (ConnectException e) {
					System.out.println("Connection Error - " + URL);
				} catch (UnsupportedMimeTypeException e) {
					System.out.println("It's unsupported type - " + URL);
					links.remove(URL);
				} catch (UnknownHostException e) {
					System.out.println("Unknown host exception - " + URL);
					links.remove(URL);
				}
				catch (Exception e) {
					System.out.println("Some exception happening: " + URL);
					e.printStackTrace();
				}
			}
		}
		
	}

	public HashSet<String> getUrls() {
		return links;
	}

	

}
