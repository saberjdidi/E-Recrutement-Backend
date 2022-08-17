package tn.recrute.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.recrute.demo.helper.InvalidEntityException;
import tn.recrute.demo.model.Category;
import tn.recrute.demo.repository.CategoryRepository;
import tn.recrute.demo.service.CategoryService;
import tn.recrute.demo.validator.CategoryValidator;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		List<String> errors = CategoryValidator.validate(category);
		if(!errors.isEmpty()) {
			System.out.println("La category n'est pas valide");
			throw new InvalidEntityException("La category n'est pas valide", errors);
		}
		
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		List<String> errors = CategoryValidator.validate(category);
		if(!errors.isEmpty()) {
			System.out.println("La category n'est pas valide");
			throw new InvalidEntityException("La category n'est pas valide", errors);
		}
		
		return this.categoryRepository.save(category);
	}

	@Override
	public List<Category> getCategories() {
		
		return this.categoryRepository.findAll();
	}

	@Override
	public void deleteCategory(Long id) {
		
		this.categoryRepository.deleteById(id);
	}

	@Override
	public Category getCategoryById(Long id) {
		
		return this.categoryRepository.findById(id).get();
	}

}
