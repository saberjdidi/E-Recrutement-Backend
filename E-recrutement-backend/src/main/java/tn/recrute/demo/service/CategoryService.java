package tn.recrute.demo.service;

import java.util.List;

import tn.recrute.demo.model.Category;

public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public List<Category> getCategories();
	
	public void deleteCategory(Long id);
	
	public Category getCategoryById(Long id);
}
