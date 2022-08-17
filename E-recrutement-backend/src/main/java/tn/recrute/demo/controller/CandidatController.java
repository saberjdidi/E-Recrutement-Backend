package tn.recrute.demo.controller;

import static java.nio.file.Paths.get;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.recrute.demo.model.Candidat;
import tn.recrute.demo.model.Formation;
import tn.recrute.demo.model.Offer;
import tn.recrute.demo.model.Response;
import tn.recrute.demo.service.CandidatService;
import tn.recrute.demo.service.OfferService;

@RestController
@RequestMapping("/candidat")
@CrossOrigin("*")
public class CandidatController {

	@Autowired
	private CandidatService candidatService;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired  ServletContext context;
	
	@PostMapping("/save")
	 public ResponseEntity<Response> addCandidat (@RequestParam("file") MultipartFile file,
			 @RequestParam("candidat") String candidat) throws JsonParseException, JsonMappingException, Exception
	 {
		 System.out.println("Ok .............");
      Candidat cand = new ObjectMapper().readValue(candidat, Candidat.class);
      boolean isExit = new File(context.getRealPath("/Files/")).exists();
      if (!isExit)
      {
      	new File (context.getRealPath("/Files/")).mkdir();
      	System.out.println("mk dir.............");
      }
      String filename = file.getOriginalFilename();
      String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
      File serverFile = new File (context.getRealPath("/Files/"+File.separator+newFileName));
      try
      {
      	System.out.println("File");
      	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
      	 
      }catch(Exception e) {
      	e.printStackTrace();
      }
     
      cand.setFilename(newFileName);
      Candidat candidate = this.candidatService.addCandidat(cand);
      if (candidate != null)
      {
      	return new ResponseEntity<Response>(new Response ("candidate save successfully"),HttpStatus.OK);
      }
      else
      {
      	return new ResponseEntity<Response>(new Response ("candidate not saved"),HttpStatus.BAD_REQUEST);	
      }
	 }
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		
		return ResponseEntity.ok(this.candidatService.getCandidats());
	}
	
	@GetMapping("/{CandidatId}")
	public Candidat getById(@PathVariable("CandidatId") Long id) {
		
		return this.candidatService.findById(id);
	}
	
	@DeleteMapping("/{CandidatId}")
	public void deleteCandidat(@PathVariable("CandidatId") Long id) {
		
		this.candidatService.deleteCandidat(id);
	}
	
	@GetMapping("/offer/{offerId}")
	public ResponseEntity<?> getCandidatOfOffers(@PathVariable("offerId") Long id){
		
		Offer offer = new Offer();
		offer.setId(id);
		Set<Candidat> candidats = this.candidatService.getCandidatsOfOffer(offer);
		
		return ResponseEntity.ok(candidats);
	}
	
	// Define a method to download file
    @GetMapping("download/{id}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("id") Long id) throws IOException {
        //Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
    	Candidat candidat = this.candidatService.findById(id);
    	Path filePath = Paths.get(context.getRealPath("/Files/")+candidat.getFilename());
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException("filename was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", id.toString());
        //httpHeaders.add("File-Name", "file");
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }
}
