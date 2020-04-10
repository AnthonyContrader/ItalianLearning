package it.contrader.service;

import java.util.List;

import it.contrader.converter.FindAWordConverter;
import it.contrader.dao.FindAWordDAO;
import it.contrader.dto.FindAWordDTO;
import it.contrader.model.FindAWord;


public class FindAWordService {
	private FindAWordDAO findAWordDAO;
	private FindAWordConverter findAWordConverter;
	
	
	public FindAWordService() {
		this.findAWordDAO = new FindAWordDAO();
		this.findAWordConverter = new FindAWordConverter();

}
	public List<FindAWordDTO> getAll() {
		return findAWordConverter.toDTOList(findAWordDAO.getAll());
	}
	
	// Ottiene un'entita e la restituisce convertendola in DTO
	public FindAWordDTO read(int id) {
		FindAWord c = findAWordDAO.read(id);
		if (c != null)
			return findAWordConverter.toDTO(c);
		else
			return null;
		//return findAWordConverter.toDTO(findAWordDAO.read(id));
	}
	
	// Converte un DTO in entita e lo passa al DAO per l'inserimento
	public boolean insert(FindAWordDTO dto) {
		return findAWordDAO.insert(findAWordConverter.toEntity(dto));
	}
	
	// Converte un categoryDTO in entita e lo passa allo categoryDAO per la modifica
	public boolean update(FindAWordDTO dto) {
		return findAWordDAO.update(findAWordConverter.toEntity(dto));
	}
	
	// Questo metodo chiama direttamente il DAO
	public boolean delete(int id) {
		return findAWordDAO.delete(id);
	}

}


