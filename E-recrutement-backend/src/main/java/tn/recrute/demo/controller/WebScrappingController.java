package tn.recrute.demo.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jsoup")
@CrossOrigin("*")
public class WebScrappingController {
	
	@GetMapping("/list")
	public void getWebScrapping() throws IOException {
		Document doc = Jsoup.connect("https://www.imdb.com/chart/top").timeout(6000).get();
		Elements body = doc.select("tbody.lister-list");
		System.out.println(body.select("tr").size());
		
		/*for(Element e : body.select("tr"))
		{
		    String img = e.select("td.posterColumn img").attr("src");
		    System.out.println(img);
		} */
	}
	
	
}
