package tn.recrute.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.recrute.demo.model.Category;
import tn.recrute.demo.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {

		Category cat = this.categoryService.addCategory(category);
		
		return ResponseEntity.ok(cat);
	}
	
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		
		return this.categoryService.updateCategory(category);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getCategories(){
		
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	@GetMapping("/{catId}")
	public Category getCategoryById(@PathVariable("catId") Long id) {
		
		return this.categoryService.getCategoryById(id);
	}
	
	@DeleteMapping("/{catId}")
	public void deleteCategory(@PathVariable("catId") Long id) {
		
		this.categoryService.deleteCategory(id);
	}
}
