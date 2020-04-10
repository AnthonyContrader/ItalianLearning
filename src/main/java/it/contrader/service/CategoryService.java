package it.contrader.service;

import java.util.List;

import it.contrader.converter.CategoryConverter;
import it.contrader.dao.CategoryDAO;
import it.contrader.dto.CategoryDTO;
import it.contrader.model.Category;


public class CategoryService {
	
	private CategoryDAO categoryDAO;
	private CategoryConverter categoryConverter;
	
	
	public CategoryService() {
		this.categoryDAO = new CategoryDAO();
		this.categoryConverter = new CategoryConverter();
	}
	
	
	// Ottiene una lista di entita e le restituisce convertendole in DTO 
	public List<CategoryDTO> getAll() {
		return categoryConverter.toDTOList(categoryDAO.getAll());
	}
	
	// Ottiene un'entita e la restituisce convertendola in DTO
	public CategoryDTO read(int id) {
		Category c = categoryDAO.read(id);
		if (c != null)
			return categoryConverter.toDTO(c);
		else
			return null;
	}
	
	// Converte un DTO in entita e lo passa al DAO per l'inserimento
	public boolean insert(CategoryDTO dto) {
		return categoryDAO.insert(categoryConverter.toEntity(dto));
	}
	
	// Converte un categoryDTO in entita e lo passa allo categoryDAO per la modifica
	public boolean update(CategoryDTO dto) {
		return categoryDAO.update(categoryConverter.toEntity(dto));
	}
	
	// Questo metodo chiama direttamente il DAO
	public boolean delete(int id) {
		return categoryDAO.delete(id);
	}
	
}
