package tn.recrute.demo.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import tn.recrute.demo.model.Category;

public class CategoryValidator {

	public static List<String> validate(Category category){
		List<String> errors = new ArrayList<>();
		
		if(category == null || !StringUtils.hasLength(category.getTitle())) {
			errors.add("Veuillez renseigner le titre de la categorie");
			System.out.println("Veuillez renseigner le titre de la categorie");
		}
		else if(category == null || !StringUtils.hasLength(category.getDescription())) {
			errors.add("Veuillez renseigner la description de la categorie");
			System.out.println("Veuillez renseigner la description de la categorie");
		}
		return errors;
	}
}
