package tn.recrute.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.recrute.demo.service.UserService;


@SpringBootApplication
public class ERecrutementBackendApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ERecrutementBackendApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("starting code");
		
		/*Document doc = Jsoup.connect("https://www.imdb.com/chart/top").timeout(6000).get();
		Elements body = doc.select("tbody.lister-list");
		System.out.println(body.select("tr").size());
		System.out.println("Jsoup web scrapping"); */
	}

}
