package tn.recrute.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
import tn.recrute.demo.model.Entreprise;
import tn.recrute.demo.model.Formation;
import tn.recrute.demo.repository.EntrepriseRepository;
import tn.recrute.demo.service.EntrepriseService;

@RestController
@RequestMapping("/entreprise")
@CrossOrigin("*")
public class EntrepriseController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private EntrepriseService entrepriseService;
	
	@Autowired  ServletContext context;
	
	/* @PostMapping("/upload")
	public BodyBuilder saveEntreprise(@RequestParam("imageFile") MultipartFile file, @RequestBody Entreprise entreprise) throws IOException {
		
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		
		Entreprise new_entreprise = new Entreprise(entreprise.getName(), entreprise.getDescription(), file.getOriginalFilename(),
				                                    file.getContentType(), compressBytes(file.getBytes()));
		
		entrepriseRepository.save(new_entreprise);
		return ResponseEntity.status(HttpStatus.OK);
	} 
	
	@PostMapping("/save")
	public BodyBuilder createEntreprise(@RequestParam("imageFile") MultipartFile file, 
			@RequestParam("title") String title, @RequestParam("description") String description) throws IOException {
		
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		
		//Entreprise ent = new ObjectMapper().readValue(entreprise, Entreprise.class);
		
		Entreprise new_entreprise = new Entreprise(title, description, file.getOriginalFilename(),
				                                    file.getContentType(), compressBytes(file.getBytes()));
		
		entrepriseRepository.save(new_entreprise);
		return ResponseEntity.status(HttpStatus.OK);
	}
	
	@GetMapping(path = { "/{imageName}" })
	public Entreprise getImage(@PathVariable("imageName") String imageName) throws IOException {

		final Optional<Entreprise> retrievedEntreprise = entrepriseRepository.findByName(imageName);
		Entreprise img = new Entreprise(retrievedEntreprise.get().getTitle(), retrievedEntreprise.get().getDescription(), retrievedEntreprise.get().getName(), retrievedEntreprise.get().getType(),
				decompressBytes(retrievedEntreprise.get().getPicByte()));
		return img;
	}*/
	@PostMapping("/save")
	 public long createCompany(@RequestParam("file") MultipartFile file,
			 @RequestParam("company") String company) throws JsonParseException , JsonMappingException , Exception
	 {
		 System.out.println("Ok .............");
      Entreprise entreprise = new ObjectMapper().readValue(company, Entreprise.class);
      
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
     
      entreprise.setFileName(newFileName); 
      
      return this.entrepriseService.addEntreprise(entreprise);
	 }
	
	@PostMapping("/")
	public Object create( @RequestParam("company") String company, @RequestParam("file") MultipartFile file) throws JsonParseException , JsonMappingException , Exception {
		Entreprise entreprise = new ObjectMapper().readValue(company, Entreprise.class);
		Entreprise entity = new Entreprise();
		entity.setName(entreprise.getName());
		entity.setDescription(entreprise.getDescription());
		entity.setAddress(entreprise.getAddress());
		if(file != null) {
			entity.setFileName(file.getOriginalFilename());
			entity.setType(file.getContentType());
			try {
				entity.setPicByte(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.entrepriseRepository.save(entity);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getEntities(){
		
		return ResponseEntity.ok(this.entrepriseService.getAll());
	}
	
	@PutMapping("/update")
	public Entreprise updateEntity(@RequestBody Entreprise entreprise) {
		
		return this.entrepriseService.updateEntreprise(entreprise);
	}
	
	@GetMapping("/{companyId}")
	public Entreprise findById(@PathVariable("companyId") Long id) {
		
		return this.entrepriseService.findById(id);
	}
	
	@GetMapping("/image/{id}")
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
		
		Entreprise entreprise = this.entrepriseService.findById(id);
		
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+entreprise.getFileName()));
	}
	
	@DeleteMapping("/{companyId}")
	public void deleteById(@PathVariable("companyId") Long id) {
		
		this.entrepriseService.deleteEntreprise(id);
	}
	
	// compress the image bytes before storing it in the database
		public static byte[] compressBytes(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

			return outputStream.toByteArray();
		}

		// uncompress the image bytes before returning it to the angular application
		public static byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}
}
