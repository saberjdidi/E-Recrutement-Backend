package tn.recrute.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.recrute.demo.model.Category;
import tn.recrute.demo.model.Formation;
import tn.recrute.demo.model.Response;
import tn.recrute.demo.service.FormationService;

@RestController
@RequestMapping("/formation")
@CrossOrigin("*")
public class FormationController {

	@Autowired
	private FormationService formationService;
	
	@Autowired  ServletContext context;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		
		return ResponseEntity.ok(this.formationService.getAll());
	}
	
	@PostMapping("/save")
	 public ResponseEntity<Response> createTraining (@RequestParam("file") MultipartFile file,
			 @RequestParam("formation") String formation) throws JsonParseException, JsonMappingException, Exception
	 {
		 System.out.println("Ok .............");
       Formation arti = new ObjectMapper().readValue(formation, Formation.class);
       boolean isExit = new File(context.getRealPath("/Images/")).exists();
       if (!isExit)
       {
       	new File (context.getRealPath("/Images/")).mkdir();
       	System.out.println("mk dir.............");
       }
       String filename = file.getOriginalFilename();
       String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
       File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
       try
       {
       	System.out.println("Image");
       	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
       	 
       }catch(Exception e) {
       	e.printStackTrace();
       }

      
       arti.setFileName(newFileName);
       Formation training = this.formationService.addFormation(arti);
       if (training != null)
       {
       	return new ResponseEntity<Response>(new Response ("formation save successfully"),HttpStatus.OK);
       }
       else
       {
       	return new ResponseEntity<Response>(new Response ("formation not saved"),HttpStatus.BAD_REQUEST);	
       }
	 }
	
	@PutMapping("/update")
	public Formation updateCategory(@RequestBody Formation formation) {
		
		return this.formationService.updateFormation(formation);
	}
	
	@GetMapping("/{formationId}")
	public Formation findById(@PathVariable("formationId") Long id) {
		
		return this.formationService.findById(id);
	}
	
	@DeleteMapping("/{formationId}")
	public void deleteCategory(@PathVariable("formationId") Long id) {
		
		this.formationService.deleteFormation(id);
	}
	
	@GetMapping("/image/{id}")
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
		
		Formation formation = this.formationService.findById(id);
		
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+formation.getFileName()));
	}
}
