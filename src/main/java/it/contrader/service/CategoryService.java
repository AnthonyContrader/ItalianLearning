package it.contrader.service;

import it.contrader.converter.CategoryConverter;
import it.contrader.dao.CategoryDAO;
import it.contrader.dto.CategoryDTO;
import it.contrader.model.Category;

public class CategoryService extends AbstractService<Category, CategoryDTO>{

	public CategoryService() {
		this.dao = new CategoryDAO();
		this.converter = new CategoryConverter();
	}
	
}
