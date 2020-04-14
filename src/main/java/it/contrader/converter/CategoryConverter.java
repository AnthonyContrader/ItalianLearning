package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.CategoryDTO;
import it.contrader.model.Category;

public class CategoryConverter  implements Converter<Category, CategoryDTO> {

	// converte Category Model in CategoryDTO
	public CategoryDTO toDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getTitle() ,category.getDescription());
		return categoryDTO;
	}
	
	//converte category DTO in category model (Entity)
	public Category toEntity(CategoryDTO categoryDto) {
		Category category = new Category(categoryDto.getId(), categoryDto.getTitle(), categoryDto.getDescription());
		return category;
	}
	
	// converte lista di category model (Entity) in lista di categoryDTO
	public List<CategoryDTO> toDTOList(List<Category> categoryList){
		
		//lista vuota
		List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
		
		// ciclo for che itera per ogni elemento di category in categoryList
		for (Category category : categoryList) {
			categoryDTOList.add(toDTO(category));
		}
		return categoryDTOList;

	}
	
}
