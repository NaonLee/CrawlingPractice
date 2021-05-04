
@Controller
public class UrlController {
	
	@Autowired
	UrlService urlService;
	
	@Autowired
	UrlVO urlVO;
	
	HashMap<String, List> allUrls = new HashMap<>();
	
	
	@RequestMapping(value = "/")
	public ModelAndView home(@ModelAttribute("urlVO") UrlVO urlVO) {
		List<UrlVO> urlList = urlService.selectAllUrls();
		ModelAndView mav = new ModelAndView("main");


		mav.addObject("url", new UrlVO());		
		mav.addObject("UrlList", urlList);
		
		return mav;
	}
	
	@RequestMapping(value = "/crawling.do")
	@ResponseBody
	public void  Crawling() {
		List<String> rootURLs = urlService.selectOnlyRoot();
		
		for(String rootURL : rootURLs) {
			if(rootURL != null) {
				
					System.out.println(rootURL);
					try {
						
						//Call the crawler
						WebCrawler cralwer = new WebCrawler();
						cralwer.getPageLinks(rootURL, rootURL, 1);
						
						//{URL, {parentURL, depth}}
						
						allUrls = cralwer.getURLs();						//Crawling result
						
						for(int i = 1; i <= 2; i++) {						//To insert data in order to depth(for pId matches)
							//Get the next URLs on the current URL
							for(String key : allUrls.keySet()) {
								if(urlService.searchid(key) == null) {		//If URL exists in DB, don't add
									String URL = key;
									String pURL = allUrls.get(key).get(0).toString();
									int depth = Integer.parseInt(allUrls.get(key).get(1).toString()); 
									
									if(depth == i) {
										urlVO.setURL(URL);
										urlVO.setPid(urlService.searchid(pURL));
										urlVO.setDepth(depth);
										urlVO.setRootId(urlService.searchid(rootURL));
										
										urlService.insertURLWithParentId(urlVO);
									}
									
								}
							}
						}
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			}
		}
		
		
		
	
	}
	
	
	@RequestMapping(value = "/inserturl.do")
	@ResponseBody
	public ModelAndView addRoot(@ModelAttribute("UrlVO") UrlVO urlVO) throws SQLIntegrityConstraintViolationException {
		ModelAndView mav = new ModelAndView("redirect:/");
		
		//Cut www.
		if(urlVO.getURL().contains("www")) urlVO.setURL(urlVO.getURL().replace("www.", ""));
		
		urlService.insertUrl(urlVO);			//Store Root information in DB right away (to use VT)
		
		return mav;
	}
	
	

}
