package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.CategoryDTO;
import it.contrader.model.Category;

/*
 * Created by Enzo Tasca
 */

@Component
public class CategoryConverter extends AbstractConverter<Category, CategoryDTO>{

	@Override
	public Category toEntity(CategoryDTO categoryDTO) {
		return categoryDTO == null ? null : new Category(categoryDTO.getId(),categoryDTO.getTitle(),categoryDTO.getDescription());
	}

	@Override
	public CategoryDTO toDTO(Category category) {
		return category == null ? null : new CategoryDTO(category.getId(),category.getTitle(),category .getDescription());
	}

}
